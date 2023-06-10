package com.example.bitume;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bitume.environment.Environment;
import com.example.bitume.environment.Loot;
import com.example.bitume.environment.Room;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Environment room = new Room();

    public MainActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button env1 = (Button) findViewById(R.id.env1);

        env1.setOnClickListener((View.OnClickListener) this);


    }

    public void onClick(View view){
        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.env1){
            String l = room.search();
            showDialog(l);
        }
        else if (clickedButton.getId()==R.id.env2){

        }
        else if (clickedButton.getId()==R.id.env3){

        }
    }

    public void showDialog(String loot){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Loot");
        builder.setMessage(loot);
        //builder.setPositiveButton("OK", (dialogInterface, i) ->actionSuite(score));
        builder.show();
        onPause();
    }
}