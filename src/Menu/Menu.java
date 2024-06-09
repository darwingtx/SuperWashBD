package Menu;

import DataBaseConnection.ConnectionBD;
import DataBaseConnection.MariadbConnection;
import DataBaseConnection.OracleConnection;
import DataSchema.Cliente;
import DataSchema.Lavado;
import DataSchema.*;
import java.util.Scanner;

public class Menu {

    private static ConnectionBD dbConnection;
    public static void menu(boolean UseConection) {
        final String[] opts = {
                "Ingresar cliente",
                "Ingresar Vehiculo",
                "Ingresar Registro de Lavado ",
                "Listar Registros de Lavado ",
                "Listar clientes",
                "Salir"
        };

        if (UseConection)
            dbConnection = new MariadbConnection("192.168.128.12", 3306, "PruebasU", "admin", "andresUser");
        else
            dbConnection = new OracleConnection("127.0.0.1", 1521, "PruebasU", "C##LAU", "hola123");

        dbConnection.connect();

        while (true) {

            switch (menuOpciones(opts)) {
                case 4:
                    System.exit(0);
                    break;

                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                default:
                    break;
            }
        }
    }


    public static Cliente obtenerCliente() {
        Cliente c;
        String[] tipo_cliente = dbConnection.tipoCliente();
        c = new Cliente(input("id cliente"), menuOpciones(tipo_cliente), input("nombre"), input("apellido"), input("Telefono"), input("descuento"));
        return c;
    }


    public static Vehiculo obtenerVehiculo() {
        Vehiculo v;
        String[] marca = {"Mercedez ven10", "whatsapp", "taxi"};
        v = new Vehiculo(input("Id vehiculo"), input("id cliente"), menuOpcionesString(marca), inputInt("Tipo_vehiculo"));
        return v;
    }

    public static Lavado obtenerLavado() {
        Lavado l;

        l = new Lavado(input(null), input("id vehiculo"), inputInt(null), inputInt(null), input("Precio"), input("Duracion"));
        return l;
    }

    public static int menuOpciones(String[] opts) {
        boolean pass = false;
        int opt;
        Scanner r = new Scanner(System.in);
        do {
            for (int i = 0; i < opts.length; i++) {
                System.out.println((i + 1) + ". " + opts[i]);
            }
            System.out.print("Escriba su opcion: ");
            opt = r.nextInt();
            if (opt >= 0 && opt < opts.length) pass = true;

        } while (!pass);
        r.close();
        return opt;
    }


    public static String menuOpcionesString(String[] opts) {
        boolean pass = false;
        int opt;
        Scanner r = new Scanner(System.in);
        do {
            for (int i = 0; i < opts.length; i++) {
                System.out.println((i + 1) + ". " + opts[i]);
            }
            System.out.print("Escriba su opcion: ");
            opt = r.nextInt();
            if (opt >= 0 && opt < opts.length) pass = true;

        } while (!pass);
        r.close();
        return opts[opt];
    }

    public static String input(String atr) {
        Scanner r = new Scanner(System.in);
        System.out.println("Ingrese " + atr + ": ");
        String d = r.nextLine();
        r.close();
        return d;
    }

    public static int inputInt(String atr) {
        Scanner r = new Scanner(System.in);
        System.out.println("Ingrese " + atr + ": ");
        int d = r.nextInt();
        r.close();
        return d;
    }


}
