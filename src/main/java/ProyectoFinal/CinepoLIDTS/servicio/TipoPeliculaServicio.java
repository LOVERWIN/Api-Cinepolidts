package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.TipoPelicula;
import ProyectoFinal.CinepoLIDTS.repositorio.TipoPeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class TipoPeliculaServicio implements ITipoPelicula {

    @Autowired
    private TipoPeliculaRepositorio tipoPeliculaRepositorio;

    @Override
    public List<TipoPelicula> consultarTodosTiposPelicula() {
        return tipoPeliculaRepositorio.findAll();
    }

    @Override
    public ResponseEntity<?> consultarUno(Integer idTipoPelicula) {
        Map<String, String> response = new HashMap<>();
        TipoPelicula tipoPelicula = tipoPeliculaRepositorio.findById(idTipoPelicula).orElse(null);
        if (tipoPelicula != null) {
            return ResponseEntity.ok(tipoPelicula);
        } else {
            response.put("mensaje", "No se encontro resultados con el ID: "+idTipoPelicula+".");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }


    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarTipoPelicula(TipoPelicula tipoPelicula) {
        Map<String, String> response = new HashMap<>();
        if (tipoPeliculaRepositorio.existsById(tipoPelicula.getIdTipo())) {
            tipoPeliculaRepositorio.save(tipoPelicula);
            response.put("mensaje", "Se actualizo correctamente.");
            return ResponseEntity.ok().body(response);
        } else {
            response.put("mensaje", "No se encontró un tipo de película con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarTipoPelicula(TipoPelicula tipoPelicula) {
        Map<String, String> response = new HashMap<>();
        tipoPelicula = tipoPeliculaRepositorio.save(tipoPelicula);
        if (tipoPelicula == null){
            response.put("mensaje", "Error al guardar el tipoPelicula.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("mensaje", "Insertado correctamente.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<?> eliminarTipoPelicula(Integer idTipoPelicula) {
        Map<String, String> response = new HashMap<>();

        if (tipoPeliculaRepositorio.existsById(idTipoPelicula)) {
            tipoPeliculaRepositorio.deleteById(idTipoPelicula);
            response.put("mensaje", "Tipo de película eliminado con éxito.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("mensaje", "No se encontró un tipo de película con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }
}
