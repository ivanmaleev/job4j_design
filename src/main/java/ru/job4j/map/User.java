package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        Calendar calendar = new Calendar();
        User userIvan = new User("Ivan", 10, calendar);
        User userIvanM = new User("Ivan", 10, calendar);

        HashMap<User, Object> users = new HashMap<>();
        users.put(userIvan, new Object());
        users.put(userIvanM, new Object());

        for (User user : users.keySet()) {
            System.out.println(user.hashCode());
        }
    }
}
