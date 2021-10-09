package ru.job4j.design.lsp;

public class Warehouse extends FoodStore {

    @Override
    public void addFood(Food food) {
        if (ControlQuality.calcExpirationDate(food) <= 25) {
            super.addFood(food);
        }
    }
}
