package DataBaseConnection;

import DataSchema.Cliente;
import DataSchema.Lavado;
import DataSchema.Vehiculo;
import Others.Util;
import terminalUtils.TerminalUtils;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static terminalUtils.TerminalUtils.successTrace;

public class OracleConnection implements ConnectionBD {
    // attributes
    final String host;
    final int port;
    final String db;
    final String usr;
    final String pass;
    final String url;

    // attribute - connections- - query's

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
            Util.errorLog();
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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

    private Boolean executeQuery(String query) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR -> SQL Exception: " + e.getMessage());
        }
        return false;
    }


    @Override
    public String[] tipoVehiculo() {
        List<String> tipos = new ArrayList<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("Select DISTINCT Tipo_Vehiculo from vehiculo")) {
                System.out.println("Seleccionando...");
                while (resultSet.next()) {
                    tipos.add(resultSet.getString("Tipo_Vehiculo"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR -> SQL Exception: " + e.getMessage());
            }
        }
        return tipos.toArray(new String[0]);
    }

    @Override
    public Map tipoCliente() {
        Map<String, Integer> tipos = new HashMap<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("Select * from Lavado")) {
                while (resultSet.next()) {
                    tipos.putIfAbsent(resultSet.getString("TIPO_LAVADO"), resultSet.getInt("ID_LAVADO"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR -> SQL Exception: " + e.getMessage());
            }
        }
        return tipos;
    }

    @Override
    public void insertClient(Cliente client) {
        String query = "BEGIN insertar_cliente (?, ?, ?, ?, ?); END;";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, client.getId_cliente());
            pstmt.setInt(2, client.getTipoCliente());
            pstmt.setString(3, client.getNombre());
            pstmt.setString(4, client.getApellido());
            pstmt.setString(5, client.getTel());
            pstmt.execute();
            TerminalUtils.infoTrace("Cliente insertado");
        } catch (SQLException e) {
            TerminalUtils.infoTrace("Error al insertar: " + e.getMessage());
        }
    }

    @Override
    public void insertVeh(Vehiculo veh) {
        String query = "BEGIN insertar_vehiculo (?, ?, ?); END;";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, veh.getId_vehiculo());
            pstmt.setString(2, veh.getMarca());
            pstmt.setString(3, veh.getTipo_vehiculo());
            pstmt.execute();
            TerminalUtils.infoTrace("Vehiculo insertado");
        } catch (SQLException e) {
            TerminalUtils.infoTrace("Error al insertar: " + e.getMessage());
        }
    }

    @Override
    public void insertLavado(Lavado lav) {
        String query = "BEGIN insertar_lavado_vehiculo (?,?,?); END;";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, lav.getTipo_lavado());
            pstmt.setString(2, lav.getId_vehiculo());
            pstmt.setInt(3, lav.getId_cliente());
            pstmt.execute();
            successTrace("Registro lavado insertado");
        } catch (SQLException e) {
            TerminalUtils.infoTrace("Error al insertar: " + e.getMessage());
        }
    }

    @Override
    public Map tipoLavado() {
        Map<String, Integer> tipos = new HashMap<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("Select * from Lavado")) {
                while (resultSet.next()) {
                    tipos.putIfAbsent(resultSet.getString("TIPO_LAVADO"), resultSet.getInt("ID_LAVADO"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR -> SQL Exception: " + e.getMessage());
            }
        }
        return tipos;
    }

    @Override
    public String listarRegistros() {
        String s = "";
        if (connection != null) {
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
    public String listarClientes() {
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                return Util.showResponse(statement.executeQuery("SELECT c.ID_CLIENTE, c.NOMBRE, APELLIDO, t.nombre as Tipo_Cliente, c.telefono from cliente c\n" +
                        "INNER JOIN tipo_cliente t on c.id_tipo_cliente = t.id_tipo_cliente"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "no hay connexion";
    }


}
