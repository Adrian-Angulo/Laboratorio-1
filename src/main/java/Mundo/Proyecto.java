/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;

import java.util.Scanner;



import java.util.ArrayList;
/**
 *
 * @author ADRIAN CASTILLO
 */
public class Proyecto {
    //Declaracion de variable y objectos
    private static ArrayList<Alumno> misAlumnos; //ArrayList para almacenar alumnos
    private static Scanner lector = new Scanner(System.in); // Objeto para leer entrada del usuario
    
    
    public static void main(String[] args) {
        misAlumnos= new ArrayList<Alumno>(); //Inicializa el ArrayList de alumnos
        misAlumnos.add(new Alumno(1, "Camilo","Castillo" ,3 , "adrian@101", "123"));
        misAlumnos.add(new Alumno(2, "Adrian","Angulo" ,3 , "adf@101", "43"));
        misAlumnos.add(new Alumno(3, "Daniel","Quiñones" ,3 , "adfsa@101", "21"));
        misAlumnos.add(new Alumno(4, "Mari","Landazury" ,3 , "fas@101", "60"));
        
        boolean activo = true; //Bancera para controlar la ejecucion
        //Bucle principal del programa
        do {            
            // Mostrar menu de opciones al usuario
            System.out.println("--------------------------");
            System.out.println("1.- Insertar alumno");
            System.out.println("2.- Eliminar alumno");
            System.out.println("3.- Modificar alumuno");
            System.out.println("4.- Consultar alumno");
            System.out.println("5.- Terminar Programa");
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
                    consultarAlumnos();
                }
                case 5 -> {
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
        for (int i = 0; i < misAlumnos.size(); i++) {
            if(misAlumnos.get(i).getCedula()==cedula){
                misAlumnos.remove(i); // elimina el alumno del Arraylist
                System.out.println("El alumno a sido eliminado");
                break;
            }else{
                System.out.println("El alumno no fue encontrado");
                break;
            }
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
                    case 1:
                        // modificar cedula
                        System.out.println("---------------------------------");
                        try {
                            System.out.print("Cedula nueva: ");
                            cedula = lector.nextInt();
                            alumno.setCedula(cedula);
                            break;
                            
                        } catch (Exception e) {
                            System.out.println("Error, Ingresar datos validos por favor");
                            lector.nextLine();// Limpia el bufer del scanner para evitar problemas
                            break;
                        }
                    case 2:
                        // Modificar el nombre
                        System.out.println("---------------------------------");
                        System.out.print("Nombre nuevo: ");
                        String nombre = lector.next();
                        alumno.setNombre(nombre);
                        break;
                    case 3:
                        // Modificar apellido
                        System.out.println("---------------------------------");
                        System.out.print("Apellido nuevo: ");
                        String apellido = lector.next();
                        alumno.setApellido(apellido);
                        break;
                    case 4:
                        // Modificar semestre
                        System.out.println("---------------------------------");
                        System.out.print("Semestre nuevo: ");
                        int semestre = lector.nextInt();
                        alumno.setSemestre(semestre);
                        break;
                    case 5:
                        // Modificar correo
                        System.out.println("---------------------------------");
                        System.out.println("Correo nuevo: ");
                        String correo = lector.next();
                        alumno.setCorreo(correo);
                        break;
                    case 6:
                        // Modificar celular
                        System.out.println("---------------------------------");
                        System.out.println("Celular nuevo: ");
                        String celular = lector.next();
                        alumno.setCelular(celular);
                        break;
                
                    default:
                        //en caso de que no digite una opcion correcta
                        System.out.println("---------------------------------");
                        System.out.println(" la opcion es incorrecta");
                        break;
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
            System.out.println("No se encuantran alumnos registrados");
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

}
