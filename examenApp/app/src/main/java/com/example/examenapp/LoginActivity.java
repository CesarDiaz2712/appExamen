package com.example.examenapp;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.example.examenapp.api.ApiClient;
import com.example.examenapp.models.LoginRequest;
import com.example.examenapp.models.LoginResponse;

import java.sql.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText usuario,password;
    Button btningresar,btnRegistrar;
    boolean band;

    public static final long DURATION_TRANSITION=1000;
    private Transition transition;

    public void onFadeClicked(View view, boolean band){
        transition= new Fade(Fade.OUT);
        iniciarActividadSecuandaria(band);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.editText_usuarioLogin);
        password = (EditText) findViewById(R.id.editText_Password);

        btningresar = (Button) findViewById(R.id.btn_IngresarLogin);


        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                while (band==false) {

                    if(usuario.getText().toString().isEmpty() && password.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Campos vacios", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else{
                        if(usuario.getText().toString().isEmpty()){
                            Toast.makeText(LoginActivity.this, "Campo usuario vacio", Toast.LENGTH_LONG).show(); return;
                        }
                        if(password.getText().toString().isEmpty()){
                            Toast.makeText(LoginActivity.this, "Campo contraseña vacio", Toast.LENGTH_LONG).show();return;
                        }
                    }
                    band=true;
                }
                if(band){

                    loginCheck(v);
                }
            }
        });
        btnRegistrar=(Button)findViewById(R.id.btn_Registrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onFadeClicked(v,false);
            }
        });
    }

    private static Date date;

    public void loginCheck(View v){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(usuario.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userlogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    date=response.body().getDatenow();
                    Toast.makeText(LoginActivity.this, "Exito", Toast.LENGTH_LONG).show();
                    onFadeClicked(v,true);
                }else{

                    Toast.makeText(LoginActivity.this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Throwable", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void iniciarActividadSecuandaria(boolean band){

        transition.setDuration(DURATION_TRANSITION);
        transition.setInterpolator(new DecelerateInterpolator());

        getWindow().setExitTransition(transition);

        if(band){

            Intent siguiente = new Intent(this, MainActivity.class);
            siguiente.putExtra("datenow", date);
            startActivity(siguiente, ActivityOptionsCompat
                    .makeSceneTransitionAnimation(this).toBundle());
        }else{

            Intent siguiente = new Intent(this, RegisterActivity.class);
            startActivity(siguiente, ActivityOptionsCompat
                    .makeSceneTransitionAnimation(this).toBundle());
        }
    }
}
