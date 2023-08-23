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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ADRIAN CASTILLO
 */
public class Proyecto {
    //Declaracion de variable y objectos
    private static ArrayList<Alumno> misAlumnos; //ArrayList para almacenar alumnos
    private static Scanner lector = new Scanner(System.in); // Objeto para leer entrada del usuario
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        misAlumnos= new ArrayList<Alumno>(); //Inicializa el ArrayList de alumnos
        leerReportes();
    //    misAlumnos.add(new Alumno(1, "Camilo","Castillo" ,3, "adrian@101", "123"));
    //    misAlumnos.add(new Alumno(2, "Adrian","Angulo" ,1, "adf@101", "43"));
    //    misAlumnos.add(new Alumno(3, "Daniel","Quiñones" ,1, "adfsa@101", "21"));
    //    misAlumnos.add(new Alumno(4, "Mari","Landazury" ,3, "fas@101", "60"));
        
        boolean activo = true; //Bancera para controlar la ejecucion
        //Bucle principal del programa
        do {            
            // Mostrar menu de opciones al usuario
            System.out.println("--------------------------");
            System.out.println("1.- Insertar alumno");
            System.out.println("2.- Eliminar alumno");
            System.out.println("3.- Modificar alumuno");
            System.out.println("4.- Consultar alumno");
            System.out.println("5.- Obtener reporte del semestre");
            System.out.println("6.- leer reportes");
            System.out.println("7.- Terminar Programa");
            System.out.print("Digite la opcion que desea realizar: ");
            int opc = lector.nextInt(); // Lee la opcion elegida por el usuario

            switch (opc) {
                case 1 -> {
                    // Insertar alumno
                    System.out.println("--------------Digite los datos del estudiante----------------- ");
                    try {
                        System.out.print("Cedula: ");
                        int cedula = lector.nextInt();
                        

                        System.out.print("Nombre: ");
                        String nombre=lector.next();

                        System.out.print("Apellido: ");
                        String apellido= lector.next();

                        System.out.print("Semestre: ");
                        int semestre = lector.nextInt();

                        System.out.print("Correo: ");
                        String correo = lector.next();

                        System.out.print("Celular: ");
                        String celular = lector.next();

                        // Intenta crear un objecto Alumno con los datos ingresados
                        Alumno nuevoAlumno = new Alumno(cedula, nombre, apellido, semestre, correo, celular);
                        
                        // LLama al metodo agregarAlumno y pasa el objeto Alumno como parametro
                        agregarAlumno(nuevoAlumno);
                        System.out.println("Estudiante añadido correctamente.");
                        
                    } catch (java.util.InputMismatchException e) {
                        //Excepcion en caso de entrada incorrecta
                        System.out.println("Error, Ingresar datos validos por favor");
                        lector.nextLine();// Limpia el bufer del scanner para evitar problemas
                        break;
                    } catch (Exception e){
                        System.out.println("Error"+ e.getMessage());
                    }
                    
                }
                case 2 -> {
                    //Eliminar alumno
                    System.out.println("-----------Eliminar Alumno------------");
                    System.out.println("Digite la cedula de alumno a eliminar: ");
                    int cedula = lector.nextInt();
                    eliminarAlumno(cedula);

                }
                case 3 -> {
                    //Modificar alumno
                    System.out.println("-----------Modificar Alumno------------");
                    System.out.print("Digite la cedula del estudiante: ");
                    int cedula = lector.nextInt();
                    modificarAlumno(cedula);
                   
                }
                case 4 -> {
                    // consular alumno
                    //leerReportes();
                    consultarAlumnos();
                }
                case 5-> {
                    try {
                        System.out.print("Digite el semestre: ");
                        int semestre= lector.nextInt();
                        obtenerReportes(semestre);
                        
                        
                    } catch (Exception e) {
                        
                    }
                }
                
                case 6 -> {
                    // Terminar el programa
                    System.out.println("Gracias por utilizar el programa");
                    activo=false; // Se cambia la vandera para salir del bucle
                }
                default -> {
                    // en caso de que la opcion no se encuentre
                    System.out.println("Opcion invalida");
                }
            }

        } while (activo); // el programa se repite mientras que la bandera sea verdaera
            
    }
        

    //metodo para agregar un alumno al ArrayList
    public static void agregarAlumno(Alumno alumno) {
        misAlumnos.add(alumno); // Agregar alumno al ArrayList
        System.out.println("el estudiante a sido añadido");
    }

    // Metodo que encarga buscar el alumno por medio de la cedula para eliminarlo
    public static void eliminarAlumno(int cedula) {
        boolean alumnoEliminado=false;
//        for (int i = 0; i < misAlumnos.size(); i++) {
//            if(misAlumnos.get(i).getCedula()==cedula){
//                misAlumnos.remove(i); // elimina el alumno del Arraylist
//                System.out.println("El alumno a sido eliminado");
//                break;
//            }else{
//                System.out.println("El alumno no fue encontrado");
//                break;
//            }
//        }
//      creando iterador para recorrer elementos de la lista
        Iterator<Alumno> it = misAlumnos.iterator();
        while(it.hasNext()){
            Alumno a = (Alumno) it.next();
            if(a.getCedula()==cedula){
                it.remove();
                alumnoEliminado=true;
                break;
            }
        }
        if(alumnoEliminado){
            System.out.println("El alumno ha sido eliminado");
            
        }else{
            System.out.println("No se encontro el alumno a eliminar");
            
        }
            
    }

    // Metodo para modificar los datos del alumno
    public static void modificarAlumno(int cedula) {
       
        for (Alumno alumno : misAlumnos) {
            // Condicion prar verificar si existe el alumno
            if(alumno.getCedula()==cedula){
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
                            alumno.setCedula(cedula);
                        } catch (Exception e) {
                            System.out.println("Error, Ingresar datos validos por favor");
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
                        //en caso de que no digite una opcion correcta
                        System.out.println("---------------------------------");
                        System.out.println(" la opcion es incorrecta");
                        lector.next(); // limpiar buffer
                    }
                }
                break; // salir del bucle despues de haber modificado el alumno
                
            }else{
                System.out.println("El alumno no existe");
                break;
            }
            
        }
        System.out.println("Modificacion exitosa");
    }

    // Metodo para consultar y mostrar los datos de los alumnos
    public static void consultarAlumnos() {
        if(misAlumnos.isEmpty()){
            System.out.println("No se encontraron datos");
        }else{
            for (Alumno alumno : misAlumnos) {
                System.out.println("------------------------------------");
                System.out.println("Cedula: "+alumno.getCedula());
                System.out.println("Nombre: "+alumno.getNombre());
                System.out.println("Apellido: "+alumno.getApellido());
                System.out.println("Semestre: "+alumno.getSemestre());
                System.out.println("Correo: "+ alumno.getCorreo());
                System.out.println("Celular: "+ alumno.getCelular());
            }
        }
    }
    
    public static void obtenerReportes(int semestre) throws Exception{
        PrintWriter pluma = null;
        boolean existeAlumno=false;
        try {
            //crear el archivo para escribir el archivo
            File archivo = new File("./data/Reporte.txt");
            //crear pluma para escribir el archivo
            pluma = new PrintWriter(archivo);
           
             //recorrido sobre los disco
            for (Alumno alumno : misAlumnos) {
                if(alumno.getSemestre()==semestre){
                    //escribir con la pluma la informacion requerida
                    
                    pluma.println(
                                    alumno.getCedula()+
                                    ","+ alumno.getNombre()+
                                    ","+ alumno.getApellido()+
                                    ","+ alumno.getSemestre()+
                                    ","+ alumno.getCorreo()+
                                    ","+ alumno.getCelular()
                    );
                    existeAlumno = true; 
                    
                }
            }
            if(!existeAlumno){
                System.out.println("Error, no existen estudiantes registrados en el semestre "+ semestre);
                throw new FileNotFoundException("No hay estudiantes en el semestre especificado");
            }else{
                System.out.println("Reporte creado con exito");
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } finally{
            if(pluma !=null){
                pluma.close();
            }
        }
    }

    /**
     * @return misAlumnos
     * @throws IOException
     */
    public static ArrayList<Alumno> leerReportes() throws IOException{
        FileReader archivo;
        BufferedReader lector;

        try {
            archivo = new FileReader("./data/Reporte.txt");
            lector = new BufferedReader(archivo);
            String cadena;
            while ((cadena = lector.readLine())!=null) {
                String[] campos = cadena.split(",");
                Alumno alumno = new Alumno(Integer.parseInt(campos[0]), campos[1], campos[2], Integer.parseInt(campos[3]), campos[4], campos[5]);
                misAlumnos.add(alumno);

            }
        } catch (Exception e) {
            System.out.println("error "+ e.getMessage());
        } 
        
        return misAlumnos;
    }

}
