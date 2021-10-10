package ru.job4j.design.isp;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        MenuElement elementJobRoot = new ElementJob("Корень меню");
        MenuElement elementJob1 = new ElementJob("Задача 1");
        MenuElement elementJob11 = new ElementJob("Задача 1.1");
        MenuElement elementJob111 = new ElementJob("Задача 1.1.1");
        MenuElement elementJob112 = new ElementJob("Задача 1.1.2");
        MenuElement elementJob12 = new ElementJob("Задача 1.2");

        List<MenuElement> elementJobRootList = List.of(elementJob1);
        elementJobRoot.setChildrens(elementJobRootList);
        List<MenuElement> elementJob1List = List.of(elementJob11, elementJob12);
        elementJob1.setChildrens(elementJob1List);
        List<MenuElement> elementJob11List = List.of(elementJob111, elementJob112);
        elementJob11.setChildrens(elementJob11List);
        printMenu(elementJobRoot);
    }

    public static void printMenu(MenuElement root) {
        System.out.println(root.getName());
        root.getChildrens().stream()
                .forEach(child -> printMenu(child));
    }
}
