package Main;

import DataBaseConnection.OracleConnection;
import terminalUtils.TerminalUtils;

public class Main {
    public static void main(String[] args) {
        //Menu.Menu.menu(true);
        //TerminalUtils.infoTrace("Bye");

        OracleConnection x = new OracleConnection("127.0.0.1", 1521, "PruebasU", "C##LAU", "hola123");
        x.connect();
        String s = x.ListarRegistros();
        //String[] y = x.tipoCliente();
        System.out.println(s);
    }
}
