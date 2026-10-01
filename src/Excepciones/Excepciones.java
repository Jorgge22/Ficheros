package Excepciones;

public class Excepciones extends Exception{
    public static class OpcionInvalidaException extends Exception {
        public OpcionInvalidaException() {
            super("La opción seleccionada no es válida.");
        }
    }
}
