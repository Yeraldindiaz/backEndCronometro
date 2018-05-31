package com.mycompany.holamuntorest;

import edu.sergioArboleda.facade.DeportesFachada;
import com.mycompany.jpa.Deporte;
import org.o7planning.restfulcrud.model.DeporteDTO;
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

@Path("/deporte")
public class DeporteSv {

    DeportesFachada deportesFachada;
    ConversorDTO conversor;

    public DeporteSv() throws ConexionException {
        deportesFachada = new DeportesFachada();
        conversor = new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<DeporteDTO> getDeportess_JSON() {
        List<Deporte> listaDeportess = deportesFachada.findAll();
        List<DeporteDTO> DeportesDTOs = new ArrayList<>();
        for (Deporte listaDeportes : listaDeportess) {
            DeportesDTOs.add(conversor.deportesToDTO(listaDeportes));
        }
        return DeportesDTOs;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeporteDTO getDeportes(@PathParam("usrNo") int usrNo) {
        return conversor.deportesToDTO(deportesFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeporteDTO crearDeportes(DeporteDTO usr) {
        try {
            deportesFachada.save(conversor.dtoToDeportes(usr));
            return usr;
        } catch (Exception e) {
            return null;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeporteDTO updateDeportes(DeporteDTO usr) {
        return conversor.deportesToDTO(deportesFachada.update(conversor.dtoToDeportes(usr)));
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteDeportes(@PathParam("usrNo") int usrNo) {
        Deporte u = deportesFachada.get(usrNo);
        deportesFachada.delete(u);
    }

}
