package ProyectoFinal.CinepoLIDTS.repositorio;

import ProyectoFinal.CinepoLIDTS.modelo.Hora;
import ProyectoFinal.CinepoLIDTS.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;

public interface HoraRepositorio extends JpaRepository<Hora,Integer> {
//    @Query("SELECT h FROM Hora h WHERE h.pelicula = :pelicula AND h.hora = :hora")
//    Hora findByPeliculaAndHora(@Param("pelicula") Pelicula pelicula, @Param("hora") LocalTime hora);
}

