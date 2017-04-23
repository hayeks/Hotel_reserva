/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Date;

/**
 *
 * @author Deliberth
 */
public class C_Reserva {
    private int id_reserva;
    private int id_habitacion;
    private int id_cliente;
    private int id_trabajador;
    private String tipo_reserva;
    private Date fecha_reserva;
    private Date fecha_ingreso;
    private Date fecha_salida;
    private Double costo_alojamiento;
    private String estado;

    public C_Reserva() {
    }

    public C_Reserva(int id_reserva, int id_habitacion, int id_cliente, int id_trabajador, String tipo_reserva, Date fecha_reserva, Date fecha_ingreso, Date fecha_salida, Double costo_alojamiento, String estado) {
        this.id_reserva = id_reserva;
        this.id_habitacion = id_habitacion;
        this.id_cliente = id_cliente;
        this.id_trabajador = id_trabajador;
        this.tipo_reserva = tipo_reserva;
        this.fecha_reserva = fecha_reserva;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
        this.costo_alojamiento = costo_alojamiento;
        this.estado = estado;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getTipo_reserva() {
        return tipo_reserva;
    }

    public void setTipo_reserva(String tipo_reserva) {
        this.tipo_reserva = tipo_reserva;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Double getCosto_alojamiento() {
        return costo_alojamiento;
    }

    public void setCosto_alojamiento(Double costo_alojamiento) {
        this.costo_alojamiento = costo_alojamiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
