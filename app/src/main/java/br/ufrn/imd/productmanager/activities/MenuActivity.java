package br.ufrn.imd.productmanager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import br.ufrn.imd.productmanager.R;

public class MenuActivity extends AppCompatActivity {
    public Boolean loginSession = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Verifica o status do valor da chave 'loginSession'
        loginSession = getIntent().getBooleanExtra("loginSession", false);

        if (!loginSession) {
            Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivityIntent);
        } else {
            Toast.makeText(MenuActivity.this, R.string.successfulLogin, Toast.LENGTH_LONG).show();
        }


    }


}