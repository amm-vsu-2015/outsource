package edu.core.java.print;

public class RussianHen extends Hen {

    @Override
    public int getCountOfEggsPerMonth(){
        return 25;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " Моя страна - " + ICountry.RUSSIA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }

}