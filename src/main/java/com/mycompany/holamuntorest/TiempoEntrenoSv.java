package com.mycompany.holamuntorest;

import edu.sergioArboleda.facade.TiempoEntrenoFachada;
import com.mycompany.jpa.TiempoEntreno;
import org.o7planning.restfulcrud.model.TiempoEntrenoDTO;
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

@Path("/tiempoEntreno")
public class TiempoEntrenoSv {

    TiempoEntrenoFachada TiempoEntrenoFachada;
    ConversorDTO conversor;

    public TiempoEntrenoSv() throws ConexionException {
        TiempoEntrenoFachada = new TiempoEntrenoFachada();
        conversor = new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<TiempoEntrenoDTO> getTiempoEntrenos_JSON() {
        List<TiempoEntreno> listaTiempoEntrenos = TiempoEntrenoFachada.findAll();
        List<TiempoEntrenoDTO> TiempoEntrenoDTOs = new ArrayList<>();
        for (TiempoEntreno listaTiempoEntreno : listaTiempoEntrenos) {
            TiempoEntrenoDTOs.add(conversor.tiempoEntrenoToDTO(listaTiempoEntreno));
        }
        return TiempoEntrenoDTOs;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public TiempoEntrenoDTO getTiempoEntreno(@PathParam("usrNo") int usrNo) {
        return conversor.tiempoEntrenoToDTO(TiempoEntrenoFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearTiempoEntreno(TiempoEntreno usr) {
        try {
            TiempoEntrenoFachada.save(usr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public TiempoEntreno updateTiempoEntreno(TiempoEntreno usr) {
        return TiempoEntrenoFachada.update(usr);
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteTiempoEntreno(@PathParam("usrNo") int usrNo) {
        TiempoEntreno u = TiempoEntrenoFachada.get(usrNo);
        TiempoEntrenoFachada.delete(u);
    }

}
