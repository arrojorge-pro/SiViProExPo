package com.arrojomacias.iis_simuladorvirico;
import java.util.*;

/**
 * @author Jorge Arrojo
 */
public class Simulacion {
    
    /**
     * Almacena la instancia creada de simulacion para su posterior recuperacion
     */
    static ArrayList <Simulacion> simulaciones = new ArrayList<>();

    /**
     * Construye el objeto simulación y a la vez realiza la simulación a través de las operaciones propias de esta clase.
     * Finaliza añadiendo el objeto creado a la colección simulaciones para su posterior recuperacion.
     * Default constructor
     */
    public Simulacion() {
        
        this.Nd[0][0]=1;
        this.calcularNd();
        this.calcularNv();
        this.calcularTotalInfectados();
        this.calcularTotalPorcentajeInfectados();
        this.porComu = this.calcPorcentInfcPorComunidad();
        simulaciones.add(this);
        
    }

    /**
     * 
     */
    
    /**
     * Matriz bidimensional que represanta una tabla cuyos campos guarda el número de infectados en un día en cada comunidad. Filas Dias. Columnas Comunidades.
     */
    private float [][] Nd = new float[Lanzador.getColeccion().get(0).getColeccionComunidades().size()][Lanzador.getColeccion().get(0).getDias()];//array tabla cuyos campos son infectados(fila)en cadaComunidad(columna)

    /**
     * Matriz unidimensional que guarda los datos de los infectados a causa de los viajeros entre comunidades
     */
    private float [] Nv= new float[Lanzador.getColeccion().get(0).getDias()];;

    /**
     * Tipo primitivo que guarda el total de infectados. Sumatorio de Nd+Nv
     */
    private float totalInfectados;
    
    /**
     * Rescatamos la referencia de la coleccion de la clase Lanzador donde guardamos el objeto que almacenaba el objeto con los datos introducidos por el usuario
     */
    private IntroduccionDatos datos = Lanzador.getColeccion().get(0);

    /**
     * Rescatamos la referencia de la coleccion de objetos tipo comunidad, ubicada en la clase Lanzador que almacena la instancia de la clase Simulacion que lleva a cabo sus operaciones
     */
    private ArrayList <Comunidad> comunidades = Lanzador.getColeccion().get(0).getColeccionComunidades();

    /**
     * Tipo primitivo que guarda el porcentaje de infectados en función de la suma total de toda la poblacion de todas las comunidades
     */
    private float totalPorcentInfectados;
    
    /**
     * Matriz unidimensional que guarda el porcentaje de infectados en cada comunidad
     */
    private float [] porcentInfectadosPorComunidad=new float[datos.getNumComu()];

    private float [] porComu = new float[datos.getNumComu()];

    /**
     * Calcula el numero de infectados del dia siguiente según los coeficientes introducidos por el usuario, partiendo de que existe una persona infectada en una comunidad
     */
    public void calcularNd() {//Numero de infectados para una comunidad
        float calculo;
        for(int i = 0; i<comunidades.size();i++){
          for(int j = 1; j<datos.getDias();j++){  
            calculo = this.Nd[i][j-1] * (1 + datos.getE()*datos.getP());
            this.Nd[i][j]=calculo; //hay un numero de infectados (calculo) en el día j, en la comunidad número i
          }
        }
    }
        
    /**
     * Calcula el número de infectados en función del porcentaje de viajeros estipulado por el usuario
     */
    public void calcularNv() {
        //calculame de cada comunidad la cuantía que representa el porcentaje de viajeros
        float [] arrayPorcentPobla = new float [comunidades.size()];
        
        for (Comunidad var: comunidades){
            for(int i=0;i<comunidades.size();i++){
            arrayPorcentPobla[i]=Comunidad.getV()*var.getPoblacion();
            }
        }// ya tenemos el porcentaje de cada poblacion guardada en el array
        
        //aplica la fórmula a esa cantidad en cada comunidad
        float calculo;
        for (int a=0;a<comunidades.size();a++){
                calculo= datos.getE()*datos.getP()*arrayPorcentPobla[a]*Nd[a][a+1]/comunidades.get(a).getPoblacion();
                this.Nv[a]=calculo; //Tenemos el numero de infectados por culpa de los viajeros en Nv
        }
    }
    
    /**
     * Calcula el porcentaje total de infectados en funcion de la población total de todas las comunidades
     * Guarda ese dato en el atributo o campo de clase totalPorcentInfectados
     */
    public void calcularTotalPorcentajeInfectados() {
        // TODO implement here
        int totalPoblacion=0;
        for (int i = 0; i<comunidades.size();i++){
            totalPoblacion = totalPoblacion + comunidades.get(i).getPoblacion();
        }
        
        float calculo;
        calculo = this.totalInfectados / totalPoblacion;
        this.totalPorcentInfectados = calculo * 100;
    }

    /**
     * 
     * Calcula el porcentaje de infectados en cada comunidad en función de su población y lo almacena de una matriz unidimensional
     * @return Array unidimensional con los porcentajes de infectados por cada comunidad
     */
    public float[] calcPorcentInfcPorComunidad() {
        float sumafilas=0;
        
        for(int i = 0; i<comunidades.size();i++){
          for(int j = 1; j<datos.getDias();j++){  
              sumafilas+=this.Nd[i][j];//almacenamos en variable el numero total de infectados en todos los días
              porcentInfectadosPorComunidad[i]=sumafilas/comunidades.get(i).getPoblacion();//hallamos el porcentaje dividiendo total infectados todos los días entre la poblacion de cada comunidad  
          }
        }
        
        return porcentInfectadosPorComunidad;
       
    }

    /**
     * Calcula el número total de infectados como el sumatorio de todos los campos de Nd más todos los valores de Nv.
     * Almacena el resultado en atributo de clase para tal efecto
     */
    public void calcularTotalInfectados() {
        float totalNd=0;
        float totalNv=0;
        float total;
        //Sumame todos los valores de Nd de todos los dias de todas las comunidades y sumame el numero de infectados por viajeros(NV)
        for(int i = 0; i<comunidades.size();i++){
          for(int j = 0; j<datos.getDias();j++){  
              totalNd+=this.Nd[i][j];
          }
          totalNv+=this.Nv[i];
        }
        total=totalNd+totalNv;
        this.totalInfectados = total;
        
    }

    /*
    Setters y Getters
    */
    public float[] getNv() {
        return Nv;
    }

    public void setNv(float[] Nv) {
        this.Nv = Nv;
    }

    public float getTotalInfectados() {
        return totalInfectados;
    }

    public void setTotalInfectados(float totalInfectados) {
        this.totalInfectados = totalInfectados;
    }

    public IntroduccionDatos getDatos() {
        return datos;
    }

    public void setDatos(IntroduccionDatos datos) {
        this.datos = datos;
    }

    public ArrayList<Comunidad> getComunidades() {
        return comunidades;
    }

    public void setComunidades(ArrayList<Comunidad> comunidades) {
        this.comunidades = comunidades;
    }

    public float getTotalPorcentInfectados() {
        return totalPorcentInfectados;
    }

    public void setTotalPorcentInfectados(float totalPorcentInfectados) {
        this.totalPorcentInfectados = totalPorcentInfectados;
    }

    public float[] getPorcentInfectadosPorComunidad() {
        return porcentInfectadosPorComunidad;
    }

    public void setPorcentInfectadosPorComunidad(float[] porcentInfectadosPorComunidad) {
        this.porcentInfectadosPorComunidad = porcentInfectadosPorComunidad;
    }

    public float[] getPorComu() {
        return porComu;
    }

    public void setPorComu(float[] porComu) {
        this.porComu = porComu;
    }

    public ArrayList<Simulacion> getSimulaciones() {
        return simulaciones;
    }

    public void setSimulaciones(ArrayList<Simulacion> simulaciones) {
        this.simulaciones = simulaciones;
    }

    public float[][] getNd() {
        return Nd;
    }

    public void setNd(float[][] Nd) {
        this.Nd = Nd;
    }
    

}