package org.Baccarat;

public class Card {

    private String name;
    private String icon;
    private int value;

    public Card(String name, String icon, int value){
        this.name = name;
        this.icon = icon;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public int getValue() {
        return value;
    }

}