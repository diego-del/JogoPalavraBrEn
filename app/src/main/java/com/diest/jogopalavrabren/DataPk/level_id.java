package com.diest.jogopalavrabren.DataPk;

public class level_id {
    private String nameLevel;
    private String numberLevel;

    public level_id(String number, String name) {
        this.numberLevel = number;
        this.nameLevel = name;
    }

    public String get_nameLevel() {
        return nameLevel;
    }

    public void set_nameLevel(String name) {
        this.nameLevel = name;
    }

    public String get_numberLevel() {
        return numberLevel;
    }

    public void set_numberLevel(String number) {
        this.numberLevel = number;
    }
}
