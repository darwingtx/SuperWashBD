package Main;

import DataBaseConnection.MariadbConnection;
import DataBaseConnection.OracleConnection;
import terminalUtils.TerminalUtils;

public class Main {
    public static void main(String[] args) {
        new MariadbConnection("192.168.128.12", 3306, "PruebasU", "admin", "andresUser").connect();
        TerminalUtils.infoTrace("Bye");

        //new OracleConnection("127.0.0.1",1521,"PruebasU","C##LAU","hola123").connect();

    }
}
