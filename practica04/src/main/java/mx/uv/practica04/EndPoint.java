package mx.uv.practica04;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.BuscarRequest;
import https.t4is_uv_mx.saludos.BuscarResponse;
import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;
import https.t4is_uv_mx.saludos.ModificarRequest;
import https.t4is_uv_mx.saludos.ModificarResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;
import https.t4is_uv_mx.saludos.VerResponse;


@Endpoint
public class EndPoint {
    @Autowired
    private ISaludador iSaludador;

    List<String> msj = new ArrayList<String>();
    String nombres;

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola "+peticion.getNombre());
        msj.add(peticion.getNombre());
        Saludador saludador = new Saludador();
        saludador.setNombre(peticion.getNombre());
        iSaludador.save(saludador);
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarResponse Saludar(@RequestPayload BuscarRequest peticion){
        BuscarResponse respuesta = new BuscarResponse();
        Saludador saludador = new Saludador();
        saludador = iSaludador.findById(peticion.getId()).get();
        respuesta.setRespuesta(saludador.getNombre());
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarResponse Modificar(@RequestPayload ModificarRequest peticion){
        ModificarResponse respuesta = new ModificarResponse();
        Saludador saludador = new Saludador();
        saludador = iSaludador.findById(peticion.getId()).get();
        saludador.setNombre(peticion.getNombre());
        iSaludador.save(saludador);
        respuesta.setRespuesta("Se ha modificado el elemento "+peticion.getId());
        return respuesta;
    }

    @PayloadRoot(localPart = "VerRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public VerResponse Ver(){
        VerResponse respuesta = new VerResponse();
        Iterable<Saludador> lista = iSaludador.findAll();
        for(Saludador elemento: lista){
            respuesta.getRespuesta().add("Nombre: "+elemento.getNombre()+" ID: "+elemento.getId());
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "EliminarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EliminarResponse Eliminar(@RequestPayload EliminarRequest peticion){
        EliminarResponse respuesta = new EliminarResponse();
        Saludador saludador = new Saludador();
        saludador = iSaludador.findById(peticion.getId()).get();
        iSaludador.deleteById(peticion.getId());
        respuesta.setRespuesta("Se ha eliminado el elemento "+saludador.getNombre());
        return respuesta;
    }
    
}
