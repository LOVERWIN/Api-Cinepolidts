package ProyectoFinal.CinepoLIDTS.modelo;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pelicula_actor")
public class PeliculaActor {
    //como tenia una llave compuesta al generar el valor automatico se genera de acuerdo al orden alfabetico
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula_actor")
    private Integer idPeliculaActor;

    @ManyToOne
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "idActor")
    private Actor actor;
}


