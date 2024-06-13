package DataSchema;

public class Vehiculo {
    String id_vehiculo;
    String id_cliente;
    String marca;
    String tipo_vehiculo;

    public Vehiculo(String id_vehiculo, String id_cliente, String marca, String tipo_vehiculo) {
        this.id_vehiculo = id_vehiculo;
        this.id_cliente = id_cliente;
        this.marca = marca;
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }
    
    
}
