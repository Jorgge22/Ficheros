import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fichero {
    private List<Usuario> usuarios;
    private List<Fichero> ficheros;

    public Fichero() {
        this.usuarios = new ArrayList<>();
        this.ficheros = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Fichero> getFicheros() {
        return ficheros;
    }

    public void setFicheros(List<Fichero> ficheros) {
        this.ficheros = ficheros;
    }

    public void leerFichero(String rutaFichero){
        /*
         * Si la lista está vacía creamos un fichero
         */
        if (ficheros.isEmpty()){
            try (FileWriter fileWriter = new FileWriter(rutaFichero, true)) {
                fileWriter.write("");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Borrar fichero
            //File file = new File(ruta);
            //if (file.delete()) {
            //System.out.println("Fichero eliminadoS");
            //} else
            //  System.out.println("No se ha borrado");
            //}
        } else {
            try {
                BufferedReader br = new BufferedReader(new FileReader(rutaFichero));
                String linea;

                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }

            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    public void mostrarFicheros(){

    }

    public String generarCodigo(){
        return "";
    }

    public void anyadirUsuarios(List<String> aficiones, String ruta){
        if (aficiones.isEmpty()){
            //TODO Mensaje de error
            return;
        } else {
            try (FileWriter fileWriter = new FileWriter(ruta, true)) {
                Usuario usuarioNuevo = new Usuario(generarCodigo(), aficiones);
                fileWriter.write(usuarioNuevo.toFormatoFichero() + "\n");
                usuarios.add(usuarioNuevo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void mostrarUsuarios(){}

    public void compararPareja(){}

    public void generarFicheroConcordancia(){}
}
