package edu.core.java.print;

import java.util.Stack;

class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Stack<Command> undoCommand;

    RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++){
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = new Stack<>();
    }

    void setCommand(int slot, Command onCommand, Command offCommmand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommmand;
    }

    void clickOnButton(int slot) {
        onCommands[slot].execute();
        undoCommand.push(onCommands[slot]);
    }

    void clickOffButton(int slot) {
        offCommands[slot].execute();
        undoCommand.push(offCommands[slot]);
    }

    void undo() {
        while (!undoCommand.empty()) {
            undoCommand.pop().execute();
        }
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
