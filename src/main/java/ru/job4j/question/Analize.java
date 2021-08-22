package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {

        Set<User> copyCurrent = new HashSet<>(current);
        copyCurrent.removeAll(previous);
        int added = copyCurrent.size();

        Set<User> copyPrevious = new HashSet<>(previous);
        copyPrevious.removeAll(current);
        int deleted = copyPrevious.size();

        copyCurrent = new HashSet<>(current);
        copyCurrent.retainAll(previous);
        int changed = 0;
        for (User userCur : copyCurrent) {
            for (User userPrev : previous) {
                if (userCur.equals(userPrev) && !userCur.getName().equals(userPrev.getName())) {
                    changed++;
                }
            }
        }
        Info info = new Info(added, changed, deleted);

        return info;
    }

}