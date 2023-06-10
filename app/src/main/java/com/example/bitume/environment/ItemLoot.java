package com.example.bitume.environment;

public class ItemLoot {

    private String name;
    private int rarity;

    public ItemLoot(String name, int rarity){
        this.name = name;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }

    public String toString(){
        return this.name + ", NR" + rarity;
    }
}
