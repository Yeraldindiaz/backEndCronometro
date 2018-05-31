/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.o7planning.restfulcrud.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MARIO
 */
@XmlRootElement(name = "EntrenadorDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntrenadorDTO {

    private int ID_Entrenador;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private int cedula;
    private String ciudad;
    private String contrasena;
    private DeporteDTO deporteFK;
    
    public int getID_Entrenador() {
        return ID_Entrenador;
    }

    public void setID_Entrenador(int ID_Entrenador) {
        this.ID_Entrenador = ID_Entrenador;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContraseña(String contrasena) {
        this.contrasena = contrasena;
    }

    public DeporteDTO getDeporteFK() {
        return deporteFK;
    }

    public void setDeporteFK(DeporteDTO deporteFK) {
        this.deporteFK = deporteFK;
    }

}
