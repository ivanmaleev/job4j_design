package ru.job4j.design.lsp;

public class Trash extends FoodStore {

    @Override
    public boolean addFood(Food food) {
        if (ControlQuality.calcExpirationDate(food) >= 100) {
            return super.addFood(food);
        }
        return false;
    }
}
