package edu.core.java.print;

public class UserIncomeData implements IIncomeData {

    @Override
    public String getCompany() {
        return "Microsoft";
    }

    @Override
    public String getCountryCode() {
        return "RU";
    }

    @Override
    public String getContactFirstName() {
        return "Billy";
    }

    @Override
    public String getContactLastName() {
        return "Gates";
    }

    @Override
    public int getCountryPhoneCode() {
        return 1;
    }

    @Override
    public int getPhoneNumber() {
        return 1119992233;
    }
}
