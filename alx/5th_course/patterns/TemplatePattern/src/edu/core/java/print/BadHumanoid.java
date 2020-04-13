package edu.core.java.print;

class BadHumanoid extends HumanoidProcess {

    @Override
    void meetReaction() {
        System.out.println("say: nice to EAT you! (>*,,*<)");
    }

    @Override
    void action() {
        System.out.println("eats a man...");
    }

}
