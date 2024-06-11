package DataBaseConnection;

import Others.Util;
import terminalUtils.TerminalUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleConnection implements ConnectionBD {
    // atributes
    final String host;
    final int port;
    final String db;
    final String usr;
    final String pass;
    final String url;

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
    public OracleConnection connect() {
        TerminalUtils.infoTrace("Trying to connect. url: " + url);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, usr, pass);
            TerminalUtils.infoTrace("------[+] Conexión Exitosa!------");
            return this;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR -> Clase del controlador no encontrada: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR -> SQL Exception: " + e.getMessage());
        }
        return null;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeQuery(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Seleccionando...");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getObject(1) + ", Tipo_Lavado: " + resultSet.getString("Tipo_Lavado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR -> SQL Exception: " + e.getMessage());
        }
    }

    @Override
    public String[] tipoCliente() {
        List<String> tipos = new ArrayList<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("Select nombre from Tipo_Cliente")) {
                System.out.println("Seleccionando...");
                while (resultSet.next()) {
                    tipos.add(resultSet.getString("nombre"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR -> SQL Exception: " + e.getMessage());
            }
        }
        return tipos.toArray(new String[0]);
    }

    @Override
    public String[] tipoLavado() {
        List<String> tipos = new ArrayList<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("Select TIPO_LAVADO from Lavado")) {
                System.out.println("Seleccionando...");
                while (resultSet.next()) {
                    tipos.add(resultSet.getString("TIPO_LAVADO"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR -> SQL Exception: " + e.getMessage());
            }
        }
        return tipos.toArray(new String[0]);
    }

    @Override
    public String listarRegistros() {
        String s = "";
        if (connection!= null) {
            try (Statement statement = connection.createStatement()) { // <--- Fix: ) instead of {
                System.out.println("Seleccionando...");
                return Util.showResponse(statement.executeQuery("SELECT lv.ID_LAVADOVEH, l.TIPO_LAVADO, lv.ID_VEHICULO, v.TIPO_VEHICULO, c.ID_CLIENTE, c.nombre, c.apellido, lv.precio  \n" +
                        "FROM LAVADOVEHICULO lv\n" +
                        "INNER JOIN CLIENTE c on lv.ID_CLIENTE = c.ID_CLIENTE\n" +
                        "INNER JOIN VEHICULO v ON lv.ID_VEHICULO = v.ID_VEHICULO\n" +
                        "INNER JOIN LAVADO l on lv.ID_Tipo_Lavado = l.ID_LAVADO"));
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR -> SQL Exception: " + e.getMessage());
            }
        }
        return s;
    }

    @Override
    public String listarCLientes() {
        if (connection != null) {
            try (Statement statement = connection.createStatement()){
                return Util.showResponse(statement.executeQuery("SELECT c.ID_CLIENTE, c.NOMBRE, APELLIDO, t.nombre as Tipo_Cliente, c.telefono from cliente c\n" +
                        "INNER JOIN tipo_cliente t on c.id_tipo_cliente = t.id_tipo_cliente"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "no hay conexion";
    }


}
