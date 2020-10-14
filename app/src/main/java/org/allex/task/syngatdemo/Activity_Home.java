package org.allex.task.syngatdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__home);
        Button bEnviar = (Button) findViewById(R.id.btnInformacion);


        bEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent paso = new Intent(Activity_Home.this, activity_informacion.class);
                startActivity(paso);
            }
        });
    }
}