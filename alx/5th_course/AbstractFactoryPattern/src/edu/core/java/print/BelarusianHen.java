package edu.core.java.print;

public class BelarusianHen extends Hen {

    @Override
    public int getCountOfEggsPerMonth() {
        return 95;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " Моя страна - " + ICountry.BELARUS + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }

}