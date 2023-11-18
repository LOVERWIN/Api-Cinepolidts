package ProyectoFinal.CinepoLIDTS.controlador;

import ProyectoFinal.CinepoLIDTS.modelo.PeliculaActor;
import ProyectoFinal.CinepoLIDTS.servicio.IPeliculaActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pelicula-actor")
public class PeliculaActorControlador {


    @Autowired
    private IPeliculaActor peliculaActorServicio;

    @GetMapping()
    public List<PeliculaActor> consultarTodasPeliculasActores() {
        return peliculaActorServicio.consultarTodasPeliculasActores();
    }

    @GetMapping("/{idPelicula}")
    public ResponseEntity<?> consultaridPelicula(@PathVariable Integer idPelicula) {

        return peliculaActorServicio.consultaridPelicula(idPelicula);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Map<String, String>> insertarPeliculaActor(@RequestBody PeliculaActor peliculaActor) {
        return peliculaActorServicio.insertarPeliculaActor(peliculaActor);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Map<String, String>> actualizarPeliculaActor(@RequestBody PeliculaActor peliculaActor) {
        return peliculaActorServicio.actualizarPeliculaActor(peliculaActor);
    }

    @DeleteMapping("/eliminar/{idPeliculaActor}")
    public ResponseEntity<?> eliminarPeliculaActor(@PathVariable Integer idPeliculaActor) {
        return peliculaActorServicio.eliminarPeliculaActor(idPeliculaActor);
    }

}
