package br.ufrn.imd.productmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Fragment {

    public Login() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginBtn = (Button) view.findViewById(R.id.loginButton);

        EditText usernameInput = (EditText) view.findViewById(R.id.username);
        EditText passwordInput = (EditText) view.findViewById(R.id.password);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typedUser = usernameInput.getText().toString();
                String typedPassword = passwordInput.getText().toString();
                makeLogin(typedUser, typedPassword);
            }
        });
        return view;
    }

    private void makeLogin(String typedUser, String typedPassword) {
        SharedPreferences data = getActivity().getSharedPreferences("savedData", Context.MODE_PRIVATE);
        String user = data.getString("user", "");
        String password = data.getString("password", "");

        if (user.equals(typedUser) && password.equals(typedPassword)) {
            Toast.makeText(getActivity(), R.string.successfulLogin, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), R.string.badLogin, Toast.LENGTH_LONG).show();
        }
    }
}