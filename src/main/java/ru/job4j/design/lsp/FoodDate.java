package ru.job4j.design.lsp;

import java.util.Calendar;

public class FoodDate {
    public static int calcExpirationDate(Food food) throws IllegalArgumentException {
        long holeTime = food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        long retainedTime = food.getExpiryDate().getTimeInMillis()
                - Math.min(food.getExpiryDate().getTimeInMillis(), Calendar.getInstance().getTimeInMillis());
        if (holeTime < 0 || retainedTime < 0
                || holeTime - retainedTime < 0) {
            throw new IllegalArgumentException("Wrong dates");
        }
        return (int) ((holeTime - retainedTime) * 100 / holeTime);
    }
}
