package edu.core.java.print;

public class Drawer implements IColor {

    private IColor textWriter;

    Drawer(IColor textWriter) {
        this.textWriter = textWriter;
    }

    void setWriter(IColor textWriter) {
        this.textWriter = textWriter;
    }

    @Override
    public void paint() {
        textWriter.paint();
    }
}
