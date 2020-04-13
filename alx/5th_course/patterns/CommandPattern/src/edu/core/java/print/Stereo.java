package edu.core.java.print;

class Stereo {

    void on() {
        System.out.println("Стерео – вкл!");
    }

    void off() {
        System.out.println("Стерео – выкл!");
    }

    void setSD() {
        System.out.println("Стерео – перевести в CD режим!");
    }

    void setDVD() {
        System.out.println("Стерео – перевести в DVD режим!");
    }

    void setRadio() {
        System.out.println("Стерео – перевести в Radio режим!");
    }

    void setVolume(int volume) {
        System.out.println("Стерео – изменить громкость на " + volume + "%!");
    }

}
