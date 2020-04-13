package edu.core.java.print;

class StereoOnAndSetupCommand implements Command {
    private Stereo stereo;

    StereoOnAndSetupCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setRadio();
        stereo.setVolume(50);
    }
}
