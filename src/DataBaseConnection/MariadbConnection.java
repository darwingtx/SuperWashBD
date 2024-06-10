package DataBaseConnection;

import terminalUtils.ColumnFormat;
import terminalUtils.TerminalUtils;

import java.sql.*;
import java.util.ArrayList;

public class MariadbConnection implements ConnectionBD{

    /**
     * DOCUMENTACION MARIADB
     *      Para conectarse a la base de datos, tienes que tener presente lo siguiente:
     *          - Tener un usuario creado con privileguios sobre la base de datos:
     *
     *                  crear usuario:
     *                      create user admin@'IP DE CONEXION EN CASO DE QUE NO SEA LOCALHOST' identified by 'andresUser'
     *                          - Ejemplo de ip: 192.168.128.% donde el % representa /24 (dejen el %)
     *
     *
     *                  privileguios:
     *                      GRANT ALL PRIVILEGES ON NOMBRE_DB.* TO 'USERNAME'@'IP COMO LA DEL USUARIO O %' IDENTIFIED BY 'PASSWORD';
     *
     *          - Tener el servicio de mariadb corriendo (esa se las dejo si estan en windows)
     *
     *          - Si se van a conectar desde otra maquina, configurar el bind-address = 0.0.0.0 o a la ip espesifica del cliente
     *                  - se puede configurar en el archivo /etc/mysql/mariadb.conf.d/50-server.cnf (en linux)
     *
     *
     * TODO: ESTA VAINALOCA
     * Triggers:
     *   - cuando se inserte, enseguida calcular el valor dependiendo del tipo de vehiculo (moto, carro, camion), tipo de lavado (interior, exterior, completo) y tipo cliente (premiun 20%, estandar 0%)
     *   -
     *
     * Procedimientos:
     *   - insertar
     *   - consultar datos
     *
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
        this.url = "jdbc:mariadb://"+host+":3306/"+db+"?user="+usr+"&password="+pass;
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
            return showResponse(query.executeQuery("SELECT c.ID_CLIENTE, c.NOMBRE, APELLIDO, t.nombre as Tipo_Cliente, c.telefono from cliente c\n" +
                    "INNER JOIN tipo_cliente t on c.id_tipo_cliente = t.id_tipo_cliente"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String listarRegistros() {
        try {
            return showResponse(query.executeQuery("SELECT \n" +
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
        return new String[0];
    }

    @Override
    public String[] tipoCliente() {
        return new String[0];
    }

    // utility: show the response in a table
    private String showResponse(ResultSet response) throws SQLException {

        ResultSetMetaData resData = response.getMetaData();
        int columnCount  = resData.getColumnCount();

        ArrayList<ArrayList<String>> data = new ArrayList<>();

        ArrayList<ColumnFormat> columnsFormat = new ArrayList<>();


        // extract data
        int[] maxLengthColumn = new int[columnCount];
        for (int i = 0; i < columnCount; i++) {
            maxLengthColumn[i] = resData.getColumnName(i+1).length();
        }

        int row = 0;
        while (res.next()) {
            ArrayList<String> rowData = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                String d = res.getObject(i) + "";
                rowData.add(d);
                if (d.length() > maxLengthColumn[i-1]) maxLengthColumn[i-1] = d.length();
            }
            data.add(rowData);
            row++;
        }

        for (int i = 1; i <= columnCount; i++) {
            columnsFormat.add(new ColumnFormat(maxLengthColumn[i-1], resData.getColumnName(i), ""));
        }

        return new TerminalUtils(columnsFormat).printTable(data, false);
    }
}
