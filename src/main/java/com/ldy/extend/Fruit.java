package com.ldy.extend;

/**
 * Created by yanz3 on 3/20/17.
 */
public class Fruit {

    private String color;

    private Double price;

    public Fruit(String color, Double price) {
        this.color = color;
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
