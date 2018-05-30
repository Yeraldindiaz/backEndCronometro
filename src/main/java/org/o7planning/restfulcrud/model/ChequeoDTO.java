/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.o7planning.restfulcrud.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MARIO
 */
@XmlRootElement(name = "ChequeoDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChequeoDTO {

    private int ID_Chequeo;
    private String prueba;
    private int distancia;
    private String tiempo;
    private DeportistaDTO deportistaDTO;

    public int getID_Chequeo() {
        return ID_Chequeo;
    }

    public void setID_Chequeo(int ID_Chequeo) {
        this.ID_Chequeo = ID_Chequeo;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public DeportistaDTO getDeportistaDTO() {
        return deportistaDTO;
    }

    public void setDeportistaDTO(DeportistaDTO deportistaDTO) {
        this.deportistaDTO = deportistaDTO;
    }

}
