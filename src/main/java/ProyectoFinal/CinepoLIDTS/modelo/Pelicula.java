package ProyectoFinal.CinepoLIDTS.modelo;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPelicula;

    private String imagePelicula;
    private String urlTrailer;
    private String nombre;
    private Integer minutos;
    private String clasificacion;
    private String yearDate;
    private String sinopsis;

    @ManyToOne
    @JoinColumn(name = "id_director_fk")
    private Director director;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoPelicula tipoPelicula;
}
