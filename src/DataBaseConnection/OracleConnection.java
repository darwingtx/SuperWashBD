package DataBaseConnection;

import terminalUtils.TerminalUtils;

import java.sql.*;

public class OracleConnection implements ConnectionBD {
    // atributes
    private String host;
    private int port;
    private String db;
    private String usr;
    private String pass;
    private String url;

    // atribute - connections- - querys

    Connection connection = null;
    Statement query = null;
    ResultSet res = null;

    public OracleConnection(String host, int port, String db, String usr, String pass) {
        this.host = host;
        this.port = port;
        this.db = db;
        this.usr = usr;
        this.pass = pass;
        this.url = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + "xe";
    }

    @Override
    public void connect() {
        this.url = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + "xe";
        TerminalUtils.infoTrace("Trying to connect. url: " + url);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, usr, pass);
            System.out.println("[+] Conexión Exitosa!");


            query = connection.createStatement();
            System.out.println("Seleccionando...");

            // Consulta simple para verificar la conexión y acceso a la tabla
            res = query.executeQuery("select * from cliente");
            if (res.next()) {
                System.out.println("conn -> " + res.getObject(1));
                int count = res.getInt(1);
                System.out.println("Número de registros en la tabla Lavado: " + count);
            }

            // Realiza la consulta principal
            res = query.executeQuery("SELECT * FROM Lavado");
            while (res.next()) {
                System.out.println("ID: " + res.getObject(1) + ", Tipo_Lavado: " + res.getString("Tipo_Lavado"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR -> Clase del controlador no encontrada: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR -> SQL Exception: " + e.getMessage());
        } finally {
            // Cerrar ResultSet
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Cerrar Statement
            if (query != null) {
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Cerrar Connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Consulta finalizada.");
    }
}
