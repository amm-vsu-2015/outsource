package edu.core.java.print;

class StereoOffCommand implements Command {
    private Stereo stereo;

    StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }
}
