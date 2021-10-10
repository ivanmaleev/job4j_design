package ru.job4j.design.lsp;

public class Warehouse extends FoodStore {

    @Override
    public boolean addFood(Food food) {
        if (ControlQuality.calcExpirationDate(food) <= 25) {
            return super.addFood(food);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Warehouse";
    }
}
