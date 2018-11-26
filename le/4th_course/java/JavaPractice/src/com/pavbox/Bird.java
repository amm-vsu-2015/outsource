package com.pavbox;

public class Bird extends Animal implements Sign {

    @Override
    public void eat() {
        System.out.println("Bread");
    }

    @Override
    public void roam() {
        System.out.println("puy-puy");
    }

    @Override
    public void setupWeight(float weight) {
        this.weight = weight * 0.1f;
    }

    // implement interfaces

    @Override
    public void sign() {
        System.out.println("Bird's sighing...");
    }
}
