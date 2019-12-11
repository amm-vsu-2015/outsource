package edu.core.java.print;

public class UkrainianHen extends Hen {

    @Override
    public int getCountOfEggsPerMonth(){
        return 67;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " Моя страна - " + ICountry.UKRAINE + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }

}
