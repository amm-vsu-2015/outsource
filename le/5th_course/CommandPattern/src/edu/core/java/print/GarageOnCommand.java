package edu.core.java.print;

class GarageOnCommand implements Command {
    private GarageDoor garageDoor;

    GarageOnCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.on();
    }
}
