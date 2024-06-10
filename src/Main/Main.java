package Main;

import DataBaseConnection.OracleConnection;

public class Main {
    public static void main(String[] args) {
        //new MariadbConnection("192.168.128.12", 3306, "PruebasU", "admin", "andresUser").connect();
       // TerminalUtils.infoTrace("Bye");

        OracleConnection x = new OracleConnection("127.0.0.1",1521,"PruebasU","C##LAU","hola123");
        x.connect();
        x.listarRegistros();

    }
}
