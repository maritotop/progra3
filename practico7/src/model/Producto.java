
package model;

import java.util.Date;

public class Producto {
    public Producto() {
    }

    public Producto(String nombre, String codigo, String precio, int cantidad, String fecha_vencimiento) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fecha_vencimiento = fecha_vencimiento;
    }

    private int id,cantidad;
    private String nombre,codigo,precio;
    private String fecha_vencimiento;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }
}
