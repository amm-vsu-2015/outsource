package com.pavbox;

public class StupidCat extends Cat implements Sign {
    @Override
    public void roam() {
        System.out.println("Stupid frrrrra");
    }

    public void sign() {
        System.out.println("Muyyyyyyyy-fr-fr-fr-fr");
    }
}
