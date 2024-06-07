package mariadbConnection;

import terminalUtils.TerminalUtils;

import java.sql.*;

public class MariadbConnection {


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
            res = query.executeQuery("select * from producto");

            while (res.next()) {
                System.out.println("ID: " + res.getObject(1) + ", nombre: " + res.getString(2));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void insert(Statement query) throws SQLException {
        query.execute("INSERT INTO producto VALUES(12, 'Insersion Java', 323, 3)");
    }
}
