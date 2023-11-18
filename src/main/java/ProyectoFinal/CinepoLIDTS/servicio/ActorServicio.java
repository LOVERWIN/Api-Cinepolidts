package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.Actor;

import ProyectoFinal.CinepoLIDTS.repositorio.ActorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ActorServicio implements IActor{
    @Autowired
    private ActorRepositorio actorRepositorio;

    @Override
    public ResponseEntity<?> consultarTodosActores() {
        List<Actor> actores = actorRepositorio.findAll();
        Map<String, Object> response = new HashMap<>();
        if (actores.isEmpty()){
            response.put("mensaje", "No se encontraron directores.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(actores);
    }

    @Override
    public ResponseEntity<?> consultarUno(Integer idActor) {
        Map<String, Object> response = new HashMap<>();
        Optional<Actor> directorOptional = actorRepositorio.findById(idActor);
        if (directorOptional.isEmpty()) {
            response.put("mensaje", "No se encontró un actor con el ID proporcionado.");
            return new  ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        Actor actor = directorOptional.get();
        return ResponseEntity.ok(actor);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarActor(Actor actor) {
        Map<String, String> response = new HashMap<>();
        Optional<Actor> actorOptional = actorRepositorio.findById(actor.getIdActor());

        if (actorOptional.isEmpty()) {
            // El actor no existe, devuelve un ResponseEntity con un mensaje de error
            response.put("mensaje", "El actor con el ID " + actor.getIdActor() + " no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Actualiza el actor existente con los datos proporcionados
        actor.setNombreActor(actor.getNombreActor());
        actorRepositorio.save(actor);
        // Devuelve un ResponseEntity con un mensaje de éxito
        response.put("mensaje", "Actor actualizado con éxito.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarActor(Actor actor) {
        Map<String, String> response = new HashMap<>();
        // Lógica para insertar un nuevo actor en el repositorio
        if (actor.getNombreActor() == null || actor.getNombreActor().isEmpty()){
            response.put("mensaje", "Error al insertar actor.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }


        // Devuelve un ResponseEntity con un mensaje de éxito
        actorRepositorio.save(actor);
        response.put("mensaje", "Actor insertado con éxito.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> eliminarActor(Integer idActor) {
        // Lógica para eliminar el actor por ID
        Map<String, String> response = new HashMap<>();
        if (actorRepositorio.existsById(idActor)){
            actorRepositorio.deleteById(idActor);
            response.put("mensaje", "Actor eliminado con éxito.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // Devuelve un ResponseEntity con un mensaje de éxito
        response.put("mensaje", "Error al eliminar actor.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> consultarbyNombre(String nombreActor) {
        Map<String, Object> response = new HashMap<>();

        if (nombreActor == null || nombreActor.isEmpty()) {
            // El nombre del actor no fue proporcionado, devuelve un ResponseEntity con un mensaje de error
            response.put("mensaje", "Ingrese el nombre a buscar.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Actor> actores = actorRepositorio.findByNombreActorIgnoreCase(nombreActor);

        if (actores.isEmpty()) {
            // No se encontraron actores con el nombre proporcionado, devuelve un ResponseEntity con un mensaje de error
            response.put("mensaje", "No se encontraron actores con el nombre " + nombreActor + ".");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Devuelve un ResponseEntity con la lista de actores encontrados
        return ResponseEntity.ok(actores);
    }
}
