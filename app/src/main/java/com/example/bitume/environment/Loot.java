package com.example.bitume.environment;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Random;

//classe representant le contenu du loot des ressources
public class Loot {
    private int food;
    private int water;
    private int fuel;
    private int ammo;
    private int generic;

    //proba de loot de chaque type de ressource
    private int probFood = 15;
    private int probWater = 15;
    private int probFuel = 10;
    private int probAmmo = 10;
    private int probGeneric = 50;

    public Loot(int low, int high){
        generateRessource(low, high);

    }

    //genere le nombre de ressource lootee
    private void generateRessource(int low, int high){
        Random r = new Random();
        int result = r.nextInt(high-low) + low;

        for (int i=0 ; i<result ; i++){
            low = 0;
            high = 100;
            int random = r.nextInt(high-low) + low;
            repartRessource(random);
        }
    }

    //repartie les ressources en fonction des proba
    private void repartRessource(int proba){
        if (proba < probGeneric){
            generic++;
        }
        else if (proba < probGeneric+probWater){
            water++;
        }
        else if (proba < probGeneric+probWater+probFood){
            food++;
        }
        else if (proba < probGeneric+probWater+probFood+probFuel){
            fuel++;
        }
        else {
            ammo++;
        }

    }

    //pour debugguer
    @NonNull
    @Override
    public String toString() {

        return "Vivres : " + food + "\nEau : " + water + "\nCarburant : " + fuel + "\nMunition " + ammo + "\nBric a brac : " + generic;
    }
}
