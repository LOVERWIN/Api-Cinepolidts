package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.Actor;
import ProyectoFinal.CinepoLIDTS.modelo.Pelicula;
import ProyectoFinal.CinepoLIDTS.modelo.PeliculaActor;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IPeliculaActor {

    List<PeliculaActor> consultarTodasPeliculasActores();
    ResponseEntity<?> consultaridPelicula(Integer idPelicula);
    ResponseEntity<Map<String, String>> actualizarPeliculaActor(PeliculaActor peliculaActor);
    ResponseEntity<Map<String, String>> insertarPeliculaActor(PeliculaActor peliculaActor);
    ResponseEntity<?> eliminarPeliculaActor(Integer abidPeliculaActor);
}
