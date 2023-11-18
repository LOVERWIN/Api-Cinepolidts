package ProyectoFinal.CinepoLIDTS.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "horarios")
public class Hora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHora;

    @ManyToOne
    @JoinColumn(name = "idPelicula",referencedColumnName = "idPelicula")
    @ToString.Exclude
    private Pelicula pelicula;

    private LocalTime hora;
}
