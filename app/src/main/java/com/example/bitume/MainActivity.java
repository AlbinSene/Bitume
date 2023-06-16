package com.example.bitume;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bitume.environment.Building;
import com.example.bitume.environment.Environment;
import com.example.bitume.environment.Furniture;
import com.example.bitume.environment.Loot;
import com.example.bitume.environment.Room;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Environment furniture = new Furniture();
    Environment room = new Room();
    Environment building = new Building();


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button env1 = (Button) findViewById(R.id.env1);
        Button env2 = (Button) findViewById(R.id.env2);
        Button env3 = (Button) findViewById(R.id.env3);

        env1.setOnClickListener((View.OnClickListener) this);
        env2.setOnClickListener((View.OnClickListener) this);
        env3.setOnClickListener((View.OnClickListener) this);

        //recuperation des json pour chaque environnement
        furniture.setJsonString(loadJSONFromAsset(this,"furniture.json"));
        room.setJsonString(loadJSONFromAsset(this,"room.json"));
        building.setJsonString(loadJSONFromAsset(this,"building.json"));

        //chargement des donnees du json dans une liste
        try {
            furniture.loadList();
            room.loadList();
            building.loadList();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

    public void onClick(View view){
        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.env1){
            String l = furniture.search();
            showDialog(l);
        }
        else if (clickedButton.getId()==R.id.env2){
            String l = room.search();
            showDialog(l);
        }
        else if (clickedButton.getId()==R.id.env3){
            String l = building.search();
            showDialog(l);
        }
    }

    public void showDialog(String loot){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Loot");
        //builder.setMessage(loot);
        builder.setView(createMessageView(loot,this));
        //builder.setPositiveButton("OK", (dialogInterface, i) ->actionSuite(score));



        builder.show();
        onPause();
    }

    public static TextView createMessageView(String message, Context context) {
        TextView messageView = (TextView) LayoutInflater.from(context).inflate(R.layout.alert_dialog_loot, null, false);
        messageView.setText(message);
        return messageView;
    }

    //Lecture d'un fichier json
    public String loadJSONFromAsset(Context context,String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}