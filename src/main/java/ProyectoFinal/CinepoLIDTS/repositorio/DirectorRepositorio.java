package ProyectoFinal.CinepoLIDTS.repositorio;

import ProyectoFinal.CinepoLIDTS.modelo.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectorRepositorio extends JpaRepository<Director,Integer> {
    boolean existsByNombreDirectorIgnoreCase(String nombreDirector);
    List<Director> findByNombreDirectorIgnoreCase(String nombreDirector);
}
