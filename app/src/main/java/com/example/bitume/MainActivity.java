package com.example.bitume;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bitume.environment.Loot;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
            Loot l = new Loot();
            Log.d("DEBUG",l.toString());
            showDialog(l);

        }
    }

    public void showDialog(Loot loot){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Loot");
        builder.setMessage(loot.toString());
        //builder.setPositiveButton("OK", (dialogInterface, i) ->actionSuite(score));
        builder.show();
        onPause();
    }
}