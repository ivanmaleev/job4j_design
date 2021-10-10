package ru.job4j.design.lsp;

public class Shop extends FoodStore {

    @Override
    public void addFood(Food food) {
        if (ControlQuality.calcExpirationDate(food) > 25
                && ControlQuality.calcExpirationDate(food) <= 75) {
            super.addFood(food);
        } else if (ControlQuality.calcExpirationDate(food) > 75
                && ControlQuality.calcExpirationDate(food) < 100) {
            ControlQuality.discountFood(food);
            super.addFood(food);
        }
    }

}
