package ProyectoFinal.CinepoLIDTS.repositorio;

import ProyectoFinal.CinepoLIDTS.modelo.Pelicula;
import ProyectoFinal.CinepoLIDTS.modelo.PeliculaActor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeliculaActorRepositorio extends JpaRepository<PeliculaActor,Integer> {
    List<PeliculaActor> findByPelicula_IdPelicula(Integer idPelicula);
}
