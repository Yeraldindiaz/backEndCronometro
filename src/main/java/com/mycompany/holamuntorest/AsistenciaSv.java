package com.mycompany.holamuntorest;

import edu.sergioArboleda.facade.AsistenciaFachada;
import com.mycompany.jpa.Asistencia;
import org.o7planning.restfulcrud.model.AsistenciaDTO;
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

@Path("/asistencia")
public class AsistenciaSv {

    AsistenciaFachada AsistenciaFachada;
    ConversorDTO conversor;

    public AsistenciaSv() throws ConexionException {
        AsistenciaFachada = new AsistenciaFachada();
        conversor= new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<AsistenciaDTO> getAsistencias_JSON() {
        List<Asistencia> listaAsistencias = AsistenciaFachada.findAll();
        List<AsistenciaDTO> AsistenciaDTOs = new ArrayList<>();
        for (Asistencia listaAsistencia : listaAsistencias) {
            AsistenciaDTOs.add(conversor.asistenciaToDTO(listaAsistencia));
        }
        return AsistenciaDTOs;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AsistenciaDTO getAsistencia(@PathParam("usrNo") int usrNo) {
        return conversor.asistenciaToDTO(AsistenciaFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearAsistencia(AsistenciaDTO usr) {
        try {
            AsistenciaFachada.save(conversor.dotToAsistencia(usr));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Asistencia updateAsistencia(AsistenciaDTO usr) {
        return AsistenciaFachada.update(conversor.dotToAsistencia(usr));
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteAsistencia(@PathParam("usrNo") int usrNo) {
        Asistencia u = AsistenciaFachada.get(usrNo);
        AsistenciaFachada.delete(u);
    }

}
