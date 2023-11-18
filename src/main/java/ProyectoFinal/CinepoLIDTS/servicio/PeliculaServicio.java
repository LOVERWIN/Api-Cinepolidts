package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.Pelicula;
import ProyectoFinal.CinepoLIDTS.repositorio.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PeliculaServicio implements IPelicula{


    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    @Override
    public List<Pelicula> consultarTodasPeliculas() {
        return peliculaRepositorio.findAll();
    }

    @Override
    public ResponseEntity<Pelicula> consultarUna(Integer idPelicula) {
        Pelicula pelicula = peliculaRepositorio.findById(idPelicula).orElse(null);
        if (pelicula == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(pelicula);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarPelicula(Pelicula pelicula) {
        pelicula = peliculaRepositorio.save(pelicula);
        Map<String, String> response = new HashMap<>();
        if (peliculaRepositorio.findById(pelicula.getIdPelicula()).orElse(null) == null) {
            // La película no existe, devuelve un ResponseEntity con un mensaje de error
            response.put("mensaje", "La película con el ID " + pelicula.getIdPelicula() + " no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (pelicula ==null){
            response.put("mensaje", "Erro al guardar la pelicula.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Actualiza la película existente con los datos proporcionados
//        peliculaExistente.setImagePelicula(pelicula.getImagePelicula());
//        peliculaExistente.setUrlTrailer(pelicula.getUrlTrailer());
//        peliculaExistente.setNombre(pelicula.getNombre());
//        peliculaExistente.setMinutos(pelicula.getMinutos());
//        peliculaExistente.setClasificacion(pelicula.getClasificacion());
//        peliculaExistente.setYearDate(pelicula.getYearDate());
//        peliculaExistente.setSinopsis(pelicula.getSinopsis());
//        peliculaExistente.setDirector(pelicula.getDirector());
//        peliculaExistente.setTipoPelicula(pelicula.getTipoPelicula());



        // Devuelve un ResponseEntity con un mensaje de éxito

        response.put("mensaje", "Película actualizada con éxito.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarPelicula(Pelicula pelicula) {
        // Lógica para insertar una nueva película en el repositorio
        Map<String, String> response = new HashMap<>();
        pelicula = peliculaRepositorio.save(pelicula);
        if ( pelicula == null){
            response.put("mensaje", "No se pudo guardar.");
        }
        // Devuelve un ResponseEntity con un mensaje de éxito
        response.put("mensaje", "Película insertada con éxito.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> eliminarPelicula(Integer idPelicula) {
        // Lógica para eliminar la película por ID
        peliculaRepositorio.deleteById(idPelicula);

        // Devuelve un ResponseEntity con un mensaje de éxito
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Película eliminada con éxito.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public List<Pelicula> consultarPeliculasPorAnio(String yearDate) {
        return peliculaRepositorio.findByyearDate(yearDate);
    }

    @Override
    public ResponseEntity<Map<String, String>> ActualizarNombrePeliculas(Integer idPelicula,String nombre) {
        Map<String, String> response = new HashMap<>();
        try {
            int rowsUpdated = peliculaRepositorio.actualizarNombrePelicula(idPelicula, nombre);
            if (rowsUpdated > 0) {
                response.put("mensaje", "Nombre de película actualizado con éxito.");
                return ResponseEntity.ok(response);
            } else {
                response.put("mensaje", "No se encontró la película con el ID proporcionado.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar el nombre de la película.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Pelicula>> listarPeliculasDirector(Integer idDirector) {
        try {
            List<Pelicula> peliculas = peliculaRepositorio.findByDirector_IdDirector(idDirector);
            if (peliculas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(peliculas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
