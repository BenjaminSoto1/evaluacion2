package cl.example.evaluacion.Clases;

public class Pedidos {
    private String idPedido;
    private String nombre;
    private String estado;

    public Pedidos()
    {
        this.idPedido ="";
        this.nombre="";
        this.estado="";
    }

    public Pedidos( String idPedido, String nombre, String estado )
    {
        this.idPedido =idPedido;
        this.nombre=nombre;
        this.estado=estado;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return
                   '\'' +
                "Producto: "+nombre + '\'' +
              "Estado: "+ estado + '\''
              ;
    }
}
