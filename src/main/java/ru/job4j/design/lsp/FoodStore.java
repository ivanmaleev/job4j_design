package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public abstract class FoodStore {

    protected List<Food> foodList = new ArrayList<>();

    public boolean addFood(Food food) {
        return foodList.add(food);
    }

    private void removeFood(Food food) {
        foodList.remove(food);
    }

    public List<Food> getFoodList() {
        return foodList;
    }
}
