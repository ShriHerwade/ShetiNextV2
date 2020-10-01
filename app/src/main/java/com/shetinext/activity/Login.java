package com.shetinext.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button button = (Button) findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashBoard();
            }
        });

        Button button2 = (Button) findViewById(R.id.buttonRegister);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        Button button3 = (Button) findViewById(R.id.buttonAddExpence);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewExpence();
            }
        });

        Button button4 = (Button) findViewById(R.id.buttonDashboard);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashBoard();
            }
        });

        Button button5 = (Button) findViewById(R.id.buttonAddPersonFirm);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPersonFirm();
            }
        });


    }

    public void dashBoard() {
        System.out.println("In successActivity calling");
        Intent intent1 = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(intent1);
    }

    public void register() {
        System.out.println("in registerActivity calling");
        Intent intent2 = new Intent(getApplicationContext(), RegisterUser.class);
        startActivity(intent2);
    }

    public void addPersonFirm() {
        System.out.println("in registerActivity calling");
        Intent intent3 = new Intent(getApplicationContext(), RegisterPersonFirm.class);
        startActivity(intent3);
    }

    public void addNewExpence() {
        System.out.println("in registerActivity calling");
        Intent intent4 = new Intent(getApplicationContext(), RegisterExpence.class);
        startActivity(intent4);
    }
}