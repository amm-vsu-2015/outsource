package edu.core.java.print;

class Invoker {

    static void invoke() {
        RemoteControl remote = new RemoteControl();

        Light kitchenLamp = new Light("Кухня");
        LightOnCommand kitchenLampOn = new LightOnCommand(kitchenLamp);
        LightOffCommand kitchenLampOff = new LightOffCommand(kitchenLamp);

        remote.setCommand(0, kitchenLampOn, kitchenLampOff);

        Light garageLamp = new Light("Гараж");
        LightOnCommand garageLampOn = new LightOnCommand(garageLamp);
        LightOffCommand garageLampOff = new LightOffCommand(garageLamp);

        remote.setCommand(1, garageLampOn, garageLampOff);

        Stereo stereoCenter = new Stereo();
        StereoOnAndSetupCommand stereoOnAndSetup = new StereoOnAndSetupCommand(stereoCenter);
        StereoOffCommand stereoOff = new StereoOffCommand(stereoCenter);

        remote.setCommand(2, stereoOnAndSetup, stereoOff);

        GarageDoor garageDoor = new GarageDoor();
        GarageOnCommand garageDoorOn = new GarageOnCommand(garageDoor);
        GarageOffCommand garageDoorOff = new GarageOffCommand(garageDoor);

        remote.setCommand(3, garageDoorOn, garageDoorOff);

        System.out.println(remote);

        remote.clickOnButton(0);
        remote.clickOnButton(1);
        remote.clickOnButton(2);
        remote.clickOffButton(0);
        remote.clickOffButton(2);
        remote.clickOnButton(3);
        remote.clickOffButton(3);
        remote.clickOffButton(1);

        remote.undo();
    }

}
