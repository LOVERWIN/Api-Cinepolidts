package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.Hora;
import ProyectoFinal.CinepoLIDTS.modelo.Pelicula;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface IHora {
    ResponseEntity<?> consultarTodasHoras();
    ResponseEntity<?> consultarUna(Integer idHora);
    ResponseEntity<Map<String, String>> actualizarHora(Hora hora);
    ResponseEntity<Map<String, String>> insertarHora(Hora hora);
    ResponseEntity<?> eliminarHora(Integer idHora);

}

