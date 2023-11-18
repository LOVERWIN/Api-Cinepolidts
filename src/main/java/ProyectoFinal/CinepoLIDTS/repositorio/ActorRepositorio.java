package ProyectoFinal.CinepoLIDTS.repositorio;

import ProyectoFinal.CinepoLIDTS.modelo.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepositorio extends JpaRepository<Actor,Integer> {


    List<Actor> findByNombreActorIgnoreCase(String nombreActor);
}
