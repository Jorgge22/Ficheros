import util.GestorEntradaSalida;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args)  {
        int opcion;
        boolean salir = false;
        Fichero gestor = new Fichero("");

        while (!salir){
            GestorEntradaSalida.imprimirMensajeSeparado("BIENVENIDO");
            GestorEntradaSalida.imprimirMensajeSeparado("Estos son los ficheros que hay en el sistema: ");

            gestor.cargarFicheros();
            List<Fichero> ficheroSistema = gestor.getFicheros();
            GestorEntradaSalida.imprimirMensajeSeparado(gestor.mostrarFicheros());

            if (ficheroSistema.isEmpty()){
                GestorEntradaSalida.imprimirMensajeSeparado("No hay ficheros en el sistema.");
                GestorEntradaSalida.imprimirMensaje("¿Quieres crear un fichero? (S/N): ");
                char respuesta = GestorEntradaSalida.leerChar();

                if (respuesta == 'S' || respuesta == 's') {
                    GestorEntradaSalida.imprimirMensaje("¿Que nombre va a tener tu fichero? (terminado en .txt): ");
                    String nombreFicheroNuevo = GestorEntradaSalida.leerLinea();

                    GestorEntradaSalida.imprimirMensaje("¿Aficiones que tiene?: ");
                    String aficiones = GestorEntradaSalida.leerLinea();

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
                GestorEntradaSalida.imprimirMensaje("¿Qué fichero quieres seleccionar?: ");
                int opcionFichero = GestorEntradaSalida.leerInt();

                gestor.mostrarFicheros();


            }
        }
    }
}


switch (opcionFichero){
        case 1:
        // Añadir usuario
        GestorEntradaSalida.imprimirMensaje("¿Nombre del nuevo usuario?: ");
String nuevoNombre = GestorEntradaSalida.leerLinea();

                        GestorEntradaSalida.imprimirMensaje("¿Aficiones que tiene? (Ej: xxx xxx xxx): ");
String aficiones = GestorEntradaSalida.leerLinea();

String[] partes = aficiones.split(" ");

List<String> listaAficiones = new ArrayList<>();
                        for (String aficion : partes) {
        if (!aficion.isEmpty()){
        listaAficiones.add(aficion);
                            }
                                    }

                                    gestor.anyadirUsuarios(listaAficiones, );
                        break;

                                case 2:
                                // Mostrar usuarios
                                break;

                                case 3:
                                // Generar fichero concordancias
                                break;

                                case 4:
                                // Salir
                                break;
                                }