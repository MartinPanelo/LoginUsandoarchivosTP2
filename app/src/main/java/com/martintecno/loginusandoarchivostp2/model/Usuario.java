package com.martintecno.loginusandoarchivostp2.model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private String dni;
    private String apellido;
    private String nombre;
    private String correo;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(String dni, String apellido, String nombre, String correo, String contraseña) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }


}
