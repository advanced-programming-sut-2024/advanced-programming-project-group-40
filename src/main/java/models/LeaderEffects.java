package models;

public class LeaderEffects {
    private boolean spyDoublePower = false;
    private boolean KingBran = false;
    public LeaderEffects() {

    }

    public boolean isKingBran() {
        return KingBran;
    }

    public void setKingBran(boolean kingBran) {
        KingBran = kingBran;
    }

    public boolean isSpyDoublePower() {
        return spyDoublePower;
    }

    public void setSpyDoublePower(boolean spyDoublePower) {
        this.spyDoublePower = spyDoublePower;
    }
}
