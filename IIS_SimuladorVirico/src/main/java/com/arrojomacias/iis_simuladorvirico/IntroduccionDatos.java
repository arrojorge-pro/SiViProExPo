package com.arrojomacias.iis_simuladorvirico;
import java.util.ArrayList;

/**
 * @author Jorge Arrojo
 */
public class IntroduccionDatos {

   

    /**
     * Numero de Contactos
     */
    private int E;
    
    /**
     * Probalidad de Contagio. Valores entre [0-1]
     */
    private float p;
    
    /**
     * Número de días que van a ser simulados
     */
    private int dias;
    
    /**
     * Agrupacion de objetos tipo Comunidad
     */
    ArrayList <Comunidad> coleccionComunidades =new ArrayList<>();
    
    /**
     * Número de comunidades que forman parte de la simulación
     */
    private int numComu;
     /**
     * Default constructor
     * Establece valor de los atributos a través de entrada estandar de teclado
     */
    public IntroduccionDatos() {
        this.pedirComunidad();
        this.E=pedirE();
        this.p=pedirp();
        this.dias=pedirDias();
        
    }


    /**
     * Solicita al usuario los datos de la(s) comunidad(es) que formarán parte de la simulación. 
     * No retorna. Asigna objeto(s) comunidad(es) a la colección definida como atributo de clase
     * Valida que el número de comunidades sea positivo
     * Valida que el numero de viajeros solo se introduzca si existe más de una comunidad. Éste debe estar comprendido entre [0-1] representando un porcentaje.
     * Valida que la población tenga al menos un valor = 1
     */
    public void pedirComunidad() {
        do{
            System.out.println("Introduzca Número de comunidades o poblaciones que formarán parte de la simulación. Entero mayor que cero.");
            numComu = Lanzador.Menus.pedirEntero();
        }while(this.numComu<=0);
        if (this.numComu!=1){
        
            float viajeros=-1;
            do{
            System.out.println("Introduzca porcentaje de viajeros para todas las comunidades\n Entre [0-1]");
            viajeros = Lanzador.Menus.pedirFloat();
            }while(viajeros<0||viajeros>1);
            Comunidad.setV(viajeros);
        }else{
            Comunidad.setV(0);
        }
        for (int i= 0;i<numComu;i++){
            System.out.println("Introduzca Nombre de Comunidad " + (int)(i+1)+"ª");
            String nombre = Lanzador.Menus.pedirString();
            int poblacion;
            do{
                System.out.println("Introduzca población para esa comunidad nombrada: " + nombre );
                poblacion = Lanzador.Menus.pedirEntero();
            }
            while(poblacion<1);
            coleccionComunidades.add(new Comunidad(poblacion,nombre));
        }
        
    }

    /**
     * Pide por la entrada estandar de teclado un valor entero correspondiente al número de contactos
     * Valida que E nunca sea negativo
     * @return E Valor de Contactos
     */
    public int pedirE() {
        
        
        int retorno;
       
        do{
            System.out.println("Introduzca el numero de contactos. Valor Entero Mayor que cero");
            retorno = Lanzador.Menus.pedirEntero();
            
        }while(retorno<=0);
        return retorno;
        
    }

    /**
     * Pide al usuario la probabilidad de contagio por la entrada de teclado estandar
     * Valida que 0<=p<=1
     * @return p Probababilidad de contagio valor entre [0-1]
     */
    public float pedirp() {
        float retorno=-1;
        
        do{
            System.out.println("Introduzca la probabilidad de contagio. Valores entre [0-1]");
            retorno = Lanzador.Menus.pedirFloat();
            
        }while(retorno<0||retorno>1);
        return retorno;
    }

    /**
     * Pide al usuario el número de días en los que se llevará a cabo la simulación por la entrada estandar de teclado.
     * Valida que el dato sea mayot que cero
     * @return dias que forman parte de la simulación
     */
    public int pedirDias() {
        int retorno;
        
        do{
            System.out.println("Introduzca el numero de dias a simular. Número Entero.");
            retorno = Lanzador.Menus.pedirEntero();
            if(retorno<0)
                System.out.println("Solo admite valores positivos \n Vuelva a introducir número de días válido");
        }while(retorno<0);
        return retorno;
        
    }

    

    public int getE() {
        return E;
    }

    public void setE(int E) {
        this.E = E;
    }

    public float getP() {
        return p;
    }

    public void setP(float p) {
        this.p = p;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public ArrayList<Comunidad> getColeccionComunidades() {
        return coleccionComunidades;
    }

    public void setColeccionComunidades(ArrayList<Comunidad> coleccionComunidades) {
        this.coleccionComunidades = coleccionComunidades;
    }

    public int getNumComu() {
        return numComu;
    }

    public void setNumComu(int numComu) {
        this.numComu = numComu;
    }
    
    

}