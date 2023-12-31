package com.martintecno.loginusandoarchivostp2.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.martintecno.loginusandoarchivostp2.API.ApiClient;
import com.martintecno.loginusandoarchivostp2.model.Usuario;
import com.martintecno.loginusandoarchivostp2.ui.registro.RegistroActivity;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }


    public void RegistrarUsuario(){

        Intent intent = new Intent(context, RegistroActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }


    public void ingresar(String correo_, String contraseña_){

        Usuario usuario = ApiClient.getUsuarioPorCorreo(context, correo_,true);

        if(usuario != null && usuario.getCorreo().equals(correo_) && usuario.getContraseña().equals(contraseña_)){


            Intent intent = new Intent(context, RegistroActivity.class);


            intent.putExtra("correo", correo_);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            Toast.makeText(context, "Ingreso con exito", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }



    }
















}
