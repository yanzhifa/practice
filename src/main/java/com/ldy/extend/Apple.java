package com.ldy.extend;

/**
 * Created by yanz3 on 3/20/17.
 */
public class Apple extends Fruit {
    private String name;

    public Apple(String color, Double price) {
        super(color, price);
    }

    public Apple(String color, Double price, String name) {
        super(color, price);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
