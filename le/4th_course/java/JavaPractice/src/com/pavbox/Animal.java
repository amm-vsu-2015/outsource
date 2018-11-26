package com.pavbox;

public abstract class Animal {

    // Constants

    protected final int EYES = 2;

    // Properties

    protected float weight;

    // Private Methods

    public abstract void eat();

    // Public methods

    public void roam() {
        System.out.println("Roam...");
    }

    // setter of weight field
    public void setupWeight(float weight) {
        this.weight = weight * 2;
    }

    // getter of weight field
    public float getWeight() {
        return this.weight;
    }


}
