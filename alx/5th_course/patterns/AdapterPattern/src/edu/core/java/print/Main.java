package edu.core.java.print;

import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
        IIncomeData income = new UserIncomeData();
        IncomeDataAdapter adapter = new IncomeDataAdapter(income);

        System.out.println(adapter.getCompanyName());
        System.out.println(adapter.getCountryName());
        System.out.println(adapter.getName());
        System.out.println(adapter.getPhoneNumber());
    }
}
