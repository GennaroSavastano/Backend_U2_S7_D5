package it.epicode.fs_gestione_eventi.prenotazioni;

import it.epicode.gestione_eventi.auth.AppUser;
import it.epicode.gestione_eventi.auth.AppUserService;
import it.epicode.gestione_eventi.eventi.Evento;
import it.epicode.gestione_eventi.eventi.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AppUserService appUserService;

    public Prenotazione createPrenotazione(PrenotazioneRequest prenotazioneRequest, AppUser username) {
       Evento evento = eventoService.getEventoById(prenotazioneRequest.getEventoId());
       if (prenotazioneRepository.findByEventoAndUser(evento, username).isPresent()) {
           throw new IllegalArgumentException("Hai già prenotato questo evento");
       }
       if (evento.postiRestanti() == false) {
           throw new IllegalArgumentException("Non ci sono posti disponibili per questo evento");
       }
       Prenotazione prenotazione = new Prenotazione();
       prenotazione.setEvento(evento);
       prenotazione.setUser(username);
       eventoService.incrementataPostiPrenotati(evento.getId());
       return prenotazioneRepository.save(prenotazione);
    }


    public void deletePrenotazione(Long id, AppUser username) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata con id: " + id));
        if (!prenotazione.getUser().equals(username)) {
            throw new IllegalArgumentException("Non puoi cancellare una prenotazione che non hai creato");
        }
        prenotazioneRepository.delete(prenotazione);
        eventoService.decrementataPostiPrenotati(prenotazione.getEvento().getId());
    }

    public Prenotazione getPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata con id: " + id));
    }

    // tutte le prenotazioni di un utente
    public List<Prenotazione> getPrenotazioniByUser(AppUser username) {
        return prenotazioneRepository.findByUser(username);
    }

}
