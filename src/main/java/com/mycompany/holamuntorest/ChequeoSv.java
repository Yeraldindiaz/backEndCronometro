package com.mycompany.holamuntorest;

import edu.sergioArboleda.facade.ChequeoFachada;
import com.mycompany.jpa.Chequeo;
import org.o7planning.restfulcrud.model.ChequeoDTO;
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

@Path("/chequeo")
public class ChequeoSv {

    ChequeoFachada chequeoFachada;
    ConversorDTO conversor;
    
    public ChequeoSv() throws ConexionException {
        chequeoFachada = new ChequeoFachada();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<ChequeoDTO> getChequeos_JSON() {
        List<Chequeo> listaChequeos = chequeoFachada.findAll();
        List<ChequeoDTO> ChequeoDTOs = new ArrayList<>();
        for (Chequeo listaChequeo : listaChequeos) {
            ChequeoDTOs.add(conversor.chequeoToDTO(listaChequeo));
        }
        return ChequeoDTOs;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ChequeoDTO getChequeo(@PathParam("usrNo") int usrNo) {
        return conversor.chequeoToDTO(chequeoFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ChequeoDTO crearChequeo(ChequeoDTO usr) {
        try {
            chequeoFachada.save(conversor.dtoTochequeo(usr));
            return usr;
        } catch (Exception e) {
            return null;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ChequeoDTO updateChequeo(ChequeoDTO usr) {
        return conversor.chequeoToDTO(chequeoFachada.update(conversor.dtoTochequeo(usr)));
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteChequeo(@PathParam("usrNo") int usrNo) {
        Chequeo u = chequeoFachada.get(usrNo);
        chequeoFachada.delete(u);
    }

}
