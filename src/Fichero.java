import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Fichero {
    private String nombreFichero;
    private List<Usuario> usuarios;
    private List<Fichero> ficheros;

    public Fichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
        this.usuarios = new ArrayList<>();
        this.ficheros = new ArrayList<>();
    }

    public String getNombreFichero() {
        return nombreFichero;
    }

    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
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

    public void cargarFicheros(){
        /**
         * Vacío la lista para evitar duplicados
         */
        ficheros.clear();

        /**
         * Le indico que los ficheros están en la raiz del proyecto
         */
        File fichero = new File(".");

        /**
         * Listo los ficheros
         */
        String[] nombreFichero = fichero.list();

        if (nombreFichero != null){
            for (String nombre : nombreFichero) {
                if (nombre.endsWith(".txt")){
                    Fichero f = new Fichero(nombre);
                    ficheros.add(f);
                }
            }
        }
    }

    public String mostrarFicheros(){
        String nombre = "";

        if (ficheros.isEmpty()) {
            return "No hay ficheros creados";
        }

        for (Fichero fichero : ficheros) {
            nombre += fichero.getNombreFichero() + "\n";
        }

        return nombre;
    }

    public String generarCodigo(){
        if (usuarios.isEmpty()){
            return "U100";
        } else {
            /**
             * Cojo el último usuario de la lista
             */
            Usuario ultimoUsuario = usuarios.get(usuarios.size()-1);
            String codigo = ultimoUsuario.getCodigo();

            /**
             * Saco la letra del código y me quedo con los números
             */
            codigo = codigo.substring(1);

            /**
             * Lo paso a int
             */
            int numeros = Integer.parseInt(codigo);

            /**
             * Le sumo 1 al código para seguir con la lista
             */
            numeros += 1;

            return "U" + numeros;
        }
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
