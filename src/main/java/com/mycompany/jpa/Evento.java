/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author MARIO
 */
@Entity
@Table(name = "EVENTOS")
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Evento;

    private String nombre;
    private String Tipo;
    private String fecha;
    private String lugar;

    public Evento() {
        this.list_Tiempo_Competencias = new ArrayList<>();
    }

    public Evento(String nombre, String Tipo, String fecha, String lugar) {
        this.nombre = nombre;
        this.Tipo = Tipo;
        this.fecha = fecha;
        this.lugar = lugar;
        this.list_Tiempo_Competencias = new ArrayList<>();
    }

    public int getID_Evento() {
        return ID_Evento;
    }

    public void setID_Evento(int ID_Evento) {
        this.ID_Evento = ID_Evento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString() {
        return "Evento{" + "id=" + ID_Evento + ", nombre=" + nombre + ", Tipo=" + Tipo + ", fecha=" + fecha + ", lugar=" + lugar + '}';
    }

    //Realacoin de 0-1----1 entrew (Evento --- Deporte)
    @ManyToOne
    @JoinColumn(name = "deport_FK")
    private Deportes deport_FK;

    public Deportes getDeport_FK() {
        return deport_FK;
    }

    public void setDeport_FK(Deportes deport_FK) {
        this.deport_FK = deport_FK;
    }

    // Relacion de 1-M entre (Evento -- Tiempo Competencia)
    //Se Referencia al mapeo, con elnombre de la variable en la clase
    @OneToMany(mappedBy = "EventoFK", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TiempoCompetencia> list_Tiempo_Competencias;

    public List<TiempoCompetencia> getList_Tiempo_Competencias() {
        return list_Tiempo_Competencias;
    }

    public void setList_Tiempo_Competencias(List<TiempoCompetencia> list_Tiempo_Competencias) {
        this.list_Tiempo_Competencias = list_Tiempo_Competencias;
    }

    public void addEvento(TiempoCompetencia tiempo_Competencia) {
        tiempo_Competencia.setEventoFK(this);
        this.list_Tiempo_Competencias.add(tiempo_Competencia);

    }

}
