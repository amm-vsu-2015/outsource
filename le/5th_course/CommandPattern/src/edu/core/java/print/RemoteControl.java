package edu.core.java.print;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;

    RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++){
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    void setCommand(int slot, Command onCommand, Command offCommmand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommmand;
    }

    void clickOnButton(int slot) {
        onCommands[slot].execute();
    }

    void clickOffButton(int slot) {
        offCommands[slot].execute();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n------ Remote Control ------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuffer.append("[slot " + i + "] " + onCommands[i].getClass().getName() +
                    "   " + offCommands[i].getClass().getName() + "\n");
        }
        return stringBuffer.toString();
    }
}