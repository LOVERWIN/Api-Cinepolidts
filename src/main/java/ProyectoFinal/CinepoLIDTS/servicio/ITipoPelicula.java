package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.TipoPelicula;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ITipoPelicula {

    List<TipoPelicula> consultarTodosTiposPelicula();
    ResponseEntity<?> consultarUno(Integer idTipoPelicula);
    ResponseEntity<Map<String, String>> actualizarTipoPelicula(TipoPelicula tipoPelicula);
    ResponseEntity<Map<String, String>> insertarTipoPelicula(TipoPelicula tipoPelicula);
    ResponseEntity<?> eliminarTipoPelicula(Integer idTipoPelicula);
}
