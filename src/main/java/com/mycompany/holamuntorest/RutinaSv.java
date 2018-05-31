package com.mycompany.holamuntorest;

import edu.sergioArboleda.facade.RutinaFachada;
import com.mycompany.jpa.Rutina;
import org.o7planning.restfulcrud.model.RutinaDTO;
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

@Path("/rutina")
public class RutinaSv {

    RutinaFachada RutinaFachada;
    ConversorDTO conversor;

    public RutinaSv() throws ConexionException {
        RutinaFachada = new RutinaFachada();
        conversor = new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<RutinaDTO> getRutinas_JSON() {
        List<Rutina> listaRutinas = RutinaFachada.findAll();
        List<RutinaDTO> RutinaDTOs = new ArrayList<>();
        for (Rutina listaRutina : listaRutinas) {
            RutinaDTOs.add(conversor.rutinaToDTO(listaRutina));
        }
        return RutinaDTOs;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RutinaDTO getRutina(@PathParam("usrNo") int usrNo) {
        return conversor.rutinaToDTO(RutinaFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearRutina(RutinaDTO usr) {
        try {
            RutinaFachada.save(conversor.dtoToRutina(usr));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Rutina updateRutina(RutinaDTO usr) {
        return RutinaFachada.update(conversor.dtoToRutina(usr));
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteRutina(@PathParam("usrNo") int usrNo) {
        Rutina u = RutinaFachada.get(usrNo);
        RutinaFachada.delete(u);
    }

}
