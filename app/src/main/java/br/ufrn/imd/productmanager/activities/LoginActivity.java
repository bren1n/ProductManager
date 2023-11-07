package br.ufrn.imd.productmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrn.imd.productmanager.R;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    EditText usernameInput;
    EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.loginBtn = (Button) findViewById(R.id.loginButton);
        this.usernameInput = (EditText) findViewById(R.id.username);
        this.passwordInput = (EditText) findViewById(R.id.password);

        SharedPreferences data = getSharedPreferences("savedData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("user", "admin");
        editor.putString("password", "admin");
        editor.commit();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeLogin();
            }
        });
    }

    private void makeLogin() {
        String typedUser = this.usernameInput.getText().toString();
        String typedPassword = this.passwordInput.getText().toString();

        SharedPreferences data = getSharedPreferences("savedData", Context.MODE_PRIVATE);
        String user = data.getString("user", "");
        String password = data.getString("password", "");

        if (user.equals(typedUser) && password.equals(typedPassword)) {
            Toast.makeText(LoginActivity.this, R.string.Login_successfulLogin, Toast.LENGTH_LONG).show();
            Intent menuActivityIntent = new Intent(getApplicationContext(), MenuActivity.class);
            menuActivityIntent.putExtra("loginSession", true);
            startActivity(menuActivityIntent);
        } else {
            Toast.makeText(LoginActivity.this, R.string.Login_badLogin, Toast.LENGTH_LONG).show();
        }
    }
}