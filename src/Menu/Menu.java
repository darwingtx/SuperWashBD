package Menu;

import DataSchema.Cliente;
import DataSchema.Lavado;
import DataSchema.*;


import java.util.Scanner;

public class Menu {
    public static void menu() {

        final String[] opts = {
                "Ingresar cliente",
                "Ingresar Vehiculo",
                "Ingresar Registro de Lavado ",
                "Listar Registros de Lavado ",
                "Listar clientes",
                "Salir"
        };

        while (true) {

            switch (menuOpcionesString(opts)) {
                case "Salir":
                    System.exit(0);
                    break;

                case "Ingresar cliente":
                    break;

                case "Ingresar Vehiculo":
                    break;

                case "Ingresar Lavado":
                    break;

                default:
                    break;
            }
        }
    }


    public static Cliente obtenerCliente() {
        Cliente c;
        String[] tipo_cliente = {"Estandar","Premiun"};
        c = new Cliente(input("id cliente"), menuOpciones(tipo_cliente), input("nombre"), input("apellido"),input("Telefono"), input("descuento"));
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

        l = new Lavado(input("id Lavado"), input("id vehiculo"), inputInt(null), inputInt(null), input("Precio"), input("Duracion"));
        return l;
    }

    public static int menuOpciones(String[] opts) {
        boolean pass = false;
        int opt;
        Scanner r = new Scanner(System.in);
        do {
            for (int i = 0; i < opts.length; i++) {
                System.out.println((i+1) + ". "+ opts[i]);
            }
            System.out.print("Escriba su opcion: ");
            opt = r.nextInt();
            if ( opt >= 0 && opt < opts.length ) pass = true;

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
                System.out.println((i+1) + ". "+ opts[i]);
            }
            System.out.print("Escriba su opcion: ");
            opt = r.nextInt();
            if ( opt >= 0 && opt < opts.length ) pass = true;

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
