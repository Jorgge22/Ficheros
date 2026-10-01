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

    @Override
    public String toString() {
        return "====================================================\n" +
                "  PAREJA DE CONCORDANCIA\n" +
                "====================================================\n" +
                "  • Usuario 1     : " + usuario1.getCodigo() + " " + usuario1.getAficiones() + "\n" +
                "  • Usuario 2     : " + usuario2.getCodigo() + " " + usuario2.getAficiones() + "\n" +
                "  • Coincidencias : " + numeroConcordancia + " afición(es) en común\n" +
                "----------------------------------------------------";
    }
}
