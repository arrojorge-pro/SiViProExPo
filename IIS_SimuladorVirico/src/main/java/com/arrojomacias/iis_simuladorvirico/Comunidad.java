package com.arrojomacias.iis_simuladorvirico;
import java.util.*;

/**
 * @author Jorge Arrojo
 */
public class Comunidad {

    

    /**
     * Guarda un valor entero que indica el número de habitantes de cada comunidad
     */
    private int Poblacion;
    
    /**
     * Porcentaje de viajeros entre comunidades. En todas las comunidades los mismos (static). Valores entre [0-1]
     */
    private static float V;
    
    /**
     * Nombre de la comunidad
     */
    private String Nombre;
    
    
    private float numeroIntectados;

    
    /**
     * Constructor por Defecto explícito
     */
    public Comunidad() {
    }
    
    /**
     * Constructor sobrecargado
     * @param poblacion establece la poblacion de la comunidad
     * @param nombre establece el nombre de la comunidad
     */
    public Comunidad(int poblacion, String nombre){
        this.Poblacion=poblacion;
        this.Nombre=nombre;
    }

   //Setters y Getters
    
    public int getPoblacion() {
        return Poblacion;
    }

    public void setPoblacion(int Poblacion) {
        this.Poblacion = Poblacion;
    }

    public static float getV() {
        return V;
    }

    public static void setV(float V) {
        Comunidad.V = V;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public float getNumeroIntectados() {
        return numeroIntectados;
    }

    public void setNumeroIntectados(float numeroIntectados) {
        this.numeroIntectados = numeroIntectados;
    }
    

}