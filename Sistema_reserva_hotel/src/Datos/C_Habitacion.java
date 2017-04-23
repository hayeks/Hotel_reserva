/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Deliberth
 */
public class C_Habitacion {
    private int id_habitacion;
    private String numero;
    private String piso;
    private String descripcion;
    private String caracteristica;
    private double presio_diario;
    private String tipo_habitacion;
    private String estado;

    public C_Habitacion(int id_habitacion, String numero, String piso, String descripcion, String caracteristica, double presio_diario, String tipo_habitacion, String estado) {
        this.id_habitacion = id_habitacion;
        this.numero = numero;
        this.piso = piso;
        this.descripcion = descripcion;
        this.caracteristica = caracteristica;
        this.presio_diario = presio_diario;
        this.tipo_habitacion = tipo_habitacion;
        this.estado = estado;
    }

    public C_Habitacion() {
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public double getPresio_diario() {
        return presio_diario;
    }

    public void setPresio_diario(double presio_diario) {
        this.presio_diario = presio_diario;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
