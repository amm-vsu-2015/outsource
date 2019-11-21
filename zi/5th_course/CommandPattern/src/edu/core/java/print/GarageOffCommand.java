package edu.core.java.print;

class GarageOffCommand implements Command {
    private GarageDoor garageDoor;

    GarageOffCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.off();
    }
}
