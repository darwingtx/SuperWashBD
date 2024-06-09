package DataBaseConnection;

import terminalUtils.TerminalUtils;

import java.sql.*;

public class MariadbConnection implements ConnectionBD {

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

    public void connect() {
        this.url = "jdbc:mariadb://"+host+":"+port+"/"+db+"?user="+usr+"&password="+pass;
        TerminalUtils.infoTrace("Trying to connect. url: " + url);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url);
            TerminalUtils.successTrace("Connection Successfully!");

            query = connection.createStatement();
            res = query.executeQuery("select * from lavado;");

            // exeQuery("select");

            while (res.next()) {
                System.out.println("ID: " + res.getObject(1) + ", nombre: " + res.getString(2));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param sql {@code SELECT} statement
     * @throws SQLException
     */
    private ResultSet exeQuery(String sql) throws SQLException {
        return query.executeQuery(sql);

    }

    private void exe(String sql) throws SQLException {
        query.execute("INSERT INTO producto VALUES(12, 'Insersion Java', 323, 3)");
    }
}
