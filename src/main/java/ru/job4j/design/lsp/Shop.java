package ru.job4j.design.lsp;

public class Shop extends FoodStore {

    @Override
    public boolean addFood(Food food) {
        int exp = FoodDate.calcExpirationDate(food);
        if (exp > 25
                && exp <= 75) {
            return super.addFood(food);
        } else if (exp > 75
                && exp < 100) {
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
