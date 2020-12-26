package application01.ensa.uit.ac.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.net.ConnectException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;

    SharedPreferences prefs;

    @BindView(R.id.checkBox)
    CheckBox rememberMe;

    @OnClick(R.id.login)
    public void onLoginBtnClicked(){

        String txtusername = username.getText().toString();
        String txtpassword = password.getText().toString();
        if(rememberMe.isChecked()){
            prefs.edit().putString("login",txtusername).commit();
            prefs.edit().putString("pwd",txtpassword).commit();
        }
        else {
            prefs.edit().putString("login","").commit();
            prefs.edit().putString("pwd","").commit();
        }

        Toast toast = Toast.makeText(this, "Bonjour "+prefs.getString("login","annonyme"), Toast.LENGTH_SHORT);
        toast.show();

        //Start second activity
        Intent intent=new Intent(LoginActivity.this, CategoryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        prefs = getPreferences(Context.MODE_PRIVATE);

        username.setText(prefs.getString("login","no login found"));
        password.setText(prefs.getString("pwd","no pwd found"));

    }
}