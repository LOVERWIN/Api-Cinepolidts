package ProyectoFinal.CinepoLIDTS.controlador;

import ProyectoFinal.CinepoLIDTS.modelo.Actor;
import ProyectoFinal.CinepoLIDTS.servicio.ActorServicio;
import ProyectoFinal.CinepoLIDTS.servicio.IActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/actores")
public class
ActorControlador {
    @Autowired
    private IActor actorServicio;

    @GetMapping
    public ResponseEntity<?> obtenerTodosActores() {
        return actorServicio.consultarTodosActores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerActorPorId(@PathVariable Integer id) {
        return actorServicio.consultarUno(id);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> obtenerActorPorNombre(@PathVariable String nombre) {
        return actorServicio.consultarbyNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> insertarActor(@RequestBody Actor actor) {
        return actorServicio.insertarActor(actor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarActor(@PathVariable Integer id, @RequestBody Actor actor) {
        actor.setIdActor(id); // Asegurarse de establecer el ID correctamente
        return actorServicio.actualizarActor(actor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarActor(@PathVariable Integer id) {
        return actorServicio.eliminarActor(id);
    }
}
