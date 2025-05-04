package it.epicode.fs_gestione_eventi.prenotazioni;

import it.epicode.fs_gestione_eventi.auth.AppUser;
import it.epicode.fs_gestione_eventi.eventi.Evento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prenotazione {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    private LocalDate dataPrenotazione;
}
