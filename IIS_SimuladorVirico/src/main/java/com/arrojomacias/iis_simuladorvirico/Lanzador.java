
package com.arrojomacias.iis_simuladorvirico;
import java.util.*;

/**
 * Clase Principal. Contiene método main
 * @author Jorge Arrojo
 */
public class Lanzador {
    
    static ArrayList <IntroduccionDatos> coleccion = new ArrayList<>();
    static ArrayList <Simulacion> simu = Simulacion.simulaciones;

   public static void main (String []args){
       
       ejecutarMenuInicial();
       UI_Intermedio();
       UI_Resultados();
       
    }
   /**
    * Muestra y gestiona el Menu Intermedio entre la Introducción de Datos y la Simulación
    */
    public static void UI_Intermedio() {
        int opcion= 0; 
        do{
            opcion= Lanzador.Menus.mostrarMenuSimulacion();
            Menus.gestorMenuIntermedio(opcion);
        }while(opcion!=3);
    }
    
    /**
     * Muestra y gestiona el menu final despues de la Simulacion. Muestra Resultados. Permite nueva simulación. Permite la salida del programa.
     */
    public static void UI_Resultados() {
        int opcion= 0; 
        do{
            opcion= Lanzador.MostrarResultados.menuResultados();
            MostrarResultados.gestorResultados(opcion);
        }while(opcion!=5);
    }

    /**
     * Muestra y gestiona el Menu inicial. Da paso a la introducción de Datos y permite la salida del programa.
     */
    public static void ejecutarMenuInicial() {
       int opcion=2;
       do{
        Menus.mostrarMenuInicial();
        System.out.println("Elija Opcion");
        opcion = Menus.selectorOpcion();
        if (opcion==1){
            
            //arrancamos la introduccion de datos
            IntroduccionDatos datos = new IntroduccionDatos();
            coleccion.add(datos);
            
        }else if (opcion==2){
            System.exit(0);
        }else{
            System.out.println("Valor introducido incorrecto");
        }
       } 
       while(opcion<1||opcion>2);
    }
    
     /*
        Setter y Getter
     */
    public static ArrayList<IntroduccionDatos> getColeccion() {
        return coleccion;
    }

    public static void setColeccion(ArrayList<IntroduccionDatos> coleccion) {
        Lanzador.coleccion = coleccion;
    }
    
    /*clase MENU Interna stática estáticas**********************************************/
                            /**
                             * Clase que gestiona el Menu Inicial y el Menu intermedio
                             * También gestiona la petición de valores Enteros y de Coma Flotante
                             * @author Jorge Arrojo
                             */
                            static class Menus {


                                /**
                                 * 1)Introducir datos para Simulación 
                                 * 2)Salir
                                 * 
                                 */
                                public static void mostrarMenuInicial() {
                                    System.out.println("PROGRAMA DE SIMULACIÓN EXPONENCIAL DE UN VIRUS EN DIFERENTES POBLACIONES");
                                    int opcion=2;

                                        System.out.println("Elija una opción para continuar");
                                        System.out.println("**********MENÚ INICIAL************");
                                        System.out.println("1.- Introducir coeficientes de la simulación");
                                        System.out.println("2.- Salir");
                                        System.out.println("**************FIN MENU************");
                                }
                                /**
                                 * Pide por la entrada de teclado estandar, un número entero.
                                 * @return valor entero que sirve como selector de opcion de un menu 
                                 */
                                public static int selectorOpcion(){
                                    java.util.Scanner sc = new java.util.Scanner(System.in);
                                    Integer opcion=0;
                                    boolean error=true;
                                    //manejar excepciones
                                    do{
                                        try{
                                            
                                            opcion = sc.nextInt();
                                            error=false;
                                        }catch(Exception e){
                                            System.out.println("Ha ocurrido un error: "+e.getMessage()+" \n Introduzca un número entero positivo de acuerdo con las opciones del menú");
                                            sc.nextLine();
                                        }
                                    }while(error);
                                    return opcion;
                                }
                                /**
                                 * Pide por la entrada de teclado estandar, un número entero.
                                 * @return valor Entero que introduce el usuario por teclado
                                 */  
                                public static int pedirEntero(){
                                    java.util.Scanner sc = new java.util.Scanner(System.in);
                                    int opcion=-1;
                                    boolean error=true;
                                    //manejar excepciones
                                    do{
                                        try{
                                            
                                            opcion = sc.nextInt();
                                            error=false;
                                        }catch(Exception e){
                                           System.out.println("Ha ocurrido un error: "+e.getMessage()+" \n Introduzca un número entero positivo");
                                           sc.nextLine();
                                        }
                                    }while(error);
                                    return opcion;   
                                }
                                /**
                                 * Pide un valor decimal al usario por la entrada de teclado estandar
                                 * @return Valor en Coma Flotante introducido por el usuario 
                                 */
                                public static float pedirFloat(){
                                    java.util.Scanner sc = new java.util.Scanner(System.in);
                                    //manejar excepciones
                                    float opcion=-1;
                                    boolean error=true;
                                    do{
                                        try{
                                            opcion = sc.nextFloat();
                                            error=false;
                                        }catch(Exception e){
                                             System.out.println("Ha ocurrido un error: "+ e.getMessage()+"\n Introduzca un número. Puede incluir parte decimal simbolizada con una coma ','");
                                             sc.nextLine();
                                        }
                                    }while(error);
                                     return opcion;
                                }
                                /**
                                 * Pide un literal de texto al usario por la entrada de teclado estandar
                                 * @return Valor de Cadena introducido por el usuario 
                                 */
                                public static String pedirString(){
                                    java.util.Scanner sc = new java.util.Scanner(System.in);
                                    
                                    String opcion;
                                    opcion = sc.nextLine();
                                    return opcion;
                                }

                                /**
                                 * Forma parte de una opción del Menu intermedio. Muestra los datos que han sido introducidos con anterioridad.
                                 */
                                private static void mostrarDatos() {
                                    IntroduccionDatos d = coleccion.get(0);
                                    int e = d.getE();
                                    int dias = d.getDias();
                                    float p = d.getP()*100;
                                    String[] nombre = new String[d.coleccionComunidades.size()];
                                    float viajeros= Comunidad.getV();
                                    int[] poblacion = new int [d.coleccionComunidades.size()];

                                    for(int i=0;i<d.coleccionComunidades.size();i++){
                                     nombre[i]=d.coleccionComunidades.get(i).getNombre();
                                     poblacion[i]=d.coleccionComunidades.get(i).getPoblacion();
                                    }

                                    System.out.println("El número de contactos es: "+ e);
                                    System.out.println("El número de días a simular es "+ dias);
                                    System.out.println("La probabilidad de contagio es "+p);
                                    System.out.println("El numero de viajeros entre provincias es "+viajeros);
                                    System.out.println("La comunidades son "+ d.getNumComu()+" y son las siguientes \n ----");
                                    for(int i=0;i<nombre.length;i++){
                                        System.out.println("Nombre: "+ nombre[i]);
                                        System.out.println("Poblacion: "+ poblacion[i]);
                                        System.out.println("----");
                                    }
                                
                            }

                            /**
                             * Menu simulacion con las siguientes opciones
                             * 1)Simular 
                             * 2)verDatosGuardados 
                             * 3)VolverInstroducirDatos
                             * Muestra menu y lanza el selector de opcion que da la posibilidad al usuario de introducir el valor del menu que desee
                             * @return devuelve la opcion escogida por el usuario
                             */
                            public static int mostrarMenuSimulacion() {
                                int opcion=0;
                                do{
                                    System.out.println("*********Datos Guardados!*************");
                                    System.out.println("Elija opción:");
                                    System.out.println("1.- Ver datos guardados");
                                    System.out.println("2.- Volver a introducir los datos");
                                    System.out.println("3.- + Comenzar SIMULACIÓN +");
                                    System.out.println("*********FIN DEL MENU**********************");
                                    System.out.println("");
                                    System.out.println("Elija Opcion: ");
                                    opcion = selectorOpcion();
                                }while(opcion<1||opcion>3);
                                return opcion;
                            }
                            
                            
                            /**
                             * Gestiona El menu intermedio
                             * @param Un número entero que consiste en la elección del número de opcion del menuIntermedio
                             */
                            public static void gestorMenuIntermedio(int opcion){
                                switch(opcion){
                               
                                    case 1://VerDatosGuardados 
                                        mostrarDatos();
                                        break;
                                    case 2://Volver a introducir los datos
                                        coleccion.remove(coleccion.get(0));
                                        Lanzador.main(new String[0]);
                                        break;
                                    case 3://Comenzar la simulacion
                                        Simulacion simula=new Simulacion();
                                        break;
                                    default: mostrarMenuSimulacion();
                                        break;
                                }
                            }
                         }//Fin Clase anidada Menu
 
 /* Clase estatica MENU RESULTADOS ***********************************/ 
                        static class MostrarResultados {

                          
                                /**
                                 * Muestra el Menú Resultados una vez que la Simulación se ha llevado a cabo con las siguientes opciones:
                                 * 1)Mostrar número total infectados
                                 * 2)Mostrar porcentaje total de infectados
                                 * 3)Mostrar porcentaje infectados por cada comunidad
                                 * 4)Realizar nueva Simulación con nuevos datos
                                 * 5)Salir de programa
                                 * @return delvuelve la entrada por teclado que ha elegido el usuario para su posterior gestion
                                 */
                                public static int menuResultados() {
                                    System.out.println("*********SIMULACION COMPLETADA!!"
                                            + "\n MOSTRAR RESULTADOS*************");
                                    System.out.println("Elija resultado a mostrar:");
                                    System.out.println("1.- Ver número total de infectados");
                                    System.out.println("2.- Ver porcentaje de infectados totales respecto al total de la población");
                                    System.out.println("3.- Ver porcentaje de infectados por comunidad-población");
                                    System.out.println("4.- Comenzar una nueva simlación");
                                    System.out.println("5.- Salir");
                                    System.out.println("*********FIN DEL MENU**********************");
                                    
                                    return Menus.selectorOpcion();

                                }
                                
                                /**
                                 * Gestor del Menu Resultados
                                 * @param Valor que introdujo el usuario por la entrada de teclado estandar
                                 */
                                public static void gestorResultados(int opcion){
                                switch(opcion){
                               
                                    case 1://VerDatosGuardados 
                                        MostrarTotalInfectados();
                                        break;
                                    case 2://Volver a introducir los datos
                                        MostrarPorcentajeTotalInfectados();
                                        break;
                                    case 3://Comenzar la simulacion
                                        MostrarPorcentajeInfectadosPorComunidad();
                                        break;
                                    case 4://Nueva Simulacion
                                        coleccion.remove(coleccion.get(0));
                                        simu.remove(simu.get(0));
                                        System.gc();
                                        Lanzador.main(new String[0]);
                                        break;
                                    case 5: System.exit(0);
                                        break;
                                    default: UI_Resultados();
                                        break;
                                    }
                                }
                                
                                /**
                                 * Muestra el número total de infectados. Es llamado por el Menu de Resultados
                                 */
                                public static void MostrarTotalInfectados(){
                                    
                                    System.out.println("Numero Total de Infectado en todas la comunidades: "+ simu.get(0).getTotalInfectados() );
                                                                        
                                }
                                
                                /**
                                 * Muestra el porcentaje de infectados en función de la población total de todas las Comunidades
                                 */
                                public static void MostrarPorcentajeTotalInfectados(){
                                    
                                    System.out.println("Porcentaje de infectados totales respecto a la poblacion total: "+ simu.get(0).getTotalPorcentInfectados()+"%");
                                    
                                }
                                
                                /**
                                 * Muestra el porcentaje de Infectados de cada comunidad
                                 */
                                public static void MostrarPorcentajeInfectadosPorComunidad(){
                                    IntroduccionDatos d = coleccion.get(0);
                                    float [] array = simu.get(0).getPorcentInfectadosPorComunidad();
                                    for (int i=0; i<array.length;i++){
                                        System.out.println("La comunidad número "+(i+1)+ " de nombre " +d.coleccionComunidades.get(i).getNombre()+" tiene "+ array[i] + "% infectados");
                                    }
                                    
                                }

                        }//fin Class anidada MostrarResultados

}//fin lanzador