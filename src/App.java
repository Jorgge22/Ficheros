public class App {
    public static void main(String[] args) {
        Fichero fichero = new Fichero("");

        fichero.cargarFicheros();
        System.out.println("Ficheros en sistema: " + fichero.mostrarFicheros());

        //fichero.leerFichero("C:\\Users\\Propietario\\Documents\\2ºDAM_2026-2027\\Acceso a Datos\\Ficheros\\usuarios.txt");
    }
}