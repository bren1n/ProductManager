package br.ufrn.imd.productmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Login loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstInitLoginFragment();

        SharedPreferences data = getSharedPreferences("savedData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("user", "admin");
        editor.putString("password", "admin");
        editor.commit();
    }

    public void firstInitLoginFragment() {
        loginFragment = new Login();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, loginFragment);
        transaction.commit();
    }
}