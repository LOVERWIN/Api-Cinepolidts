package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.Actor;
import ProyectoFinal.CinepoLIDTS.modelo.Hora;
import ProyectoFinal.CinepoLIDTS.modelo.Pelicula;
import ProyectoFinal.CinepoLIDTS.modelo.PeliculaActor;
import ProyectoFinal.CinepoLIDTS.repositorio.PeliculaActorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PeliculaActorServicio implements IPeliculaActor {

    @Autowired
    private PeliculaActorRepositorio peliculaActorRepositorio;

    @Override
    public List<PeliculaActor> consultarTodasPeliculasActores() {
        return peliculaActorRepositorio.findAll();
    }

    @Override
    public ResponseEntity<?> consultaridPelicula(Integer idPelicula) {
        List<PeliculaActor> peliculaActorOptional  = peliculaActorRepositorio.findByPelicula_IdPelicula(idPelicula);
        Map<String, Object> response = new HashMap<>();

        if (peliculaActorOptional.isEmpty() || peliculaActorOptional == null){
            response.put("mensaje", "No se encontró con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(peliculaActorOptional);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarPeliculaActor(PeliculaActor peliculaActor) {
        Map<String, String> response = new HashMap<>();
        if (!peliculaActorRepositorio.existsById(peliculaActor.getIdPeliculaActor())) {
            response.put("mensaje", "No se encontró una relación PeliculaActor con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (peliculaActor.getIdPeliculaActor().equals("") || peliculaActor.getIdPeliculaActor() == null){
            response.put("mensaje", "Error al actualizar los datos.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        peliculaActorRepositorio.save(peliculaActor);
        response.put("mensaje", "Actualizado correctamente ");
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarPeliculaActor(PeliculaActor peliculaActor) {
        Map<String, String> response = new HashMap<>();
        if (peliculaActor.getPelicula().getIdPelicula().equals("") ||peliculaActor.getActor().getIdActor().equals("")){
            if (peliculaActor.getPelicula().getIdPelicula() == null || peliculaActor.getActor().getIdActor() == null){
                response.put("mensaje", "Error al insertar.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }
        peliculaActorRepositorio.save(peliculaActor);
        response.put("mensaje", "Guardado Correctamente.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<?> eliminarPeliculaActor(Integer idPeliculaActor) {
        Map<String, String> response = new HashMap<>();
        if (!peliculaActorRepositorio.existsById(idPeliculaActor)) {
            response.put("mensaje", "No se encontró una relación PeliculaActor con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        peliculaActorRepositorio.deleteById(idPeliculaActor);
        response.put("mensaje", "Se ha eliminado correctamente.");

        return ResponseEntity.ok().body(response);
    }
}
