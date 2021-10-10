package ru.job4j.design.lsp;

public class Shop extends FoodStore {

    @Override
    public boolean addFood(Food food) {
        if (ControlQuality.calcExpirationDate(food) > 25
                && ControlQuality.calcExpirationDate(food) <= 75) {
            return super.addFood(food);
        } else if (ControlQuality.calcExpirationDate(food) > 75
                && ControlQuality.calcExpirationDate(food) < 100) {
            ControlQuality.discountFood(food);
            return super.addFood(food);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Shop";
    }
}
