package com.martintecno.loginusandoarchivostp2.ui.registro;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.martintecno.loginusandoarchivostp2.API.ApiClient;
import com.martintecno.loginusandoarchivostp2.model.Usuario;


public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;

    private MutableLiveData<String> TituloM;

    private MutableLiveData<String> BotonM;
    private MutableLiveData<Usuario> usuarioM;

    private String correoF;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    public LiveData<String> getTituloM(){
        if(TituloM == null){
            TituloM = new MutableLiveData<>();
        }
        return TituloM;
    }
    public LiveData<String> getBotonM(){
        if(BotonM == null){
            BotonM = new MutableLiveData<>();
        }
        return BotonM;
    }

    public LiveData<Usuario> getUsuarioM(){
        if(usuarioM == null){
            usuarioM = new MutableLiveData<>();
        }
        return usuarioM;
    }


    public void cargarSesion(String correo_){

        if(correo_ != null){
            TituloM.setValue("Perfil de usuario");
            BotonM.setValue("Guardar");
            this.correoF = correo_;

            usuarioM.setValue(ApiClient.getUsuarioPorCorreo(context, correo_));
        }else{
            TituloM.setValue("Registrar Usuario");
            BotonM.setValue("Registrar");
        }
    }


    public void ActualizarRegistrar(String dni, String apellido, String nombre, String correo, String contraseña) {

        if(dni.isEmpty() || apellido.isEmpty() || nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty()){
            Toast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
        }else{
            Usuario usuario = new Usuario(dni, apellido, nombre, correo, contraseña);


            if (this.correoF != null) {

                this.correoF = ApiClient.ActualizarUsuario(context, usuario).getCorreo();

                if(this.correoF != null) {
                    Toast.makeText(context, "Actualizado con exito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Error al actualizar", Toast.LENGTH_LONG).show();
                }
                this.cargarSesion(correoF);

            } else {

                this.correoF = ApiClient.registrar(context, usuario).getCorreo();

                if (this.correoF != null) {
                    Toast.makeText(context, "Registrado con exito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Error al registrar", Toast.LENGTH_LONG).show();
                }
                this.cargarSesion(correoF);


            }
        }


    }



}
