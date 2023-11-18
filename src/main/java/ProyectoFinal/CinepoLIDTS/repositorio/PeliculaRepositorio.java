package ProyectoFinal.CinepoLIDTS.repositorio;

import ProyectoFinal.CinepoLIDTS.modelo.Pelicula;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PeliculaRepositorio extends JpaRepository<Pelicula,Integer> {

    List<Pelicula> findByyearDate(String yearDate);
    List<Pelicula> findByDirector_IdDirector(Integer idDirector);
    @Modifying
    @Transactional
    @Query("UPDATE Pelicula p SET p.nombre = :nombre WHERE p.idPelicula = :idPelicula")
    int actualizarNombrePelicula(Integer idPelicula, String nombre);



}
