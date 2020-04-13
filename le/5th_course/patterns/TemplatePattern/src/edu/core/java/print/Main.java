package edu.core.java.print;

public class Main {

    public static void main(String[] args) {

        BadHumanoid badboy = new BadHumanoid();
        GoodHumanoid goodboy = new GoodHumanoid();

        System.out.println("good boy's day:");
        goodboy.process();

        System.out.println();
        System.out.println("bad boy's day:");
        badboy.process();
    }
}
