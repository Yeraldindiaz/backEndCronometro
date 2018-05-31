package com.mycompany.holamuntorest;

import edu.sergioArboleda.facade.DeportistaFachada;
import com.mycompany.jpa.Deportista;
import org.o7planning.restfulcrud.model.DeportistaDTO;
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

@Path("/deportista")
public class DeportistaSv {

    DeportistaFachada DeportistaFachada;
    ConversorDTO conversor;

    public DeportistaSv() throws ConexionException {
        DeportistaFachada = new DeportistaFachada();
        conversor = new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<DeportistaDTO> getDeportistas_JSON() {
        List<Deportista> listaDeportistas = DeportistaFachada.findAll();
        List<DeportistaDTO> DeportistaDTOs = new ArrayList<>();
        for (Deportista listaDeportista : listaDeportistas) {
            DeportistaDTOs.add(conversor.deportistaToDTO(listaDeportista));
        }
        return DeportistaDTOs;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeportistaDTO getDeportista(@PathParam("usrNo") int usrNo) {
        return conversor.deportistaToDTO(DeportistaFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearDeportista(DeportistaDTO usr) {
        try {
            DeportistaFachada.save(conversor.dtoToDeportista(usr));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Deportista updateDeportista(DeportistaDTO usr) {
        return DeportistaFachada.update(conversor.dtoToDeportista(usr));
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteDeportista(@PathParam("usrNo") int usrNo) {
        Deportista u = DeportistaFachada.get(usrNo);
        DeportistaFachada.delete(u);
    }
    
}
