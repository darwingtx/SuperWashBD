package Main;

import mariadbConnection.MariadbConnection;
import terminalUtils.TerminalUtils;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        new MariadbConnection("192.168.128.12", 3306, "PruebasU", "admin", "andresUser").connect();
        TerminalUtils.infoTrace("Bye");
    }
}
