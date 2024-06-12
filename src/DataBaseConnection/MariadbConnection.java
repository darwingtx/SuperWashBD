package DataBaseConnection;

import DataSchema.Cliente;
import DataSchema.Lavado;
import DataSchema.Vehiculo;
import Others.Util;
import terminalUtils.TerminalUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MariadbConnection implements ConnectionBD{

    /**
     * TODO: ESTA VAINALOCA
     * Triggers:
     *   - cuando se inserte, enseguida calcular el valor dependiendo del tipo de vehiculo (moto, carro, camion), tipo de lavado (interior, exterior, completo) y tipo cliente (premiun 20%, estandar 0%)
     *   -
     * Procedimientos:
     *   - insertar
     *   - consultar datos
    */


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
        TerminalUtils.infoTrace("Trying to connect. url: " + url);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url);
            TerminalUtils.successTrace("Connection Successfully!");
            query = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String listarCLientes() {
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
    public String[] tipoLavado() {
        List<String> tipos = new ArrayList<>();
        if (connection!= null) {
            try (ResultSet resultSet = query.executeQuery("Select tipo_lavado from lavado")) {
                while (resultSet.next()) {
                    tipos.add(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR -> SQL Exception: " + e.getMessage());
            }
        }
        return tipos.toArray(new String[0]);
    }

    @Override
    public String[] tipoCliente() {
        List<String> tipos = new ArrayList<>();
        if (connection!= null) {
            try (ResultSet resultSet = query.executeQuery("Select nombre from tipo_cliente")) {
                while (resultSet.next()) {
                    tipos.add(resultSet.getString(1));
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

    }

}
