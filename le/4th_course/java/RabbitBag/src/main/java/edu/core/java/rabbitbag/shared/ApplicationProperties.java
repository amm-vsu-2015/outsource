package edu.core.java.rabbitbag.shared;

public class ApplicationProperties {

    private ApplicationProperties(){ }

    private static class Holder {
        private static final ApplicationProperties INSTANCE = new ApplicationProperties();
    }

    public static ApplicationProperties getInstance() {
        return Holder.INSTANCE;
    }

}
