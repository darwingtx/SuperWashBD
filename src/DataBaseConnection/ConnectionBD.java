package DataBaseConnection;

import DataSchema.Cliente;
import DataSchema.Lavado;
import DataSchema.Vehiculo;

public interface ConnectionBD {

    public ConnectionBD connect();

    public String listarCLientes();

    public String listarRegistros();

    public String[] tipoLavado();

    public String[] tipoCliente();

    public void insertClient(Cliente client);

    public void insertVeh(Vehiculo veh);

    public void insertLavado(Lavado lav);
}
