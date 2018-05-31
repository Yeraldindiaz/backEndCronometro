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

    DeportesFachada DeportesFachada;
    ConversorDTO conversor;

    public DeporteSv() throws ConexionException {
        DeportesFachada = new DeportesFachada();
        conversor = new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<DeporteDTO> getDeportess_JSON() {
        List<Deporte> listaDeportess = DeportesFachada.findAll();
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
        return conversor.deportesToDTO(DeportesFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearDeportes(DeporteDTO usr) {
        try {
            DeportesFachada.save(conversor.dtoToDeportes(usr));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Deporte updateDeportes(DeporteDTO usr) {
        return DeportesFachada.update(conversor.dtoToDeportes(usr));
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteDeportes(@PathParam("usrNo") int usrNo) {
        Deporte u = DeportesFachada.get(usrNo);
        DeportesFachada.delete(u);
    }

}
