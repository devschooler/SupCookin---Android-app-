package com.supcookinbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText username ;
    private EditText password ;
    private Button login ;
    private TextView Counter ;
    private int counter = 10 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText)findViewById(R.id.loginUserName) ;
        password = (EditText)findViewById(R.id.loginPassword);
        login = (Button)findViewById(R.id.loginBtn) ;
        Counter = (TextView)findViewById(R.id.counter);

        Counter.setText("Nombre de tentatives de connexions restantes : 10");


        login.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View View) {
                validate(username.getText().toString(), password.getText().toString());
            }

        });

    }


    private void validate(String Username, String Userpassword) {

        if((Username.equals("admin")) && (Userpassword.equals("admin"))) {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else{  counter--;

            Counter.setText("Nombre de tentatives restantes avant bloquage compte : "+ String.valueOf(counter));

            if(counter == 0) {
                login.setEnabled(false);
            }

        }

    }

    }

