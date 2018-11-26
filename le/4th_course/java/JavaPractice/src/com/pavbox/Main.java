package com.pavbox;

public class Main {

    public static void main(String[] args) {
//
//        Main.base();
//        System.out.println();
//        Main.advanced();
//        System.out.println();
        Sign b = new Bird();
        Main.pro(b);
        System.out.println();

    }

    private static void base() {
        // Animal animal = new Animal(); // you can't

        Cat cat = new Cat(); // cat's object

        cat.roam();
        cat.eat();
        cat.purring();

        // polymorphism
        Animal a = new Cat();
        a.roam();
        // a.purring(); // can't see purring method

        System.out.println();

        Animal[] all = new Animal[2];

        all[0] = new Cat();
        all[1] = new Dog();

        for (int i = 0; i < all.length; i++) {
            all[i].roam();
        }
    }

    // inheritance
    private static void advanced() {

        Animal animalAnon = new Animal() {
            @Override
            public void eat() {
                System.out.println("Kill me");
            }
        };

        animalAnon.eat();
    }

    // interfaces
    // annotations
    // syntax sugar
    private static void pro(Sign a) {
        Sign signOfBird = new Bird();
        signOfBird.sign(); // only one method available; (polymorphism)

        // signOfBird.roam(); // can't, because not available ^^^

        // Sign signOfCat = new Cat(); // can't, because Dog not implements Sign interface
        Sign signOfCat = new StupidCat();
        signOfCat.sign();
    }
}
