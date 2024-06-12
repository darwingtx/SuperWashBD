package DataSchema;

public class Lavado {

    private int id_cliente, tipo_lavado;
    private String id_vehiculo;

    public Lavado(int tipo_lavado, int id_cliente, String id_vehiculo) {
        this.tipo_lavado = tipo_lavado;
        this.id_cliente = id_cliente;
        this.id_vehiculo = id_vehiculo;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getTipo_lavado() {
        return tipo_lavado;
    }

    public void setTipo_lavado(int tipo_lavado) {
        this.tipo_lavado = tipo_lavado;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }
}
