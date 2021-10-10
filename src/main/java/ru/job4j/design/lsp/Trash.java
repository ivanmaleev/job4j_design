package ru.job4j.design.lsp;

public class Trash extends FoodStore {

    @Override
    public boolean addFood(Food food) {
        if (FoodDate.calcExpirationDate(food) >= 100) {
            return super.addFood(food);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Trash";
    }
}
