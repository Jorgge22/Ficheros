package util;

import java.util.Scanner;

public class GestorEntradaSalida {
    private static final Scanner scanner = new Scanner(System.in);

    private GestorEntradaSalida() {}

    public static void imprimirMensajeSeparado(String mensaje) {
        System.out.println(mensaje);
    }

    public static void imprimirMensajeSeparadoInt(int numero) {
        System.out.println(numero);
    }

    public static void imprimirMensaje(String mensaje) {
        System.out.print(mensaje);
    }

    public static void imprimirMensajeConFormato(String formato) {
        System.out.printf(formato); // Usa printf para formatos complejos
    }

    public static String leerLinea() {
        return scanner.nextLine().trim();
    }

    public static int leerInt() {
        int numero = scanner.nextInt();  // Leer el número
        scanner.nextLine();  // Limpiar el salto de línea residual
        return numero;
    }


    public static double leerDouble() {
        double numero = scanner.nextDouble();
        scanner.nextLine();
        return numero;
    }

    public static char leerChar() {
        String entrada = leerLinea();
        while (entrada.isEmpty()) {
            entrada = leerLinea();
        }
        return entrada.charAt(0);
    }

    public static void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
