package DataBaseConnection;

public interface ConnectionBD {

    public void connect();

    public String[] tipoCliente();

    public String[] tipoLavado();

    public String ListarRegistros();

    public String ListarCLientes();

}
