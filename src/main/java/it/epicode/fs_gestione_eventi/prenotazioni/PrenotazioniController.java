package it.epicode.fs_gestione_eventi.prenotazioni;

import it.epicode.gestione_eventi.auth.AppUser;
import it.epicode.gestione_eventi.eventi.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private EventoService eventoService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public List<Prenotazione> getMyPrenotazioni(@AuthenticationPrincipal AppUser user) {
        return prenotazioneService.getPrenotazioniByUser(user);
    }

    // crea prenotazione
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/prenota/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione createPrenotazione(@RequestBody PrenotazioneRequest prenotazioneRequest, @AuthenticationPrincipal AppUser user) {
        return prenotazioneService.createPrenotazione(prenotazioneRequest, user);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/elimina/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@AuthenticationPrincipal AppUser user, @RequestBody Long id) {
        prenotazioneService.deletePrenotazione(id, user);
    }

}
