package Main;

import DataBaseConnection.OracleConnection;
import DataSchema.Cliente;
import DataSchema.Lavado;
import DataSchema.Vehiculo;
import Menu.Menu;

public class Main {
    public static void main(String[] args) {
     //Menu menu = new Menu();
     //menu.menu();
        Lavado x = new Lavado("GHI789", 2, "Carro", "105151515");
        OracleConnection db = new OracleConnection(
                System.getenv("DB_HOST"),
                Integer.parseInt(System.getenv("DB_PORT")),
                System.getenv("DB_NAME"),
                System.getenv("DB_USR"),
                System.getenv("DB_PASS"));
        db.connect();
        db.insertLavado(x);
    }
}