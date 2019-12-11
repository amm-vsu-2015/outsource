package edu.core.java.print;

class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++){
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    void setCommand(int slot, Command onCommand, Command offCommmand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommmand;
    }

    void clickOnButton(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    void clickOffButton(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    void undo() {
        undoCommand.execute();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n------ Remote Control ------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuffer.append("[slot " + i + "] " + onCommands[i].getClass().getName());
            stringBuffer.append("   " + offCommands[i].getClass().getName() + "\n");
        }
        return stringBuffer.toString();
    }
}