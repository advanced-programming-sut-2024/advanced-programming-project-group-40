package models;

import java.io.Serializable;

public class Relation implements Serializable {
    private String first;
    private String second;

    public Relation(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public void setFirst(String first){
        this.first = first;
    }
    public void setSecond(String second){
        this.second = second;
    }
}