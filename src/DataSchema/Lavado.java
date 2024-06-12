package DataSchema;

public class Lavado {
    String id_lavado, id_vehiclo, id_Cliente,tipo_vehiculo;
    int tipo_lavado;


    public String getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(String id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public Lavado(String id_vehiclo, int tipo_lavado, String tipo_vahiculo, String id_Cliente) {
        this.id_lavado = "";
        this.id_vehiclo = id_vehiclo;
        this.tipo_lavado = tipo_lavado;
        this.tipo_vehiculo = tipo_vahiculo;
        this.id_Cliente = id_Cliente;
    }

    public String getId_lavado() {
        return id_lavado;
    }

    public void setId_lavado(String id_lavado) {
        this.id_lavado = id_lavado;
    }

    public String getId_vehiclo() {
        return id_vehiclo;
    }

    public void setId_vehiclo(String id_vehiclo) {
        this.id_vehiclo = id_vehiclo;
    }

    public int getTipo_lavado() {
        return tipo_lavado;
    }

    public void setTipo_lavado(int tipo_lavado) {
        this.tipo_lavado = tipo_lavado;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vahiculo) {
        this.tipo_vehiculo = tipo_vahiculo;
    }

    

}
