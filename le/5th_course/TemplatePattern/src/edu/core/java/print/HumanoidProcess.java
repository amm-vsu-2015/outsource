package edu.core.java.print;

public abstract class HumanoidProcess {

    private void morning() {
        System.out.println("waking up!");
    }

    private void meet() {
        System.out.println("meet a man!");
    }

    abstract void action();

    private void sleep() {
        System.out.println("sleeping!");
    }

    void process() {
        morning();
        meet();
        action();
        sleep();
    }
}
