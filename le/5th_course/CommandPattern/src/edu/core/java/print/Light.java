package edu.core.java.print;

class Light {
    private String location;

    Light(String location) {
        this.location = location;
    }

    void on() {
        System.out.println(this.location + ": свет – вкл!");
    }

    void off() {
        System.out.println(this.location + ": свет – выкл!");
    }

}
