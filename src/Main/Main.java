package Main;

import mariadbConnection.MariadbConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        new MariadbConnection("IP DEL SERVER", 3306, "PruebasU", "SU USER CAREMONDAS", "PONGAN SU PASSWORD").connect();
        /*
        Connection conn = null;
        Statement sentencia = null;
        ResultSet resultado = null;

        String host = "127.0.0.1";
        String port = "1521";
        String serviceName = "xe";
        String url = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + serviceName;
        String user = "C##LAU";
        String password = "hola123";

        System.out.println("[!] Conexión a la base de datos...");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("[+] Conexión Exitosa!");


            sentencia = conn.createStatement();
            System.out.println("Seleccionando...");

            // Consulta simple para verificar la conexión y acceso a la tabla
            resultado = sentencia.executeQuery("select * from cliente");
            if (resultado.next()) {
                System.out.println("conn -> " + resultado.getObject(1));
                int count = resultado.getInt(1);
                System.out.println("Número de registros en la tabla Lavado: " + count);
            }

            // Realiza la consulta principal
            resultado = sentencia.executeQuery("SELECT * FROM Lavado");
            while (resultado.next()) {
                System.out.println("ID: " + resultado.getObject(1) + ", Tipo_Lavado: " + resultado.getString("Tipo_Lavado"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR -> Clase del controlador no encontrada: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR -> SQL Exception: " + e.getMessage());
        } finally {
            // Cerrar ResultSet
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Cerrar Statement
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Cerrar Connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        */

        System.out.println("Consulta finalizada.");
    }
}
