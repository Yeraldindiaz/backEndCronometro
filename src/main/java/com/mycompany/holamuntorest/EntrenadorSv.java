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

    EntrenadorFachada entrenadorFachada;
    ConversorDTO conversor;
    
    public EntrenadorSv() throws ConexionException {
        entrenadorFachada = new EntrenadorFachada();
        conversor = new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<EntrenadorDTO> getEntrenadors_JSON() {
        List<Entrenador> listaEntrenadors = entrenadorFachada.findAll();
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
        return conversor.entrenadorToDTO(entrenadorFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public EntrenadorDTO crearEntrenador(EntrenadorDTO usr) {
        try {
            entrenadorFachada.save(conversor.dtoToEntrenador(usr));
            return usr;
        } catch (Exception e) {
            return null;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public EntrenadorDTO updateEntrenador(EntrenadorDTO usr) {
        return conversor.entrenadorToDTO(entrenadorFachada.update(conversor.dtoToEntrenador(usr)));
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteEntrenador(@PathParam("usrNo") int usrNo) {
        Entrenador u = entrenadorFachada.get(usrNo);
        entrenadorFachada.delete(u);
    }
    
}
