package it.epicode.fs_gestione_eventi.eventi;

import it.epicode.fs_gestione_eventi.auth.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private String luogo;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private int postiDisponibili;

    @ManyToOne
    private AppUser organizzatore;

    private int postiPrenotati = 0;

    public boolean postiRestanti() {
        return postiDisponibili > postiPrenotati;
    }

}
