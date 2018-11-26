package com.pavbox;

public class Dog extends Animal {

    @Override
    public void eat() {
        System.out.println("pedigree");
    }

    @Override
    public void roam() {
        System.out.println("woof, blyat");
    }

    // TODO: useless
    @Override
    public void setupWeight(float weight) {
         super.setupWeight(weight);
    }

    void fight() {
        System.out.println("Peoples hurts by the fucking dog...");
    }
}
