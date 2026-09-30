public class Pareja {
    private Usuario usuario1;
    private Usuario usuario2;
    private int numeroConcordancia;

    public Pareja(Usuario usuario1, Usuario usuario2, int numeroConcordancia) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.numeroConcordancia = numeroConcordancia;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public int getNumeroConcordancia() {
        return numeroConcordancia;
    }

    public void setNumeroConcordancia(int numeroConcordancia) {
        this.numeroConcordancia = numeroConcordancia;
    }
}
