package edu.core.java.print;

class IncomeDataAdapter implements ICustomer, IContact {

    private IIncomeData data;

    IncomeDataAdapter(IIncomeData data) {
        this.data = data;
    }

    // ICustomer

    @Override
    public String getCompanyName() {
        return data.getCompany();
    }

    @Override
    public String getCountryName() {
        return Main.countries.get(data.getCountryCode());
    }

    // IContact

    @Override
    public String getName() {
        return String.format("%s, %s", data.getContactLastName(), data.getContactFirstName());
    }

    @Override
    public String getPhoneNumber() {
        return String.format("+%d %d", data.getCountryPhoneCode(), data.getPhoneNumber());
    }

}
