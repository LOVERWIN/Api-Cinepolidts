package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.Pelicula;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IPelicula {
    List<Pelicula> consultarTodasPeliculas();
    ResponseEntity<Pelicula> consultarUna(Integer idPelicula);
    ResponseEntity<Map<String, String>> actualizarPelicula(Pelicula pelicula);
    ResponseEntity<Map<String, String>> insertarPelicula(Pelicula pelicula);
    ResponseEntity<?> eliminarPelicula(Integer idPelicula);
    List<Pelicula> consultarPeliculasPorAnio(String yearDate);
    ResponseEntity<Map<String, String>> ActualizarNombrePeliculas(Integer idPelicula,String nombre);
    ResponseEntity<List<Pelicula>> listarPeliculasDirector(Integer idDirector);
}
