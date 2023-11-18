package ProyectoFinal.CinepoLIDTS.controlador;

import ProyectoFinal.CinepoLIDTS.modelo.Director;
import ProyectoFinal.CinepoLIDTS.servicio.IDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@RestController
@RequestMapping("api/directores")
public class DirectorControlador {

    @Autowired
    private IDirector directorServicio;

    @GetMapping
    public ResponseEntity<?> obtenerTodosDirectores() {
        return directorServicio.consultarTodosDirectores();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> obtenerDirectorPorNombre(@PathVariable String nombre) {
        return directorServicio.consultarbyNombre(nombre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDirectorPorId(@PathVariable("id") Integer idDirector) {
        return directorServicio.consultarUno(idDirector);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> agregarDirector(@RequestBody Director director) {
        return directorServicio.insertarDirector(director);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarDirector(@PathVariable("id") Integer idDirector, @RequestBody Director director) {
        director.setIdDirector(idDirector);
        return directorServicio.actualizarDirector(director);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDirector(@PathVariable("id") int idDirector) {
        return directorServicio.eliminarDirector(idDirector);
    }
}
