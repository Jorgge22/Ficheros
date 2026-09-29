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

    public void leerFichero(String rutaFichero) {
        /*
         * Si la lista de ficheros está vacía, nos aseguramos de que el archivo existe
         */
        if (ficheros.isEmpty()) {
            try (FileWriter fileWriter = new FileWriter(rutaFichero, true)) {
                fileWriter.write("");
            } catch (IOException e) {
                throw new RuntimeException("Error al verificar/crear el fichero: " + e.getMessage());
            }
        }

        /*
         * Vaciamos la lista en memoria antes de leer para evitar duplicados
         */
        this.usuarios.clear();

        /*
         * Leemos el archivo y convertimos cada línea en un objeto Usuario
         */
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                /**
                 * Ignoramos líneas vacías
                 */
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(" ");
                List<String> aficiones = new ArrayList<>();

                for (int i = 1; i < partes.length; i++) {
                    aficiones.add(partes[i]);
                }

                /**
                 * Creamos el usuario y lo añadimos a la lista en memoria
                 */
                Usuario usuario = new Usuario(partes[0], aficiones);
                this.usuarios.add(usuario);
            }

        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + e.getMessage());
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

    public String mostrarUsuarios() throws Exception {
        String resultado = "";

        if (usuarios.isEmpty()){
            throw new Exception("No hay usuarios en la lista.");
        } else {
            for (Usuario u : usuarios) {
                resultado += u.toFormatoFichero() + "\n";
            }
        }
        return resultado;
    }

    public void compararPareja(){}

    public void generarFicheroConcordancia(){}
}
