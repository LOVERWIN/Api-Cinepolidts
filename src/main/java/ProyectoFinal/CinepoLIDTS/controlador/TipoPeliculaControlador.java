package ProyectoFinal.CinepoLIDTS.controlador;

import ProyectoFinal.CinepoLIDTS.modelo.TipoPelicula;
import ProyectoFinal.CinepoLIDTS.servicio.ITipoPelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tipo-peliculas")
public class TipoPeliculaControlador {
    @Autowired
    ITipoPelicula itipoPelicula;

    @GetMapping
    public List<TipoPelicula> consultarTodosTiposPelicula() {
        TipoPeliculaControlador tipoPeliculaServicio;
        return itipoPelicula.consultarTodosTiposPelicula();
    }

    @GetMapping("/{idTipoPelicula}")
    public ResponseEntity<?> consultarUnTipoPelicula(@PathVariable Integer idTipoPelicula) {
        return itipoPelicula.consultarUno(idTipoPelicula);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> insertarTipoPelicula(@RequestBody TipoPelicula tipoPelicula) {
        return itipoPelicula.insertarTipoPelicula(tipoPelicula);
    }

    @PutMapping("/{idTipoPelicula}")
    public ResponseEntity<Map<String, String>> actualizarTipoPelicula(@PathVariable Integer idTipoPelicula, @RequestBody TipoPelicula tipoPelicula) {
        tipoPelicula.setIdTipo(idTipoPelicula); // Asegurarse de establecer el ID correctamente
        return itipoPelicula.actualizarTipoPelicula(tipoPelicula);
    }

    @DeleteMapping("/{idTipoPelicula}")
    public ResponseEntity<?> eliminarTipoPelicula(@PathVariable Integer idTipoPelicula) {
        return itipoPelicula.eliminarTipoPelicula(idTipoPelicula);
    }
}
