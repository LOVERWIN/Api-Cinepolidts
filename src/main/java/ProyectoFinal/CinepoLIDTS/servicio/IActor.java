package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.Actor;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IActor {

    ResponseEntity<?> consultarTodosActores();
    ResponseEntity<?> consultarUno(Integer idActor);
    ResponseEntity<Map<String, String>> actualizarActor(Actor actor);
    ResponseEntity<Map<String, String>> insertarActor(Actor actor);
    ResponseEntity<?> eliminarActor(Integer idActor);
    ResponseEntity<?> consultarbyNombre(String nombreActor);


}
