import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args)  {
        Fichero fichero = new Fichero("");

        // fichero.cargarFicheros();
        // System.out.println("Ficheros en sistema: " + fichero.mostrarFicheros());

        // List<String> aficiones = new ArrayList<>();
        // aficiones.add("P, A");
        // fichero.anyadirUsuarios(aficiones, "usu.txt");

        try {
            fichero.leerFichero("usuarios.txt");

            System.out.println("Usuarios: " + fichero.mostrarUsuarios());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //fichero.leerFichero("C:\\Users\\Propietario\\Documents\\2ºDAM_2026-2027\\Acceso a Datos\\Ficheros\\usuarios.txt");
    }
}