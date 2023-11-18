package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.Actor;
import ProyectoFinal.CinepoLIDTS.modelo.Director;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IDirector {

    ResponseEntity<?> consultarTodosDirectores();
    ResponseEntity<?> consultarUno(Integer idDirector);
    ResponseEntity<Map<String, String>> actualizarDirector(Director director);
    ResponseEntity<Map<String, String>> insertarDirector(Director director);
    ResponseEntity<?> eliminarDirector(Integer idDirector);
    ResponseEntity<?> consultarbyNombre(String nombreDirector);
}
