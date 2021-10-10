package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ControlQuality {

    List<FoodStore> foodStoreList;

    public ControlQuality(List<FoodStore> foodStoreList) {
        this.foodStoreList = foodStoreList;
    }

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

    public static void discountFood(Food food) {
        food.setPrice(food.getPrice() * (1 - food.getDiscount() / 100));
    }

    public static void main(String[] args) {
        List<Food> foodList = new ArrayList<>();
        Food greenApple = new Apple("GreenApple", new GregorianCalendar(2022, 01, 01),
                new GregorianCalendar(2021, 1, 1), 100, 20);
        Food beef = new Meat("Beef", new GregorianCalendar(2021, 8, 01),
                new GregorianCalendar(2021, 6, 1), 500, 10);
        Food milk = new Milk("Milk", new GregorianCalendar(2021, 11, 01),
                new GregorianCalendar(2021, 9, 1), 70, 5);
        foodList.add(greenApple);
        foodList.add(beef);
        foodList.add(milk);

        List<FoodStore> foodStoreList = List.of(new Warehouse(), new Shop(), new Trash());
        ControlQuality controlQuality = new ControlQuality(foodStoreList);
        controlQuality.sortFood(foodList);
        controlQuality.showStores();
    }

    public void sortFood(List<Food> foodList) {
        foodStoreList.stream().forEach(foodStore -> foodList
                .stream()
                .forEach(foodStore::addFood));
    }

    public void showStores() {
        for (FoodStore foodStore : foodStoreList) {
            System.out.println(foodStore);
            foodStore.foodList.forEach(System.out::println);
        }
    }
}
