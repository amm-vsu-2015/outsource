package edu.core.java.print;

public class Main {

    public static void main(String[] args) {

        IColor redWriter = new TextRedColor();
        IColor blueWriter = new TextBlueColor();

        Drawer drawer = new Drawer(redWriter);
        drawer.paint();

        drawer.setWriter(blueWriter);
        drawer.paint();
    }
}
