package com.horisoes.modelo;

import java.io.Serializable;
/**
 *
 * @author Pol taborda
 */
public class Pelicula implements Serializable
{

    private int idpeli_serie;
    private String nombre;
    private String tipo;
    private int calificacion;
    private String imagen;
    private int duracion;
    
    
    public String getImagen() {

        if (imagen != null) {
            return imagen.trim();
        }
        else{
               return "";
        }
    }

    public void setImagen(String imagen) {
        this.imagen = imagen.trim();
    }

    public int getIdpeli_serie() {
        return idpeli_serie;
    }

    public void setIdpeli_serie(int idpeli_serie) {
        this.idpeli_serie = idpeli_serie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

}
