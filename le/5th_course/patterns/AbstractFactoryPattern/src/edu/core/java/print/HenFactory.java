package edu.core.java.print;

class HenFactory {

    static Hen getHen(String country) {
        Hen hen = new BelarusianHen();

        switch (country) {
            case ICountry.BELARUS:
                hen = new BelarusianHen();
                break;
            case ICountry.RUSSIA:
                hen = new RussianHen();
                break;
            case ICountry.UKRAINE:
                hen = new UkrainianHen();
                break;
            case ICountry.MOLDOVA:
                hen = new MoldovanHen();
                break;
        }

        return hen;
    }
}