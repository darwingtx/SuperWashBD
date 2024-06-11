package Menu;

import DataSchema.*;

import DataBaseConnection.*;

import java.util.InputMismatchException;
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
        final String[] tConx = {
                "Trabajara con MariaDB",
                "Trabajara con Oracle Database",
                "Salir"
        };
        switch (menuOpcionesString(tConx)) {
            case "Salir":
                System.exit(0);
                break;

            case "Trabajara con MariaDB":
                ConnectionBD db = new MariadbConnection(
                        System.getenv("DB_HOST"),
                        Integer.parseInt(System.getenv("DB_PORT")),
                        System.getenv("DB_NAME"),
                        System.getenv("DB_USR"),
                        System.getenv("DB_PASS"));
                db.connect();
                menuMdb(db, opts);

                break;

            case "Trabajara con Oracle Database":
                OracleConnection x = new OracleConnection("127.0.0.1", 1521, "SQLFinal", "PARZ", "1015332154");
                x.connect();
                menuOracle(x, opts);
                break;
            default:
                break;
        }
    }

    private static void menuOracle(OracleConnection x, String[] opts) {
        while (true) {

            switch (menuOpcionesString(opts)) {
                case "Salir":
                x.closeConnection();
                    System.exit(0);
                    break;

                case "Ingresar cliente":

                    break;

                case "Ingresar Vehiculo":

                    break;

                case "Ingresar Registro de Lavado ":

                    break;

                case "Listar Registros de Lavado ":
                    x.listarRegistros();
                    break;

                case "Listar clientes":
                    x.listarCLientes();
                    break;

                default:
                    break;
            }
        }
    }

    private static void menuMdb(ConnectionBD db, String[] opts) {
        while (true) {

            switch (menuOpcionesString(opts)) {
                case "Salir":
                    System.exit(0);
                    break;

                case "Ingresar cliente":

                    break;

                case "Ingresar Vehiculo":

                    break;

                case "Ingresar Registro de Lavado ":

                    break;

                case "Listar Registros de Lavado ":
                    db.listarRegistros();
                    break;

                case "Listar clientes":
                    db.listarCLientes();
                    break;

                default:
                    break;
            }
        }
    }

    public static Cliente obtenerCliente() {
        Cliente c;
        String[] tipo_cliente = { "Estandar", "Premiun" };
        c = new Cliente(input("id cliente"), menuOpciones(tipo_cliente), input("nombre"), input("apellido"),
                input("Telefono"), input("descuento"));
        return c;
    }

    public static Vehiculo obtenerVehiculo() {
        Vehiculo v;
        String[] marca = { "Mercedez ven10", "whatsapp", "taxi" };
        v = new Vehiculo(input("Id vehiculo"), input("id cliente"), menuOpcionesString(marca),
                inputInt("Tipo_vehiculo"));
        return v;
    }

    public static Lavado obtenerLavado() {
        Lavado l;

        l = new Lavado(input("id Lavado"), input("id vehiculo"), inputInt(null), inputInt(null), input("Precio"),
                input("Duracion"));
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
            if (opt >= 0 && opt < opts.length)
                pass = true;

        } while (!pass);
        r.close();
        return opt;
    }

     public static String menuOpcionesString(String[] opciones) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        
        while (true) {
            for (int i = 0; i < opciones.length; i++) {
                System.out.println((i + 1) + ". " + opciones[i]);
            }
            System.out.print("Escriba su opción: ");
            try {
                opcion = scanner.nextInt() - 1;
                if (opcion >= 0 && opcion < opciones.length) {
                    break;
                } else {
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                scanner.next(); // Limpiar la entrada inválida
            }
        }
        
        return opciones[opcion];
    }

    // public static String menuOpcionesString(String[] opts) {
    //     boolean pass = false;
    //     int opt;
    //     Scanner r = new Scanner(System.in);
    //     do {
    //         for (int i = 0; i < opts.length; i++) {
    //             System.out.println((i + 1) + ". " + opts[i]);
    //         }
    //         System.out.print("Escriba su opcion: ");
    //         opt = r.nextInt();
    //         if (opt >= 0 && opt < opts.length)
    //             pass = true;

    //     } while (!pass);
    //     r.close();
    //     return opts[opt];
    // }

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
