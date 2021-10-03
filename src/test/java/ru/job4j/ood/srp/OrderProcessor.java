package ru.job4j.ood.srp;

public class OrderProcessor {

    public void process(Order order){
        /*process order*/
    }

    private boolean save(Order order) {
        /*сохраняем заказ в базу данных*/
        return true;
    }
}