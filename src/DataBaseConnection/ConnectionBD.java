package DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ConnectionBD {

    public ConnectionBD connect();

    public String listarCLientes();

    public void listarRegistros();

    public String[] tipoLavado();

    public String[] tipoCliente();

}
