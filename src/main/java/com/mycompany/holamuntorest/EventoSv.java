package com.mycompany.holamuntorest;

import edu.sergioArboleda.facade.EventoFachada;
import com.mycompany.jpa.Evento;
import org.o7planning.restfulcrud.model.EventoDTO;
import edu.sergioArboleda.exception.ConexionException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/evento")
public class EventoSv {

    EventoFachada EventoFachada;
    ConversorDTO conversor;
    
    public EventoSv() throws ConexionException {
        EventoFachada = new EventoFachada();
        conversor = new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<EventoDTO> getEventos_JSON() {
        List<Evento> listaEventos = EventoFachada.findAll();
        List<EventoDTO> EventoDTOs = new ArrayList<>();
        for (Evento listaEvento : listaEventos) {
            EventoDTOs.add(conversor.eventoToDTO(listaEvento));
        }
        return EventoDTOs;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public EventoDTO getEvento(@PathParam("usrNo") int usrNo) {
        return conversor.eventoToDTO(EventoFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearEvento(Evento usr) {
        try {
            EventoFachada.save(usr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Evento updateEvento(Evento usr) {
        return EventoFachada.update(usr);
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteEvento(@PathParam("usrNo") int usrNo) {
        Evento u = EventoFachada.get(usrNo);
        EventoFachada.delete(u);
    }

}
