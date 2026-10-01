import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.GestorEntradaSalida;

public class App {
    public static void main(String[] args) throws Exception {
        int opcion;
        boolean salir = false;
        Fichero gestor = new Fichero("");

        while (!salir) {
            GestorEntradaSalida.imprimirMensajeSeparado("BIENVENIDO");

            /**
             * Cargo la lista de ficheros que ya existen en el directorio para saber si el sistema está vacío o si ya tenemos datos.
             */
            List<Fichero> ficheroSistema = gestor.cargarFicheros();

            if (ficheroSistema.isEmpty()) {
                GestorEntradaSalida.imprimirMensajeSeparado("No hay ficheros en el sistema.");
                GestorEntradaSalida.imprimirMensaje("¿Quieres crear un fichero? (S/N): ");
                char respuesta = GestorEntradaSalida.leerChar();

                if (respuesta == 'S' || respuesta == 's') {
                    GestorEntradaSalida.imprimirMensaje("¿Que nombre va a tener tu fichero?: ");
                    String nombreFicheroNuevo = GestorEntradaSalida.leerLinea();

                    if (!nombreFicheroNuevo.endsWith(".txt")) {
                        nombreFicheroNuevo += ".txt";
                    }

                    GestorEntradaSalida.imprimirMensaje("¿Aficiones que tiene?: ");
                    String aficiones = GestorEntradaSalida.leerLinea().toUpperCase();

                    /**
                     * /* Separo la cadena de entrada por espacios.
                     */
                    String[] partes = aficiones.split(" ");

                    List<String> listaAficiones = new ArrayList<>();
                    for (String aficion : partes) {
                        if (!aficion.isEmpty()) {
                            listaAficiones.add(aficion);
                        }
                    }

                    /**
                     * Control para evitar que se añada un usuario sin aficiones
                     */
                    if (listaAficiones.isEmpty()) {
                        GestorEntradaSalida
                                .imprimirMensajeSeparado("Error: No se puede añadir un usuario sin aficiones.");
                        continue; // Vuelve al inicio del bucle
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

                /**
                 * Control para evitar algún error en los parámetros de entrada.
                 */
                if (ficheroElegido < 1 || ficheroElegido > ficheroSistema.size() + 1) {
                    GestorEntradaSalida.imprimirMensajeSeparado("Error en los parámetros de entrada: Opción inválida.");

                } else if (ficheroElegido == ficheroSistema.size() + 1) {
                    GestorEntradaSalida.imprimirMensaje("¿Cómo quieres que se llame tu fichero?: ");
                    String nombreFichero = GestorEntradaSalida.leerLinea();

                    if (!nombreFichero.toLowerCase().endsWith(".txt")) {
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

                        if (listaAficiones.isEmpty()) {
                            GestorEntradaSalida
                                    .imprimirMensajeSeparado("Error: No se puede añadir un usuario sin aficiones.");
                        } else {
                            gestor.anyadirUsuarios(listaAficiones, nombreFichero);
                        }
                    } catch (Exception e) {
                        GestorEntradaSalida.imprimirMensajeSeparado(e.getMessage());
                    }
                } else {
                    Fichero ficheroSeleccionado = ficheroSistema.get(ficheroElegido - 1);
                    String nombre = ficheroSeleccionado.getNombreFichero();

                    File archivoSeleccionado = new File(nombre);

                    /**
                     * Control, el fichero de entrada no se puede leer.
                     */
                    if (!archivoSeleccionado.exists() || !archivoSeleccionado.canRead()) {
                        GestorEntradaSalida.imprimirMensajeSeparado("Error: El fichero de entrada no se puede leer.");
                        continue; // Vuelve a pedir qué hacer
                    }

                    /**
                     * Control, fichero de entrada tiene un tamaño superior a 10000 bytes.
                     */
                    if (archivoSeleccionado.length() > 10000) {
                        GestorEntradaSalida.imprimirMensajeSeparado(
                                "Error: El fichero de entrada tiene un tamaño superior a 10000 bytes.");
                        continue;
                    }

                    gestor.setNombreFichero(nombre);

                    try {
                        gestor.leerFichero(nombre);
                    } catch (Exception e) {
                        GestorEntradaSalida.imprimirMensajeSeparado("Error: El fichero de entrada no se puede leer.");
                        continue;
                    }

                    boolean salirMenu = false;

                    while (!salirMenu) {
                        GestorEntradaSalida.imprimirMensajeSeparado("MENU PRINCIPAL");
                        GestorEntradaSalida.imprimirMensajeSeparado("1. Añadir usuario");
                        GestorEntradaSalida.imprimirMensajeSeparado("2. Mostrar usuarios introducidos");
                        GestorEntradaSalida.imprimirMensajeSeparado("3. Generar fichero concordancias");
                        GestorEntradaSalida.imprimirMensajeSeparado("5. Salir");
                        GestorEntradaSalida.imprimirMensaje("Elige una opción: ");
                        opcion = GestorEntradaSalida.leerInt();

                        switch (opcion) {
                            case 1 -> {
                                /**
                                 * Añadir nuevo usuario
                                 */
                                GestorEntradaSalida
                                        .imprimirMensaje("¿Aficiones que tiene? (Ej: aficion1 aficion2 aficion3): ");
                                String aficiones = GestorEntradaSalida.leerLinea().toUpperCase();

                                String[] partes = aficiones.trim().split(" ");

                                List<String> listaAficiones = new ArrayList<>();
                                for (String aficion : partes) {
                                    if (!aficion.isEmpty()) {
                                        listaAficiones.add(aficion);
                                    }
                                }

                                if (listaAficiones.isEmpty()) {
                                    GestorEntradaSalida.imprimirMensajeSeparado(
                                            "Error: No se puede añadir un usuario sin aficiones.");
                                } else {
                                    try {
                                        Collections.sort(listaAficiones);
                                        gestor.anyadirUsuarios(listaAficiones, nombre);
                                    } catch (Exception e) {
                                        GestorEntradaSalida.imprimirMensajeSeparado(e.getMessage());
                                    }
                                }
                            }

                            case 2 -> {
                                /**
                                 * Mostrar usuarios
                                 */
                                GestorEntradaSalida.imprimirMensajeSeparado("Estos son los usuarios:");
                                GestorEntradaSalida.imprimirMensaje(gestor.mostrarUsuarios());
                            }

                            case 3 -> {
                                /**
                                 * Generar fichero concordancias
                                 */
                                GestorEntradaSalida.imprimirMensaje("¿Cuántas concordancias deseas?: ");
                                int numConcordancias = GestorEntradaSalida.leerInt();

                                if (ficheroSistema.isEmpty()) {
                                    GestorEntradaSalida.imprimirMensajeSeparado("No hay usuarios.");
                                } else if (numConcordancias <= 0) {
                                    GestorEntradaSalida.imprimirMensajeSeparado(
                                            "Error en los parámetros de entrada: El número debe ser mayor que 0.");
                                } else {
                                    try {
                                        int parejas = gestor.generarFicheroConcordancia(numConcordancias, "concordancias.txt");

                                        if (parejas > 0) {
                                            GestorEntradaSalida.imprimirMensajeSeparado("Fichero generado con éxito. Parejas encontradas: " + parejas);
                                        } else {
                                            GestorEntradaSalida.imprimirMensajeSeparado("No se han encontrado parejas con ese número de concordancias. No se ha creado el fichero.");
                                        }
                                    } catch (Exception e) {
                                        /**
                                         * Control, no se puede crear el fichero de salida.
                                         */
                                        GestorEntradaSalida.imprimirMensajeSeparado(
                                                "Error: No se puede crear el fichero de salida.");
                                    }
                                }
                            }

                            case 5 -> {
                                /**
                                 * Salir
                                 */
                                GestorEntradaSalida.imprimirMensajeSeparado("Saliendo del programa...");
                                salirMenu = true;
                                salir = true;
                            }

                            default -> {
                                GestorEntradaSalida.imprimirMensajeSeparado(
                                        "Error en los parámetros de entrada: Opción no válida.");
                            }
                        }
                    }
                }
            }
        }
    }
}