package com.mycompany.holamuntorest;

import edu.sergioArboleda.facade.EntrenadorFachada;
import com.mycompany.jpa.Entrenador;
import org.o7planning.restfulcrud.model.EntrenadorDTO;
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

@Path("/entrenador")
public class EntrenadorSv {

    EntrenadorFachada EntrenadorFachada;
    ConversorDTO conversor;
    
    public EntrenadorSv() throws ConexionException {
        EntrenadorFachada = new EntrenadorFachada();
        conversor = new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<EntrenadorDTO> getEntrenadors_JSON() {
        List<Entrenador> listaEntrenadors = EntrenadorFachada.findAll();
        List<EntrenadorDTO> EntrenadorDTOs = new ArrayList<>();
        for (Entrenador listaEntrenador : listaEntrenadors) {
            EntrenadorDTOs.add(conversor.entrenadorToDTO(listaEntrenador));
        }
        return EntrenadorDTOs;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public EntrenadorDTO getEntrenador(@PathParam("usrNo") int usrNo) {
        return conversor.entrenadorToDTO(EntrenadorFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearEntrenador(EntrenadorDTO usr) {
        try {
            EntrenadorFachada.save(conversor.dtoToEntrenador(usr));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Entrenador updateEntrenador(EntrenadorDTO usr) {
        return EntrenadorFachada.update(conversor.dtoToEntrenador(usr));
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteEntrenador(@PathParam("usrNo") int usrNo) {
        Entrenador u = EntrenadorFachada.get(usrNo);
        EntrenadorFachada.delete(u);
    }
    
}
