package DataBaseConnection;

import DataSchema.Cliente;
import DataSchema.Lavado;
import DataSchema.Vehiculo;
import java.util.Map;

public interface ConnectionBD {

    public ConnectionBD connect();

    public String listarCLientes();

    public String listarRegistros();

    public Map tipoLavado();

    public Map tipoCliente();

    public void insertClient(Cliente client);

    public void insertVeh(Vehiculo veh);

    public void insertLavado(Lavado lav);
}
