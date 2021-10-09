package ru.job4j.design.lsp;

public class Trash extends FoodStore {

    @Override
    public void addFood(Food food) {
        if (ControlQuality.calcExpirationDate(food) >= 100) {
            super.addFood(food);
        }
    }
}
