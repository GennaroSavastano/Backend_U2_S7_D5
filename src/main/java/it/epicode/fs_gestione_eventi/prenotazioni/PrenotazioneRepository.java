package it.epicode.fs_gestione_eventi.prenotazioni;

import it.epicode.gestione_eventi.auth.AppUser;
import it.epicode.gestione_eventi.eventi.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long>{
    List<Prenotazione> findByUser(AppUser user);

    List<Prenotazione> findByEvento(Evento evento);

    Optional<Prenotazione> findByEventoAndUser(Evento evento, AppUser user);
}


