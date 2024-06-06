package DataSchema;

public class Lavado {
    String id_lavado, id_vehiclo;
    int tipo_lavado, tipo_vehiculo;  

    String precio, duracion;

    public Lavado(String id_lavado, String id_vehiclo, int tipo_lavado, int tipo_vahiculo, String precio,
            String duracion) {
        this.id_lavado = id_lavado;
        this.id_vehiclo = id_vehiclo;
        this.tipo_lavado = tipo_lavado;
        this.tipo_vehiculo = tipo_vahiculo;
        this.precio = precio;
        this.duracion = duracion;
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

    public int getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(int tipo_vahiculo) {
        this.tipo_vehiculo = tipo_vahiculo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    

}
