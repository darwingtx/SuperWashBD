package DataBaseConnection;

import DataSchema.Cliente;
import DataSchema.Lavado;
import DataSchema.Vehiculo;
import Others.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static terminalUtils.TerminalUtils.*;


public class MariadbConnection implements ConnectionBD{
    
    // attributes
    private String host;
    private int port;
    private String db;
    private String usr;
    private String pass;
    private String url;

    // attribute - connections- - query's

    Connection connection = null;
    Statement query = null;

    public MariadbConnection(String host, int port, String db, String usr, String pass) {
        this. host = host;
        this.port = port;
        this. db = db;
        this. usr = usr;
        this. pass = pass;
        this.url = "jdbc:mariadb://"+host+":"+port+"/"+db+"?user="+usr+"&password="+pass;
    }

    public ConnectionBD connect() {
        this.url = "jdbc:mariadb://"+host+":"+port+"/"+db+"?user="+usr+"&password="+pass;
        infoTrace("Trying to connect. url: " + url);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url);
            query = connection.createStatement();
            successTrace("Connection Successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return this;
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

    @Override
    public String listarClientes() {
        try {
            return Util.showResponse(query.executeQuery("SELECT c.ID_CLIENTE, c.NOMBRE, APELLIDO, t.nombre as Tipo_Cliente, c.telefono from cliente c\n" +
                    "INNER JOIN tipo_cliente t on c.id_tipo_cliente = t.id_tipo_cliente"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String listarRegistros() {
        try {
            return Util.showResponse(query.executeQuery("SELECT \n" +
                    "    lv.id_lavado_veh, \n" +
                    "    l.tipo_lavado, \n" +
                    "    lv.id_vehiculo, \n" +
                    "    v.tipo_vehiculo, \n" +
                    "    c.id_cliente, \n" +
                    "    c.nombre, \n" +
                    "    c.apellido, \n" +
                    "    lv.precio\n" +
                    "FROM \n" +
                    "    lavado_vehiculo lv\n" +
                    "INNER JOIN \n" +
                    "    cliente c ON lv.id_cliente = c.id_cliente\n" +
                    "INNER JOIN \n" +
                    "    vehiculo v ON lv.id_vehiculo = v.id_vehiculo\n" +
                    "INNER JOIN \n" +
                    "    lavado l ON lv.id_tipo_lavado = l.id_lavado;\n"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map tipoLavado() {
        Map<String, Integer> tipos = new HashMap<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("Select * from lavado")) {
                while (resultSet.next()) {
                    tipos.putIfAbsent(resultSet.getString("TIPO_LAVADO"), resultSet.getInt("ID_LAVADO"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
               errorTrace(e.getMessage(), true);
            }
        }
        return tipos;
    }

    @Override
    public Map tipoCliente() {
        Map<String, Integer> tipos = new HashMap<>();
        if (connection != null) {
            try (ResultSet res = query.executeQuery("select id_tipo_cliente, nombre from tipo_cliente")) {
                while (res.next())
                    tipos.putIfAbsent(res.getString("nombre"), res.getInt("id_tipo_cliente"));

            } catch (SQLException e) {
                e.printStackTrace();
                errorTrace(e.getMessage(), true);
            }
        }
        return tipos;
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
    public void insertClient(Cliente client) {
        String query = "CALL insertar_cliente (?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, client.getId_cliente());
            pstmt.setInt(2, client.getTipoCliente());
            pstmt.setString(3, client.getNombre());
            pstmt.setString(4, client.getApellido());
            pstmt.setString(5, client.getTel());
            pstmt.execute();
            successTrace("Cliente insertado");
        } catch (SQLException e) {
            errorTrace(e.getMessage(), true);
        }
    }

    @Override
    public void insertVeh(Vehiculo veh) {
        String query = "CALL insertar_vehiculo (?, ?, ?);";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, veh.getId_vehiculo());
            pstmt.setString(2, veh.getMarca());
            pstmt.setString(3, veh.getTipo_vehiculo());
            pstmt.execute();
            successTrace("Vehiculo insertado");
        } catch (SQLException e) {
            errorTrace(e.getMessage(), true);
        }
    }

    @Override
    public void insertLavado(Lavado lav) {
        String query = "CALL insertar_lavado_vehiculo (?,?,?);";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, lav.getTipo_lavado());
            pstmt.setString(2, lav.getId_vehiculo());
            pstmt.setInt(3, lav.getId_cliente());
            pstmt.execute();
            successTrace("Registro de lavado insertado");
        } catch (SQLException e) {
            errorTrace(e.getMessage(), true);
        }
    }

}
