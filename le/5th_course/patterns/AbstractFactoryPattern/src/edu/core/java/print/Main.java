package edu.core.java.print;

public class Main {

    public static void main(String[] args) {
        Hen belHen = HenFactory.getHen(ICountry.BELARUS);
        System.out.println(belHen.getDescription());

        Hen rusHen = HenFactory.getHen(ICountry.RUSSIA);
        System.out.println(rusHen.getDescription());

        Hen molHen = HenFactory.getHen(ICountry.MOLDOVA);
        System.out.println(molHen.getDescription());

        Hen ukrHen = HenFactory.getHen(ICountry.UKRAINE);
        System.out.println(ukrHen.getDescription());
    }

}
