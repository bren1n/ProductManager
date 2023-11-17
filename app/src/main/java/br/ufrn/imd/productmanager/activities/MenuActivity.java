package br.ufrn.imd.productmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.ufrn.imd.productmanager.R;

public class MenuActivity extends AppCompatActivity {
    Boolean loginSession, passwordDefault;
    Button buttonCreate, buttonRead, buttonDelete, buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        SharedPreferences data = getSharedPreferences("savedData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();

        editor.putString("user", "admin");
        editor.putString("password", "admin");

        editor.commit();

        this.loginSession = getIntent().getBooleanExtra("loginSession", false);

        if (!loginSession) {
            Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivityIntent);
            this.finish();
        }

        buttonCreate = (Button) findViewById(R.id.Menu_buttonCreate);
        buttonRead = (Button) findViewById(R.id.Menu_buttonRead);
        buttonDelete = (Button) findViewById(R.id.Menu_buttonDelete);
        buttonUpdate = (Button) findViewById(R.id.Menu_buttonUpdate);

        Intent cadastroIntent = new Intent(this, CadastroActivity.class);
        Intent listagemIntent = new Intent(this, ListaActivity.class);
        Intent edicaoIntent = new Intent(this, EdicaoActivity.class);
        Intent deleteIntent = new Intent(this, DeleteActivity.class);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cadastroIntent);
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(listagemIntent);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(edicaoIntent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(deleteIntent);
            }
        });



    }

}