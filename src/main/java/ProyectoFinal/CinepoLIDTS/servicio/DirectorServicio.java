package ProyectoFinal.CinepoLIDTS.servicio;

import ProyectoFinal.CinepoLIDTS.modelo.Director;
import ProyectoFinal.CinepoLIDTS.repositorio.DirectorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DirectorServicio implements IDirector{
    @Autowired
    private DirectorRepositorio directorRepositorio;

    @Override
    public ResponseEntity<?> consultarTodosDirectores() {
        List<Director> directores = directorRepositorio.findAll();
        Map<String, Object> response = new HashMap<>();
        if (directores.isEmpty()){
            response.put("mensaje", "No se encontraron directores.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(directores);
    }

    @Override
    public ResponseEntity<?> consultarUno(Integer idDirector) {
        Map<String, Object> response = new HashMap<>();
        Optional<Director> directorOptional = directorRepositorio.findById(idDirector);
        if (directorOptional.isEmpty()) {
            response.put("mensaje", "No se encontró un director con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Director director = directorOptional.get();
        return ResponseEntity.ok(director);
    }

    @Override
    public ResponseEntity<Map<String, String>> actualizarDirector(Director director) {
        // Verifica si el director con el ID proporcionado existe
        Map<String, String> response = new HashMap<>();
        if (!directorRepositorio.existsById(director.getIdDirector())) {
            response.put("mensaje", "No se encontró un director con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Realiza la actualización
        directorRepositorio.save(director);
        response.put("mensaje", "Se actualizo Correctamente.");

        // Devuelve un ResponseEntity con un mensaje y con código 200 OK
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Map<String, String>> insertarDirector(Director director) {
        // Verifica si ya hay un director con el mismo nombre
        Map<String, String> response = new HashMap<>();
        if (director.getNombreDirector().isEmpty() || director == null ) {
            response.put("mensaje", "Error al insertar director.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Realiza la inserción
        directorRepositorio.save(director);
        response.put("mensaje", "Se agrego correctamente.");

        // Devuelve un ResponseEntity con un mensaje de exito con código 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<?> eliminarDirector(Integer idDirector) {
        // Verifica si el director con el ID proporcionado existe
        Map<String, String> response = new HashMap<>();
        if (!directorRepositorio.existsById(idDirector)) {
            response.put("mensaje", "No se encontró un director con el ID proporcionado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Realiza la eliminación
        directorRepositorio.deleteById(idDirector);
        response.put("mensaje", "Eliminado Correctamente.");

        // Devuelve un ResponseEntity vacío con código 200 OK
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<?> consultarbyNombre(String nombreDirector) {
        Map<String, Object> response = new HashMap<>();

        if (nombreDirector == null || nombreDirector.isEmpty()) {
            // El nombre del director no fue proporcionado, devuelve un ResponseEntity con un mensaje de error
            response.put("mensaje", "Ingrese el nombre a buscar.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Director> directores = directorRepositorio.findByNombreDirectorIgnoreCase(nombreDirector);

        if (directores.isEmpty()) {
            // No se encontraron directores con el nombre proporcionado, devuelve un ResponseEntity con un mensaje de error
            response.put("mensaje", "No se encontraron directores con el nombre " + nombreDirector + ".");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Devuelve un ResponseEntity con la lista de directores encontrados
        return ResponseEntity.ok(directores);
    }
}
