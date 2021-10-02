package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    public void initConnection() throws ClassNotFoundException, SQLException {
        Config config = new Config("app.properties");
        config.load();
        Class.forName("org.postgresql.Driver");
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
        String query = String.format(
                "create table if not exists %s(%s);",
                tableName,
                "id serial primary key"
        );
        execute(query);
    }

    private void execute(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        String query = String.format(
                "drop table if exists %s;",
                tableName
        );
        execute(query);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String query = String.format(
                "alter table %s add column if not exists %s %s;",
                tableName,
                columnName,
                type
        );
        execute(query);
    }

    public void dropColumn(String tableName, String columnName) {
        String query = String.format(
                "alter table %s drop column if exists %s;",
                tableName,
                columnName
        );
        execute(query);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String query = String.format(
                "alter table %s rename column %s to %s;",
                tableName,
                columnName,
                newColumnName
        );
        execute(query);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        final Properties properties = new Properties();
        try (InputStream in = new FileInputStream("app.properties")) {
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.initConnection();
            tableEditor.createTable("test_table");
            tableEditor.addColumn("test_table", "name", "varchar(255)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}