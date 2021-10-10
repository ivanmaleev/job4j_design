package ru.job4j.design.isp;

import java.util.List;

public class Program {

    private MenuElement root;

    public Program(MenuElement root) {
        this.root = root;
    }

    public static void main(String[] args) {
        MenuElement elementJobRoot = new ElementJob("Корень меню");
        MenuElement elementJob1 = new ElementJob("Задача 1");
        MenuElement elementJob11 = new ElementJob("Задача 1.1");
        MenuElement elementJob111 = new ElementJob("Задача 1.1.1");
        MenuElement elementJob112 = new ElementJob("Задача 1.1.2");
        MenuElement elementJob12 = new ElementJob("Задача 1.2");

        Action action = new SimpleAction();
        elementJob111.setAction(action);

        Program program = new Program(elementJobRoot);

        List<MenuElement> elementJobRootList = List.of(elementJob1);
        elementJobRoot.setChildrens(elementJobRootList);
        List<MenuElement> elementJob1List = List.of(elementJob11, elementJob12);
        elementJob1.setChildrens(elementJob1List);
        List<MenuElement> elementJob11List = List.of(elementJob111, elementJob112);
        elementJob11.setChildrens(elementJob11List);
        printMenu(elementJobRoot);
        program.doAction("Задача 1.1.1");
    }

    public static void printMenu(MenuElement root) {
        System.out.println(root.getName());
        root.getChildrens().stream()
                .forEach(Program::printMenu);
    }

    public static MenuElement select(MenuElement root, String name) {
        if (root.getName().equals(name)) {
            return root;
        }
        for (MenuElement child : root.getChildrens()) {
            MenuElement select = select(child, name);
            if (select != null) {
                return select;
            }
        }
        return null;
    }

    public void doAction(String name) {
        MenuElement select = select(this.root, name);
        if (select != null) {
            select.getAction().doAction();
        }
    }
}
