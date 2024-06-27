package models.UserInputHandler;

import models.MatchTable;

public abstract class Command {
    private MatchTable recieverMatchtable;

    public Command() {
    }

    public Command(MatchTable recieverMatchtable) {
        this.recieverMatchtable = recieverMatchtable;
    }
    public MatchTable getRecieverMatchtable() {
        return recieverMatchtable;
    }

    public void setRecieverMatchtable(MatchTable recieverMatchtable) {
        this.recieverMatchtable = recieverMatchtable;
    }

    abstract public void excute();
}
