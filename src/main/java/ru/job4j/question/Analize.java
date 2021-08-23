package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {

        Map<Integer, User> mapCurrent = current.stream()
                .collect(Collectors.toMap(User::getId, user -> user));
        int deleted = 0;
        int changed = 0;
        for (User user : previous) {
            User curUser = mapCurrent.get(user.getId());
            if (curUser == null) {
                deleted++;
            } else if (!user.getName().equals(curUser.getName())) {
                changed++;
            }
            mapCurrent.remove(user.getId());
        }
        int added = mapCurrent.size();
        return new Info(added, changed, deleted);
    }

}