package DataBaseConnection;

public interface ConnectionBD {

    public ConnectionBD connect();

    public String listarCLientes();

    public String listarRegistros();

    public String[] tipoLavado();

    public String[] tipoCliente();

}
