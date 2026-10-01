import util.GestorEntradaSalida;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        int opcion;
        boolean salir = false;
        Fichero gestor = new Fichero("");

        while (!salir){
            GestorEntradaSalida.imprimirMensajeSeparado("BIENVENIDO");

            //gestor.cargarFicheros();
            //List<Fichero> ficheroSistema = gestor.getFicheros();
            List<Fichero> ficheroSistema = gestor.cargarFicheros();
            //GestorEntradaSalida.imprimirMensajeSeparado(gestor.mostrarFicheros());

            if (ficheroSistema.isEmpty()){
                GestorEntradaSalida.imprimirMensajeSeparado("No hay ficheros en el sistema.");
                GestorEntradaSalida.imprimirMensaje("¿Quieres crear un fichero? (S/N): ");
                char respuesta = GestorEntradaSalida.leerChar();

                if (respuesta == 'S' || respuesta == 's') {
                    GestorEntradaSalida.imprimirMensaje("¿Que nombre va a tener tu fichero?: ");
                    String nombreFicheroNuevo = GestorEntradaSalida.leerLinea();

                    if (!nombreFicheroNuevo.endsWith(".txt")){
                        nombreFicheroNuevo += ".txt";
                    }

                    GestorEntradaSalida.imprimirMensaje("¿Aficiones que tiene?: ");
                    String aficiones = GestorEntradaSalida.leerLinea().toUpperCase();

                    String[] partes = aficiones.split(" ");

                    List<String> listaAficiones = new ArrayList<>();
                    for (String aficion : partes) {
                        if (!aficion.isEmpty()){
                            listaAficiones.add(aficion);
                        }
                    }

                    try {
                        gestor.anyadirUsuarios(listaAficiones, nombreFicheroNuevo);
                        continue;
                    } catch (Exception e) {
                        GestorEntradaSalida.imprimirMensajeSeparado(e.getMessage());
                    }
                } else {
                    salir = true;
                    GestorEntradaSalida.imprimirMensajeSeparado("HASTA LUEGO");
                    return;
                }
            } else {
                GestorEntradaSalida.imprimirMensajeSeparado("Estos son los ficheros que hay en el sistema: ");
                gestor.cargarFicheros();

                ficheroSistema = gestor.cargarFicheros();
                GestorEntradaSalida.imprimirMensajeSeparado(gestor.mostrarFicheros());

                GestorEntradaSalida.imprimirMensaje("¿Qué fichero quieres seleccionar?: ");
                int ficheroElegido = GestorEntradaSalida.leerInt();

                if (ficheroElegido < 1 || ficheroElegido > ficheroSistema.size() + 1) {
                    GestorEntradaSalida.imprimirMensajeSeparado("Opción inválida.");

                } else if (ficheroElegido == ficheroSistema.size() + 1) {
                    GestorEntradaSalida.imprimirMensaje("¿Cómo quieres que se llame tu fichero?: ");
                    String nombreFichero = GestorEntradaSalida.leerLinea();

                    if (!nombreFichero.toLowerCase().endsWith(".txt")){
                        nombreFichero += ".txt";
                    }

                    GestorEntradaSalida.imprimirMensaje("");

                    try {
                        GestorEntradaSalida.imprimirMensaje("¿Aficiones que tiene? (Ej: aficion1 aficion2 aficion3): ");
                        String aficiones = GestorEntradaSalida.leerLinea().toUpperCase();

                        String[] partes = aficiones.trim().split(" ");

                        List<String> listaAficiones = new ArrayList<>();
                        for (String aficion : partes) {
                            if (!aficion.isEmpty()) {
                                listaAficiones.add(aficion);
                            }
                        }

                        gestor.anyadirUsuarios(listaAficiones, nombreFichero);
                    } catch (Exception e) {
                        GestorEntradaSalida.imprimirMensajeSeparado(e.getMessage());
                    }
                } else {
                    Fichero ficheroSeleccionado = ficheroSistema.get(ficheroElegido - 1);
                    String nombre = ficheroSeleccionado.getNombreFichero();
                    gestor.setNombreFichero(nombre);
                    gestor.leerFichero(nombre);

                    GestorEntradaSalida.imprimirMensajeSeparado("MENU PRINCIPAl");
                    GestorEntradaSalida.imprimirMensajeSeparado("1. Añadir usuario");
                    GestorEntradaSalida.imprimirMensajeSeparado("2. Mostrar usuarios introducidos");
                    GestorEntradaSalida.imprimirMensajeSeparado("3. Generar fichero concordancias");
                    GestorEntradaSalida.imprimirMensajeSeparado("5. Salir");
                    GestorEntradaSalida.imprimirMensaje("Elige una opción: ");
                    opcion = GestorEntradaSalida.leerInt();

                    switch (opcion){
                        case 1:
                            /**
                             * Añadir nuevo usuario
                             */
                            GestorEntradaSalida.imprimirMensaje("¿Aficiones que tiene? (Ej: aficion1 aficion2 aficion3): ");
                            String aficiones = GestorEntradaSalida.leerLinea().toUpperCase();

                            String[] partes = aficiones.trim().split(" ");

                            List<String> listaAficiones = new ArrayList<>();
                            for (String aficion : partes) {
                                if (!aficion.isEmpty()) {
                                    listaAficiones.add(aficion);
                                }
                            }

                            try {
                                gestor.anyadirUsuarios(listaAficiones, nombre);
                            } catch (Exception e) {
                                GestorEntradaSalida.imprimirMensajeSeparado(e.getMessage());
                            }
                            break;

                        case 2:
                            /**
                             * Mostrar usuarios
                             */
                            GestorEntradaSalida.imprimirMensajeSeparado("Estos son los usuarios:");
                            GestorEntradaSalida.imprimirMensaje(gestor.mostrarUsuarios());
                            break;

                        case 3:
                            /**
                             * Generar fichero concordancias
                             */
                            GestorEntradaSalida.imprimirMensaje("¿Cuántas concordancias deseas?: ");
                            int numConcordancias = GestorEntradaSalida.leerInt();

                            if (ficheroSistema.isEmpty()){
                                GestorEntradaSalida.imprimirMensajeSeparado("No hay usuarios.");
                            }

                            gestor.generarFicheroConcordancia(numConcordancias, "concordancias.txt");
                            break;

                        case 5:
                            /**
                             * Salir
                             */
                            GestorEntradaSalida.imprimirMensajeSeparado("Saliendo del programa...");
                            salir = true;
                            break;
                    }
                }
            }
        }
    }
}