package com.pavbox;

public class Cat extends Animal {

    // Overrided methods

    @Override
    @Cute(howMuch = 2)
    public void eat() {
        System.out.println("whiskas");
    }

    @Override
    public void roam() {
        System.out.println("frrrrr");
    }

    @Override
    public void setupWeight(float weight) {
        this.weight = weight;
    }

    // new methods

    @Cute
    void purring() {
        System.out.println("pur-pur");
    }
}
