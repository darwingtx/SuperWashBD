package DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ConnectionBD {

    public OracleConnection connect();

    public String ListarCLientes();

    public String ListarRegistros();

    public String[] tipoLavado();

    public String[] tipoCliente();

}
