package edu.core.java.print;

class GoodHumanoid extends HumanoidProcess {

    @Override
    void meetReaction() {
        System.out.println("say: nice to meet you! <3");
    }

    @Override
    void action() {
        System.out.println("talking about pets...");
    }

}
