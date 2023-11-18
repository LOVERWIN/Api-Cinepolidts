package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.Actor;
import ProyectoFinal.CinepoLIDTS.modelo.Director;
import ProyectoFinal.CinepoLIDTS.modelo.Hora;
import ProyectoFinal.CinepoLIDTS.modelo.Pelicula;
import ProyectoFinal.CinepoLIDTS.repositorio.HoraRepositorio;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HoraServicio implements IHora{

    @Autowired
    private HoraRepositorio horaRepositorio;

    @Override
    public ResponseEntity<?> consultarTodasHoras() {
        List<Hora> horas =  horaRepositorio.findAll();

        Map<String, Object> response = new HashMap<>();
        if (horas.isEmpty()){
            response.put("mensaje", "No se econtro horas guardadas.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(horas);
    }

    @Override
    public ResponseEntity<?> consultarUna(Integer idHora) {
        Optional<Hora> horaOptional  = horaRepositorio.findById(idHora);
        Map<String, Object> response = new HashMap<>();
        if (horaOptional.isEmpty() || horaOptional == null) {
            // No se encontró la hora con el ID proporcionado
            response.put("mensaje", "No se encontró una hora con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Hora hora = horaOptional.get();
        // Devuelve la hora encontrada
        return ResponseEntity.ok(hora);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarHora(Hora hora) {
        // Verifica si la hora con el ID proporcionado existe
        if (!horaRepositorio.existsById(hora.getIdHora())) {
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "No se encontró una hora con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Realiza la actualización
        horaRepositorio.save(hora);

        // Devuelve un ResponseEntity vacío con código 200 OK
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarHora(Hora hora) {
        // Realiza la inserción

        Map<String, String> response = new HashMap<>();
        if (hora.getPelicula().getIdPelicula() == null || hora.getHora() == null || hora.getHora().equals("")) {
            response.put("mensaje", "Error al insertar la hora");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        horaRepositorio.save(hora);
        response.put("mensaje", "Se agrego correctamente.");
        // Devuelve un ResponseEntity vacío con código 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<?> eliminarHora(Integer idHora) {
        // Verifica si la hora con el ID proporcionado existe
        Map<String, String> response = new HashMap<>();
        if (!horaRepositorio.existsById(idHora)) {

            response.put("mensaje", "No se encontró una hora con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Realiza la eliminación
        horaRepositorio.deleteById(idHora);
        response.put("mensaje", "Se elimino correctamente");
        // Devuelve un ResponseEntity vacío con código 200 OK
        return ResponseEntity.ok().body(response);
    }

//    @Override
//    public ResponseEntity<Hora> consultarPorPeliculaYPorHora(Pelicula pelicula, LocalTime hora) {
//        Hora horaEncontrada = horaRepositorio.findByPeliculaAndHora(pelicula, hora);
//
//        if (horaEncontrada == null) {
//            // No se encontró la hora para la película y la hora proporcionadas
//            return ResponseEntity.notFound().build();
//        }
//
//        // Devuelve la hora encontrada
//        return ResponseEntity.ok(horaEncontrada);
//
//    }
}
