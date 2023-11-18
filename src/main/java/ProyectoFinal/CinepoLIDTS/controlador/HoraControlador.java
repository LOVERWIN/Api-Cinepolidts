package ProyectoFinal.CinepoLIDTS.controlador;

import ProyectoFinal.CinepoLIDTS.modelo.Hora;
import ProyectoFinal.CinepoLIDTS.servicio.IHora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/horas")

public class HoraControlador {

    @Autowired
    private IHora horaServicio;

    @GetMapping
    public ResponseEntity<?> consultarTodasLasHoras() {
        return horaServicio.consultarTodasHoras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultarUnaHora(@PathVariable Integer id) {
        return horaServicio.consultarUna(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> insertarHora(@RequestBody Hora hora) {
        return horaServicio.insertarHora(hora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarHora(@PathVariable Integer id, @RequestBody Hora hora) {
        hora.setIdHora(id);
        return horaServicio.actualizarHora(hora);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHora(@PathVariable Integer id) {
        return horaServicio.eliminarHora(id);
    }
}
