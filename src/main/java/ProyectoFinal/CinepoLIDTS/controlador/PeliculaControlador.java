package ProyectoFinal.CinepoLIDTS.controlador;

import ProyectoFinal.CinepoLIDTS.modelo.Pelicula;
import ProyectoFinal.CinepoLIDTS.servicio.IPelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/peliculas")
public class PeliculaControlador {


    @Autowired
    private IPelicula peliculaServicio;

    @GetMapping
    public List<Pelicula> consultarTodasPeliculas() {
        return peliculaServicio.consultarTodasPeliculas();
    }

    @GetMapping("/{idPelicula}")
    public ResponseEntity<Pelicula> consultarUnaPelicula(@PathVariable Integer idPelicula) {
        return peliculaServicio.consultarUna(idPelicula);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> insertarPelicula(@RequestBody Pelicula pelicula) {
        return peliculaServicio.insertarPelicula(pelicula);
    }

    @PutMapping("/{idPelicula}")
    public ResponseEntity<Map<String, String>> actualizarPelicula(@PathVariable Integer idPelicula, @RequestBody Pelicula pelicula) {
        pelicula.setIdPelicula(idPelicula); // Asegurarse de establecer el ID correctamente
        return peliculaServicio.actualizarPelicula(pelicula);
    }

    @DeleteMapping("/{idPelicula}")
    public ResponseEntity<?> eliminarPelicula(@PathVariable Integer idPelicula) {
        return peliculaServicio.eliminarPelicula(idPelicula);
    }
    @GetMapping("/por-anio/{anio}")
    public ResponseEntity<List<Pelicula>> getPeliculasPorAnio(@PathVariable Integer anio) {
         List<Pelicula> peliculas = peliculaServicio.consultarPeliculasPorAnio(anio.toString());
        if (peliculas.isEmpty()) {
            // Puedes devolver un ResponseEntity con código 404 si no se encuentran películas para el año especificado
            String mensaje = "No se encontraron películas para el año especificado: " + anio;
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(peliculas);
    }


    @PutMapping("/nombre/{idPelicula}")
    public ResponseEntity<Map<String, String>> actualizarNombrePelicula(
            @PathVariable Integer idPelicula,
            @RequestBody Map<String, String> nombreMap) {
        String nombre = nombreMap.get("nombre");
        ResponseEntity<Map<String, String>> responseEntity = peliculaServicio.ActualizarNombrePeliculas(idPelicula, nombre);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }
    @GetMapping("/director/{idDirector}")
    public ResponseEntity<?> listarPeliculasDirector(@PathVariable Integer idDirector) {
        ResponseEntity<List<Pelicula>> responseEntity = peliculaServicio.listarPeliculasDirector(idDirector);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

}
