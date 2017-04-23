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
public class C_Persona {
    private int id_persona;
    private String nombre;
    private String ape_paterno;
    private String ape_materno;
    private String tipo_documento;
    private String num_documento;
    private String direccion;
    private String telefono;
    private String email;
    
    static C_Persona persona = new C_Persona();

    public static C_Persona getPersona() {
        return persona;
    }

    public static void setPersona(C_Persona persona) {
        C_Persona.persona = persona;
    }
    
    public C_Persona() {
    }

    public C_Persona(int id_persona, String nombre, String ape_paterno, String ape_materno, String tipo_documento, String num_documento, String direccion, String telefono, String email) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.ape_paterno = ape_paterno;
        this.ape_materno = ape_materno;
        this.tipo_documento = tipo_documento;
        this.num_documento = num_documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe_paterno() {
        return ape_paterno;
    }

    public void setApe_paterno(String ape_paterno) {
        this.ape_paterno = ape_paterno;
    }

    public String getApe_materno() {
        return ape_materno;
    }

    public void setApe_materno(String ape_materno) {
        this.ape_materno = ape_materno;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
