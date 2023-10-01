package com.martintecno.loginusandoarchivostp2.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;


import com.martintecno.loginusandoarchivostp2.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class ApiClient {

    private static File carpeta;
    private static File archivo;

    private static File conectar(Context context){


        if(archivo == null){
            carpeta = context.getFilesDir();
            archivo = new File(carpeta,"usuarios.dat");
        }
        return archivo;
    }


    public static Usuario getUsuarioPorCorreo(Context context, String correo_){

        File archivo = conectar(context);

        try {

            FileInputStream FIS = new FileInputStream(archivo);

            BufferedInputStream BIS = new BufferedInputStream(FIS);

            ObjectInputStream OIS = new ObjectInputStream(BIS);

            Usuario usuario;

            while  ((usuario = (Usuario) OIS.readObject()) != null) {

                if(usuario.getCorreo().equals(correo_)) {

                    return usuario;
                }
            }

            FIS.close();


        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error al leer getUsuarioPorCorreo", Toast.LENGTH_LONG).show();
        } catch (IOException e) {


            // esta excepcion se debe a que se termino de leer el archivo y ya no se puede leer ===>>>> EOFException

        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "Error al castear getUsuarioPorCorreo", Toast.LENGTH_SHORT).show();
        }


        return null;
    }

    public static Usuario registrar(Context context, Usuario usuario){


        File archivo = conectar(context);

        try {
            FileOutputStream FOS = new FileOutputStream(archivo);

            BufferedOutputStream BOS = new BufferedOutputStream(FOS);

            ObjectOutputStream OOS = new ObjectOutputStream(BOS);

            if(ApiClient.getUsuarioPorCorreo(context, usuario.getCorreo()) == null){// si no existe lo registro

                OOS.writeObject(usuario);
                BOS.flush();
                FOS.close();
                return usuario;
            }




        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error al guardar", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
        return null;
    }

    public static Usuario ActualizarUsuario(Context context, Usuario usuario) {

       File archivo = conectar(context);

        try {
            FileOutputStream FOS = new FileOutputStream(archivo);

            BufferedOutputStream BOS = new BufferedOutputStream(FOS);

            ObjectOutputStream OOS = new ObjectOutputStream(BOS);



            if(ApiClient.getUsuarioPorCorreo(context, usuario.getCorreo()) == null){// si no existe el correo actualizo

                OOS.writeObject(usuario);
                BOS.flush();
                FOS.close();
                return usuario;
            }


        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error al guardar", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error E/S", Toast.LENGTH_LONG).show();
        }

        return null;
    }


}
