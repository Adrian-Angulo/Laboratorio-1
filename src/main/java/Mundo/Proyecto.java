/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author ADRIAN CASTILLO
 */
public class Proyecto {
 // Objeto para leer entrada del usuario
    private static ArrayList<Alumno> misAlumnos; // ArrayList para almacenar alumnos
    private static Scanner lector = new Scanner(System.in); 


    /**
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        // Inicializa el ArrayList de alumnos
        misAlumnos = new ArrayList<Alumno>();
        //lee los datos guardados en el archivo al ejecutar el programa
        leerReportes(misAlumnos);
        menu(lector, misAlumnos);
    }

    
    /**
     * metodo para agregar un alumno al ArrayList
     * @param alumno
     */
    public static void agregarAlumno(Alumno alumno, ArrayList<Alumno> misAlumnos) {
        misAlumnos.add(alumno); // Agregar alumno al ArrayList
        //Agrega el nuevo alumno sin necesidad de sobre escribir el archivo, utlizando FileWriter
        try (PrintWriter pluma = new PrintWriter(new FileWriter("./data/datos.txt", true))) { 
            pluma.println(
                alumno.getCedula() + "," 
                + alumno.getNombre() + "," 
                + alumno.getApellido() + "," 
                + alumno.getSemestre() + "," 
                + alumno.getCorreo() + ","
                + alumno.getCelular());
            pluma.close();
            System.out.println("\n" + //
                    "el estudiante a sido añadido");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    

    /**
     * Metodo que encarga buscar el alumno por medio de la cedula para eliminarlo
     * @param cedula
     */
    public static void eliminarAlumno(int cedula, ArrayList<Alumno> misAlumnos) {
        boolean alumnoEliminado = false;

        Iterator<Alumno> it = misAlumnos.iterator();
        while (it.hasNext()) {
            Alumno a = it.next();
            if (a.getCedula() == cedula) {
                it.remove();
                alumnoEliminado = true;
                break;
            }
        }
        if (alumnoEliminado) {
            System.out.println("\n" + //
                    "El alumno ha sido eliminado");
            try (PrintWriter pluma = new PrintWriter( new File("./data/datos.txt"))){
                // recorrido sobre los disco
                for (Alumno alumno : misAlumnos) {
                    pluma.println(
                            alumno.getCedula() +
                                    "," + alumno.getNombre() +
                                    "," + alumno.getApellido() +
                                    "," + alumno.getSemestre() +
                                    "," + alumno.getCorreo() +
                                    "," + alumno.getCelular());
                }
            }catch (FileNotFoundException ex) {
            System.out.println(ex);
            } 
        }else{
            System.out.println("\n" + //
                    "El alumno no se encuentra en la lista" );
        }
    }

     

    /**
     * Metodo para modificar los datos del alumno
     * @param cedula
     */
    public static void modificarAlumno(int cedula,ArrayList<Alumno> misAlumnos, Scanner lector) {
        boolean alumnoM=false; 
        for (Alumno alumno : misAlumnos) {
            // Condicion prar verificar si existe el alumno
            if (alumno.getCedula() == cedula) {
                // Mostrar opciones de modificacion
                System.out.print("""
                        ----Que dato desea modificar---
                        1. Cedula
                        2. Nombre
                        3. Apellido
                        4. Semestre
                        5. Correo
                        6. Celular
                        Digite una opcion:
                        """);
                int dato = lector.nextInt();
                switch (dato) {
                    case 1 -> {
                        // modificar cedula
                        System.out.println("---------------------------------");
                        try {
                            System.out.print("Cedula nueva: ");
                            cedula = lector.nextInt();
                            alumno.setCedula(cedula); // almacena el nuevo valor en alumno requerido
                        } catch (Exception e) {
                            System.out.println("\nError, Ingresar datos validos por favor");
                            lector.nextLine();// Limpia el bufer del scanner para evitar problemas
                        }
                    }
                    case 2 -> {
                        // Modificar el nombre
                        System.out.println("---------------------------------");
                        System.out.print("Nombre nuevo: ");
                        String nombre = lector.next();
                        alumno.setNombre(nombre);
                    }
                    case 3 -> {
                        // Modificar apellido
                        System.out.println("---------------------------------");
                        System.out.print("Apellido nuevo: ");
                        String apellido = lector.next();
                        alumno.setApellido(apellido);
                    }
                    case 4 -> {
                        // Modificar semestre
                        System.out.println("---------------------------------");
                        System.out.print("Semestre nuevo: ");
                        int semestre = lector.nextInt();
                        alumno.setSemestre(semestre);
                    }
                    case 5 -> {
                        // Modificar correo
                        System.out.println("---------------------------------");
                        System.out.println("Correo nuevo: ");
                        String correo = lector.next();
                        alumno.setCorreo(correo);
                    }
                    case 6 -> {
                        // Modificar celular
                        System.out.println("---------------------------------");
                        System.out.println("Celular nuevo: ");
                        String celular = lector.next();
                        alumno.setCelular(celular); 
                    }

                    default -> {
                        // en caso de que no digite una opcion correcta
                        System.out.println("---------------------------------");
                        System.out.println("\nla opcion es incorrecta");
                        lector.next(); // limpiar buffer
                    }
                }
                alumnoM = true;
                break; // salir del bucle despues de haber modificado el alumno

            } 

        }if(alumnoM){
            System.out.println("Modificacion exitosa");
            try (PrintWriter pluma = new PrintWriter(new File("./data/datos.txt"))){
                //Recorrdo sobre los alumnos para escribir en el archivo
                for (Alumno alumno : misAlumnos) {
                    pluma.println(
                        alumno.getCedula() +
                        "," + alumno.getNombre() +
                        "," + alumno.getApellido() +
                        "," + alumno.getSemestre() +
                        "," + alumno.getCorreo() +
                        "," + alumno.getCelular());                   
                }
            } catch (FileNotFoundException e) {
                System.out.println("\nNo se pudo crear el archivo "+e);
            }
        } else{
            System.out.println("\nEl alumno no existe"); // Mostrar mensaje si no se encuentra el alumno
        }
    }

    /**
     * Metodo para consultar y mostrar los datos de los alumnos
     */
    public static void consultarAlumnos(ArrayList<Alumno> misAlumnos) {

    // Ordenar la lista de alumnos por cédula (de menor a mayor)
        Collections.sort(misAlumnos, new Comparator<Alumno>() {
            public int compare(Alumno alumno1, Alumno alumno2) {
                return Integer.compare(alumno1.getCedula(), alumno2.getCedula());
            }
        }); 
        // condicion que comprueba si la lista esta vacia    
        if (misAlumnos.isEmpty()) {
            System.out.println("\nNo se encontraron datos");
        // En caso de que la lista no este vacia imprime todos los datos de la lista    
        } else {
            System.out.println("\n" + //
                    "--------------------------------------------------------------------------------------------------------");
            //estructura que permite guardar espacios, en este caso se usa para darle forma a la lista
            System.out.printf("| %-10s | %-15s | %-15s | %-8s | %-25s | %-12s |\n", 
                          "CEDULA", "NOMBRE", "APELLIDO", "SEMESTRE", "CORREO", "CELULAR");
            System.out.println("--------------------------------------------------------------------------------------------------------" + //
                    "");
        
            for (Alumno alumno : misAlumnos) {
                System.out.printf("| %-10s | %-15s | %-15s | %-8s | %-25s | %-12s |\n",
                alumno.getCedula(), alumno.getNombre(), alumno.getApellido(),
                alumno.getSemestre(), alumno.getCorreo(), alumno.getCelular());
                System.out.println("--------------------------------------------------------------------------------------------------------");
            }
        }
    }

    /**
     * Metodo que permite obtener reportes de los estudiantes de un semestre dado
     * @param semestre parametro que indica el semestre
     * @throws Exception excepcion en caso que ocurra algun error
     */
    public static void obtenerReportes(int semestre, ArrayList<Alumno> misAlumnos) throws Exception {
        // Variable para rastrear si se encuentra alumnos en el semestre dado
        boolean existeAlumno = false;

        // crear el archivo para escribir el reporte
        // crear pluma para escribir el archivo
        try (PrintWriter pluma = new PrintWriter( new File("./data/Reporte.txt"))){
            
            // recorrido sobre los disco
            for (Alumno alumno : misAlumnos) {
                if (alumno.getSemestre() == semestre) {
                    // escribir con la pluma la informacion del alumno en el archivo

                    pluma.println(
                            alumno.getCedula() +
                                    "," + alumno.getNombre() +
                                    "," + alumno.getApellido() +
                                    "," + alumno.getSemestre() +
                                    "," + alumno.getCorreo() +
                                    "," + alumno.getCelular());
                    existeAlumno = true; // Marcar que se encontró al menos un alumno.

                }
            }
            if (!existeAlumno) {
                //System.out.println("\nrror, no existen estudiantes registrados en el semestre " + semestre);
                throw new FileNotFoundException("\nNo hay estudiantes en el semestre especificado");
            } else {
                System.out.println("\nReporte creado con exito");
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } 
    }

    /** 
     *  metodo que se encarga de leer el contenido del archivo de texto
     * @return misAlumnos
     * @throws IOException 
     */
    public static ArrayList<Alumno> leerReportes(ArrayList<Alumno> misAlumnos) throws IOException {
        //Declaracion de objectos
        FileReader archivo;
        BufferedReader lector;
        
        try {
            archivo = new FileReader("./data/datos.txt"); // Abre el archivo para lectura
            lector = new BufferedReader(archivo); // leer archivo de manera mas eficiente
            String cadena; //variable para almacenar cada linea leida desde el archivo

            // Lee líneas del archivo hasta que se alcance el final (se devuelve null).
            while ((cadena = lector.readLine()) != null) {
                String[] campos = cadena.split(","); // Divide la línea actual en campos utilizando la coma como delimitador.
                //registrando los campos en contrado en un clase alumno
                Alumno alumno = new Alumno( 
                    Integer.parseInt(campos[0]),
                    campos[1],
                    campos[2],
                    Integer.parseInt(campos[3]),
                    campos[4],
                    campos[5]);
                misAlumnos.add(alumno); // agrega el objecto a la lista 

            }
        } catch (Exception e) {
            System.out.println("\nerror " + e.getMessage()); // Imprime un mensaje de error si ocurre una excepción durante la lectura del archivo.
        }

        return misAlumnos; // Devuelve la lista de objetos de estudiantes (misAlumnos).
    }
    /**
     * 
     */
    public static void menu(Scanner lector, ArrayList<Alumno> misAlumnos){
        boolean activo = true; // Bancera para controlar la ejecucion
        do {
             // Mostrar menu de opciones al usuario
            System.out.println();
            System.out.println("1.- Insertar alumno");
            System.out.println("2.- Eliminar alumno");
            System.out.println("3.- Modificar alumuno");
            System.out.println("4.- Consultar alumno");
            System.out.println("5.- Obtener reporte del semestre");
            System.out.println("6.- Terminar programa");
            System.out.print("Digite la opcion que desea realizar: ");
            int opc = lector.nextInt(); // Lee la opcion elegida por el usuario

            switch (opc) {
                case 1->{
                    // Insertar alumno
                    System.out.println("\n--------------Digite los datos del estudiante----------------- ");
                    try {
                        System.out.print("Cedula: ");
                        int cedula = lector.nextInt();

                        boolean alumnoExiste=false;
                        for (Alumno alumno : misAlumnos) {
                            if(alumno.getCedula()==cedula){
                                System.out.println("\n" + //
                                        "El alumno ya se encuentra registrado");
                                alumnoExiste=true;
                                break; // salir del bucle
                            }
                        }
                        if (!alumnoExiste) {
                            
                            System.out.print("Nombre: ");
                            String nombre = lector.next();

                            System.out.print("Apellido: ");
                            String apellido = lector.next();

                            System.out.print("Semestre: ");
                            int semestre = lector.nextInt();

                            System.out.print("Correo: ");
                            String correo = lector.next();

                            System.out.print("Celular: ");
                            String celular = lector.next();

                            // Intenta crear un objecto Alumno con los datos ingresados
                            Alumno nuevoAlumno = new Alumno(cedula, nombre, apellido, semestre, correo, celular);

                            // LLama al metodo agregarAlumno y pasa el objeto Alumno como parametro
                            agregarAlumno(nuevoAlumno, misAlumnos);
                        }

                    } catch (java.util.InputMismatchException e) {
                        // Excepcion en caso de entrada incorrecta
                        System.out.println("\n" + //
                                "Error, Ingresar datos validos por favor" + //
                                        "");
                        lector.nextLine();// Limpia el bufer del scanner para evitar problemas
                        
                    } catch (Exception e) {
                        System.out.println("\n" + //
                                "Error" + e.getMessage());
                    }
                }
                case 2 ->{
                    // Eliminar alumno
                    System.out.println("\n" + //
                            "-----------Eliminar Alumno------------");
                    System.out.println("Digite la cedula de alumno a eliminar: ");
                    int cedula = lector.nextInt();
                    eliminarAlumno(cedula, misAlumnos);

                }
                case 3 -> {
                    // Modificar alumno
                    System.out.println("\n" + //
                            "-----------Modificar Alumno------------");
                    System.out.print("Digite la cedula del estudiante: ");
                    int cedula = lector.nextInt();
                    modificarAlumno(cedula, misAlumnos, lector);
                }
                case 4 -> {
                    // consular alumno
                    
                    consultarAlumnos(misAlumnos);
                }
                case 5 -> {
                    try {
                        System.out.print("Digite el semestre: ");
                        int semestre = lector.nextInt();
                        obtenerReportes(semestre, misAlumnos);

                    } catch (Exception e) {

                    }
                }

                case 6 -> {
                    // Terminar el programa
                    System.out.println("\n" + //
                            "Gracias por utilizar el programa" + //
                                    "");
                    activo = false; // Se cambia la vandera para salir del bucle
                }
                default -> {
                    // en caso de que la opcion no se encuentre
                    System.out.println("\n" + //
                            "Opcion invalida" + //
                                    "");
                }            
                
            }
        } while (activo);// el programa se repite mientras que la bandera sea verdaera
    }





}
