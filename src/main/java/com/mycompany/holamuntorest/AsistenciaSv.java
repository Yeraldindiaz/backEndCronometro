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

    AsistenciaFachada asistenciaFachada;
    ConversorDTO conversor;

    public AsistenciaSv() throws ConexionException {
        asistenciaFachada = new AsistenciaFachada();
        conversor= new ConversorDTO();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<AsistenciaDTO> getAsistencias_JSON() {
        List<Asistencia> listaAsistencias = asistenciaFachada.findAll();
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
        return conversor.asistenciaToDTO(asistenciaFachada.get(usrNo));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AsistenciaDTO crearAsistencia(AsistenciaDTO usr) {
        try {
            asistenciaFachada.save(conversor.dotToAsistencia(usr));
            return usr;
        } catch (Exception e) {
            return null;
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AsistenciaDTO updateAsistencia(AsistenciaDTO usr) {
        return conversor.asistenciaToDTO(asistenciaFachada.update(conversor.dotToAsistencia(usr)));
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteAsistencia(@PathParam("usrNo") int usrNo) {
        Asistencia u = asistenciaFachada.get(usrNo);
        asistenciaFachada.delete(u);
    }

}
