package DataSchema;

public class Cliente {
    String id_cliente;

    int tipo_cliente;
    String nombre;
    String apellido;
    String tel;



    public Cliente(String id_cliente, int tipoCliente, String nombre, String apellido, String tel) {
        this.id_cliente = id_cliente;
        this.tipo_cliente = tipoCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tel = tel;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getTipoCliente() {
        return tipo_cliente;
    }

    public void setTipoCliente(int tipoCliente) {
        this.tipo_cliente = tipoCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
