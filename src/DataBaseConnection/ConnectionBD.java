package DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ConnectionBD {

    public OracleConnection connect();

    public String listarCLientes();

    public String listarRegistros();

    public String[] tipoLavado();

    public String[] tipoCliente();

}
