package Main;

import DataBaseConnection.*;
import Menu.Menu;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
     Menu menu = new Menu();
     menu.menu();
        // if (true) {
        //     ConnectionBD db = new MariadbConnection(
        //             System.getenv("DB_HOST"),
        //             Integer.parseInt(System.getenv("DB_PORT")),
        //             System.getenv("DB_NAME"),
        //             System.getenv("DB_USR"),
        //             System.getenv("DB_PASS"));
        //     db.connect();
        //     System.out.println(Arrays.toString(Arrays.stream(db.tipoLavado()).toArray()));
        // } else {
        //     OracleConnection x = new OracleConnection("127.0.0.1", 1521, "SQLFinal", "C##LAU", "hola123");
        //     x.connect();
        //     x.listarRegistros();
        // }   
     }
}