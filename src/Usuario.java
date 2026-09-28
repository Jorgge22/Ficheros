import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private String codigo;
    private List<String> aficiones;

    public Usuario(String codigo, List<String> aficiones) {
        this.aficiones = aficiones;
        Collections.sort(aficiones); // Ordenar alfabéticamente
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<String> getAficiones() {
        return aficiones;
    }

    public void setAficiones(List<String> aficiones) {
        this.aficiones = aficiones;
    }

    @Override
    public String toString() {
        return "(" + codigo + "{" + String.join("| ", aficiones) + "})";
    }

    public String toFormatoFichero() {
        return codigo + " " + String.join(" ", aficiones);
    }
}
