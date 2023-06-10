package com.example.bitume.environment;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

//classe mere representant un environnement a fouiller
public abstract class Environment {

    String fileName; //Nom du fichier qui contient les infos de loot
    Loot loot; //loot de ressources
    int ressLow; //limite basse de ressources trouvable (inclue)
    int ressHigh; //limite haute de ressources trouvable (exclue)

    ArrayList<String> listItemsNr1 = new ArrayList<>(); //liste des items trouvables dans l'environnement
    ArrayList<String> listItemsNr2 = new ArrayList<>();
    ArrayList<String> listItemsNr3 = new ArrayList<>();
    int nr1; //proba de trouver selon chaque rarete
    int nr2;
    int nr3;
    ItemLoot itemLoot;  //item trouve

    //fonction appelee lors d'une fouille
    public String search(){
        searchLoot();
        searchItem();
        return this.toString();
    }

    protected void loadList() throws JSONException {
        String testjson = "{nr1:[item1,item2],nr2:[item3,item4],nr3:[item5]}";
        JSONObject jObject = new JSONObject(testjson);

        JSONArray jArray1 = jObject.getJSONArray("nr1");
        for (int i=0; i < jArray1.length(); i++){
            String s = jArray1.getString(i);
            listItemsNr1.add(s.toString());
        }

        JSONArray jArray2 = jObject.getJSONArray("nr2");
        for (int i=0; i < jArray2.length(); i++){
            String s = jArray2.getString(i);
            listItemsNr2.add(s.toString());
        }

        JSONArray jArray3 = jObject.getJSONArray("nr3");
        for (int i=0; i < jArray3.length(); i++){
            String s = jArray3.getString(i);
            listItemsNr3.add(s.toString());
        }
    }

    protected void searchLoot(){
        loot = new Loot(ressLow,ressHigh);
    }

    protected void searchItem(){
        Random r = new Random();
        int low = 0;
        int high = 100;
        int result = r.nextInt(high-low) + low;
        Log.d("DEBUG","random : " + result);

        if (result < nr1){
            low=0;
            high=listItemsNr1.size();
            result = r.nextInt(high-low) + low;

            itemLoot = new ItemLoot(listItemsNr1.get(result),1);
        }
        else if (result < nr1+nr2) {
            low=0;
            high=listItemsNr2.size();
            result = r.nextInt(high-low) + low;

            itemLoot = new ItemLoot(listItemsNr2.get(result),2);
        }
        else {
            low=0;
            high=listItemsNr3.size();
            result = r.nextInt(high-low) + low;

            itemLoot = new ItemLoot(listItemsNr3.get(result),3);
        }


    }

    public String toString(){
        return loot.toString() + "\n" + itemLoot.toString();
    }



}
