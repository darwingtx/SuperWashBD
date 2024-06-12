package Menu;

import DataSchema.*;

import DataBaseConnection.*;
import terminalUtils.TerminalUtils;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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
        ConnectionBD db = null;
        switch (menuOpcionesString(tConx)) {
            case "Salir":
                System.exit(0);
                break;

            case "Trabajara con MariaDB":
                db = new MariadbConnection(
                        System.getenv("DB_HOST"),
                        Integer.parseInt(System.getenv("DB_PORT")),
                        System.getenv("DB_NAME"),
                        System.getenv("DB_USR"),
                        System.getenv("DB_PASS"));
                db = db.connect();
                if (db == null) {
                    TerminalUtils.infoTrace("------[+] Conexión Erronea!, verifica credenciales------");
                } else {
                    menuDB(db, opts);
                }
                break;

            case "Trabajara con Oracle Database":
                db = new OracleConnection(
                        "127.0.0.1", 1521,
                        "SQLFinal",
                        "PARZ",
                        "1015332154");
                db = db.connect();
                if (db == null) {
                    TerminalUtils.infoTrace("------[+] Conexión Erronea!, verifica credenciales------");
                } else {
                    menuDB(db, opts);
                }
                break;
            default:
                break;
        }
    }

    private static void menuDB(ConnectionBD x, String[] opts) {
        while (true) {

            switch (menuOpcionesString(opts)) {
                case "Salir":
                    System.exit(0);
                    break;

                case "Ingresar cliente":
                    Cliente cliente = obtenerCliente();
                    x.insertClient(cliente);
                    break;

                case "Ingresar Vehiculo":
                    Vehiculo vehiculo = obtenerVehiculo();
                    x.insertVeh(vehiculo);

                    break;

                case "Ingresar Registro de Lavado ":
                    Lavado lavado = obtenerLavado();
                    x.insertLavado(lavado);
                    break;

                case "Listar Registros de Lavado ":
                    System.out.println(x.listarRegistros());

                    break;

                case "Listar clientes":
                    System.out.println(x.listarCLientes());
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
                input("Tipo_vehiculo"));
        return v;
    }

    public static Lavado obtenerLavado() {
        Lavado l = null;
        String[] Tlav = { "Exterior", "Interior", "Completo" };
        l = new Lavado(menuOpciones(Tlav), inputInt("Id Cliente"), input("Id Vehiculo"));
        return l;
    }

    public static int menuOpciones(String[] opciones) {
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
                scanner.next(); 
            }
        }

        return opcion;
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
                scanner.next(); 
            }
        }

        return opciones[opcion];
    }

    public static String input(String atr) {
        Scanner r = new Scanner(System.in);
        System.out.println("Ingrese " + atr + ": ");
        String d = r.nextLine();
        return d;
    }

    public static int inputInt(String atr) {
        Scanner r = new Scanner(System.in);
        System.out.println("Ingrese " + atr + ": ");
        int d = r.nextInt();
        return d;
    }
}