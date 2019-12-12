package edu.core.java.print;

public class MoldovanHen extends Hen {

    @Override
    public int getCountOfEggsPerMonth(){
        return 16;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " Моя страна - " + ICountry.MOLDOVA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }

}