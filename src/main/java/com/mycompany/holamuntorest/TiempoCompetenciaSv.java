package com.mycompany.holamuntorest;

import edu.sergioArboleda.facade.TiempoCompetenciaFachada;
import com.mycompany.jpa.TiempoCompetencia;
import org.o7planning.restfulcrud.model.TiempoCompetenciaDTO;
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

@Path("tiempoCompetencia")
public class TiempoCompetenciaSv {

    TiempoCompetenciaFachada TiempoCompetenciaFachada;
    ConversorDTO conversor;

    public TiempoCompetenciaSv() throws ConexionException {
        TiempoCompetenciaFachada = new TiempoCompetenciaFachada();
        conversor= new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<TiempoCompetenciaDTO> getTiempoCompetencias_JSON() {
        List<TiempoCompetencia> listaTiempoCompetencias = TiempoCompetenciaFachada.findAll();
        List<TiempoCompetenciaDTO> TiempoCompetenciaDTOs = new ArrayList<>();
        for (TiempoCompetencia listaTiempoCompetencia : listaTiempoCompetencias) {
            TiempoCompetenciaDTOs.add(conversor.tiempoCompetenciaToDTO(listaTiempoCompetencia));
        }
        return TiempoCompetenciaDTOs;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public TiempoCompetenciaDTO getTiempoCompetencia(@PathParam("usrNo") int usrNo) {
        return conversor.tiempoCompetenciaToDTO(TiempoCompetenciaFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearTiempoCompetencia(TiempoCompetenciaDTO usr) {
        try {
            TiempoCompetenciaFachada.save(conversor.dtoToTiempoCompetencia(usr));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public TiempoCompetencia updateTiempoCompetencia(TiempoCompetenciaDTO usr) {
        return TiempoCompetenciaFachada.update(conversor.dtoToTiempoCompetencia(usr));
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteTiempoCompetencia(@PathParam("usrNo") int usrNo) {
        TiempoCompetencia u = TiempoCompetenciaFachada.get(usrNo);
        TiempoCompetenciaFachada.delete(u);
    }

}
