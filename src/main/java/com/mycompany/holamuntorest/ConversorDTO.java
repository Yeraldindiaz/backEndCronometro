package com.mycompany.holamuntorest;

import com.mycompany.jpa.Asistencia;
import com.mycompany.jpa.Chequeo;
import com.mycompany.jpa.Deporte;
import com.mycompany.jpa.Deportista;
import com.mycompany.jpa.Entrenador;
import com.mycompany.jpa.Evento;
import com.mycompany.jpa.Rutina;
import com.mycompany.jpa.TiempoCompetencia;
import com.mycompany.jpa.TiempoEntreno;
import org.o7planning.restfulcrud.model.AsistenciaDTO;
import org.o7planning.restfulcrud.model.ChequeoDTO;
import org.o7planning.restfulcrud.model.DeporteDTO;
import org.o7planning.restfulcrud.model.DeportistaDTO;
import org.o7planning.restfulcrud.model.EntrenadorDTO;
import org.o7planning.restfulcrud.model.EventoDTO;
import org.o7planning.restfulcrud.model.RutinaDTO;
import org.o7planning.restfulcrud.model.TiempoCompetenciaDTO;
import org.o7planning.restfulcrud.model.TiempoEntrenoDTO;

public class ConversorDTO {

    public AsistenciaDTO asistenciaToDTO(Asistencia a) {
        AsistenciaDTO asistenciaDTO = new AsistenciaDTO();
        asistenciaDTO.setJornada(a.getJornada());
        asistenciaDTO.setFecha(a.getFecha());
        asistenciaDTO.setID_Asistencia(a.getID_Asistencia());
        asistenciaDTO.setEntrenadorDTO(entrenadorToDTO(a.getEntrenadorFK()));
        asistenciaDTO.setDeportistaDTO(deportistaToDTO(a.getDeportistaFK()));
        return asistenciaDTO;
    }
    
    public Asistencia dotToAsistencia(AsistenciaDTO a) {
        Asistencia asistencia = new Asistencia();
        asistencia.setJornada(a.getJornada());
        asistencia.setFecha(a.getFecha());
        asistencia.setID_Asistencia(a.getID_Asistencia());
        asistencia.setEntrenadorFK(dtoToEntrenador(a.getEntrenadorDTO()));
        asistencia.setDeportistaFK(dtoToDeportista(a.getDeportistaDTO()));
        return asistencia;
    }

    public ChequeoDTO chequeoToDTO(Chequeo a) {
        ChequeoDTO chequeoDTO = new ChequeoDTO();
        chequeoDTO.setID_Chequeo(a.getID_Chequeo());
        chequeoDTO.setPrueba(a.getPrueba());
        chequeoDTO.setDistancia(a.getDistancia());
        chequeoDTO.setTiempo(a.getTiempo());
        chequeoDTO.setDeportistaDTO(deportistaToDTO(a.getDeportistaFK()));
        return chequeoDTO;
    }
    
    public Chequeo dtoTochequeo(ChequeoDTO a) {
        Chequeo chequeo = new Chequeo();
        chequeo.setID_Chequeo(a.getID_Chequeo());
        chequeo.setPrueba(a.getPrueba());
        chequeo.setDistancia(a.getDistancia());
        chequeo.setTiempo(a.getTiempo());
        chequeo.setDeportistaFK(dtoToDeportista(a.getDeportistaDTO()));
        return chequeo;
    }

    public DeportistaDTO deportistaToDTO(Deportista a) {
        DeportistaDTO deportistaDTO = new DeportistaDTO();
        deportistaDTO.setID_Deportista(a.getID_Deportista());
        deportistaDTO.setNombre(a.getNombre());
        deportistaDTO.setApellido(a.getApellido());
        deportistaDTO.setFechaNacimiento(a.getFechaNacimiento());
        deportistaDTO.setCedula(a.getCedula());
        deportistaDTO.setCiudad(a.getCiudad());
        deportistaDTO.setCategoria(a.getCategoria());
        deportistaDTO.setContrasena(a.getContrasena());
        deportistaDTO.setDeporteFK(deportesToDTO(a.getDeporteFK()));
        deportistaDTO.setEntrenadorFK(entrenadorToDTO(a.getEntrenadorFK()));
        return deportistaDTO;
    }
    
    public Deportista dtoToDeportista(DeportistaDTO a) {
        Deportista deportista=  new Deportista();
        deportista.setID_Deportista(a.getID_Deportista());
        deportista.setNombre(a.getNombre());
        deportista.setApellido(a.getApellido());
        deportista.setFechaNacimiento(a.getFechaNacimiento());
        deportista.setCedula(a.getCedula());
        deportista.setCiudad(a.getCiudad());
        deportista.setCategoria(a.getCategoria());
        deportista.setContrasena(a.getContrasena());
        deportista.setDeporteFK(dtoToDeportes(a.getDeporteFK()));
        deportista.setEntrenadorFK(dtoToEntrenador(a.getEntrenadorFK()));
        return deportista;
    }

    public RutinaDTO rutinaToDTO(Rutina a) {
        RutinaDTO rutinaDTO = new RutinaDTO();
        rutinaDTO.setID_Rutina(a.getID_Rutina());
        rutinaDTO.setJornada(a.getJornada());
        rutinaDTO.setFecha(a.getFecha());
        rutinaDTO.setEstilo(a.getEstilo());
        rutinaDTO.setDistancia(a.getDistancia());
        rutinaDTO.setRepeticiones(a.getRepeticiones());
        rutinaDTO.setEntrenadorDTO(entrenadorToDTO(a.getEntrenadorFK()));
        return rutinaDTO;
    }
    
    public Rutina dtoToRutina(RutinaDTO a) {
        Rutina rutina = new Rutina();
        rutina.setID_Rutina(a.getID_Rutina());
        rutina.setJornada(a.getJornada());
        rutina.setFecha(a.getFecha());
        rutina.setEstilo(a.getEstilo());
        rutina.setDistancia(a.getDistancia());
        rutina.setRepeticiones(a.getRepeticiones());
        rutina.setEntrenadorFK(dtoToEntrenador(a.getEntrenadorDTO()));
        return rutina;
    }

    public EventoDTO eventoToDTO(Evento a) {
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setID_Evento(a.getID_Evento());
        eventoDTO.setFecha(a.getFecha());
        eventoDTO.setNombre(a.getNombre());
        eventoDTO.setLugar(a.getLugar());
        eventoDTO.setTipo(a.getTipo());
        eventoDTO.setDeporteFK(deportesToDTO(a.getDeport_FK()));
        return eventoDTO;
    }
    
    public Evento dtoToEvento(EventoDTO a) {
        Evento evento = new Evento();
        evento.setID_Evento(a.getID_Evento());
        evento.setFecha(a.getFecha());
        evento.setNombre(a.getNombre());
        evento.setLugar(a.getLugar());
        evento.setTipo(a.getTipo());
        evento.setDeport_FK(dtoToDeportes(a.getDeporteFK()));
        return evento;
    }

    public TiempoEntrenoDTO tiempoEntrenoToDTO(TiempoEntreno a) {
        TiempoEntrenoDTO tiempoEntrenoDTO = new TiempoEntrenoDTO();
        tiempoEntrenoDTO.setID_tiempos(a.getID_tiempos());
        tiempoEntrenoDTO.setSerie(a.getSerie());
        tiempoEntrenoDTO.setTiempo(a.getTiempo());
        tiempoEntrenoDTO.setTiempodescanso(a.getTiempodescanso());
        tiempoEntrenoDTO.setTiempoexigencia(a.getTiempoexigencia());
        tiempoEntrenoDTO.setDeportistaDTO(deportistaToDTO(a.getDeportistaFK()));
        tiempoEntrenoDTO.setRutinaDTO(rutinaToDTO(a.getRutinaFK()));
        return tiempoEntrenoDTO;
    }
    
    public TiempoEntreno dtoToTiempoEntreno(TiempoEntrenoDTO a) {
        TiempoEntreno tiempoEntrenoDTO = new TiempoEntreno();
        tiempoEntrenoDTO.setID_tiempos(a.getID_tiempos());
        tiempoEntrenoDTO.setSerie(a.getSerie());
        tiempoEntrenoDTO.setTiempo(a.getTiempo());
        tiempoEntrenoDTO.setTiempodescanso(a.getTiempodescanso());
        tiempoEntrenoDTO.setTiempoexigencia(a.getTiempoexigencia());
        tiempoEntrenoDTO.setDeportistaFK(dtoToDeportista(a.getDeportistaDTO()));
        tiempoEntrenoDTO.setRutinaFK(dtoToRutina(a.getRutinaDTO()));
        return tiempoEntrenoDTO;
    }

    public EntrenadorDTO entrenadorToDTO(Entrenador a) {
        EntrenadorDTO entrenadorDTO = new EntrenadorDTO();
        entrenadorDTO.setID_Entrenador(a.getID_Entrenador());
        entrenadorDTO.setNombre(a.getNombre());
        entrenadorDTO.setApellido(a.getApellido());
        entrenadorDTO.setCedula(a.getCedula());
        entrenadorDTO.setCiudad(a.getCiudad());
        entrenadorDTO.setContraseña(a.getContrasena());
        entrenadorDTO.setFechaNacimiento(a.getFechaNacimiento());
        entrenadorDTO.setDeporteFK(deportesToDTO(a.getDeporteFK()));
        return entrenadorDTO;
    }

    public Entrenador dtoToEntrenador(EntrenadorDTO a) {
        Entrenador entrenador = new Entrenador();
        entrenador.setID_Entrenador(a.getID_Entrenador());
        entrenador.setNombre(a.getNombre());
        entrenador.setApellido(a.getApellido());
        entrenador.setCedula(a.getCedula());
        entrenador.setCiudad(a.getCiudad());
        entrenador.setContrasena(a.getContrasena());
        entrenador.setFechaNacimiento(a.getFechaNacimiento());
        entrenador.setDeporteFK(dtoToDeportes(a.getDeporteFK()));
        return entrenador;
    }

    public DeporteDTO deportesToDTO(Deporte a) {
        DeporteDTO deportesDTO = new DeporteDTO();
        deportesDTO.setID_Deporte(a.getID_Deporte());
        deportesDTO.setNombre(a.getNombre());
        deportesDTO.setDescripcion(a.getDescripcion());
        return deportesDTO;
    }

    public Deporte dtoToDeportes(DeporteDTO a) {
        Deporte deporte = new Deporte();
        deporte.setID_Deporte(a.getID_Deporte());
        deporte.setNombre(a.getNombre());
        deporte.setDescripcion(a.getDescripcion());
        return deporte;
    }

    public TiempoCompetenciaDTO tiempoCompetenciaToDTO(TiempoCompetencia a) {
        TiempoCompetenciaDTO competenciaDTO = new TiempoCompetenciaDTO();
        competenciaDTO.setID_tiempos(a.getID_tiempos());
        competenciaDTO.setPrueba(a.getPrueba());
        competenciaDTO.setDistancia(a.getDistancia());
        competenciaDTO.setTiempo(a.getTiempo());
        competenciaDTO.setCategoria(a.getCategoria());
        competenciaDTO.setEventoDTO(eventoToDTO(a.getEventoFK()));
        competenciaDTO.setDeportistaDTO(deportistaToDTO(a.getDeportistaFK()));
        return competenciaDTO;
    }

    public TiempoCompetencia dtoToTiempoCompetencia(TiempoCompetenciaDTO a) {
        TiempoCompetencia competencia = new TiempoCompetencia();
        competencia.setID_tiempos(a.getID_tiempos());
        competencia.setPrueba(a.getPrueba());
        competencia.setDistancia(a.getDistancia());
        competencia.setTiempo(a.getTiempo());
        competencia.setCategoria(a.getCategoria());
        competencia.setEventoFK(dtoToEvento(a.getEventoDTO()));
        competencia.setDeportistaFK(dtoToDeportista(a.getDeportistaDTO()));
        return competencia;
    }
}
