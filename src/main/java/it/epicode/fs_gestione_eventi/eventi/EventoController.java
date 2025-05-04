package it.epicode.fs_gestione_eventi.eventi;

import it.epicode.fs_gestione_eventi.auth.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("")
    public List<Evento> getEventi() {
        return eventoService.getAllEventi();
    }

    @GetMapping("/{id}")
    public Evento getEventoById(Long id) {
        return eventoService.getEventoById(id);
    }

    @GetMapping("/{titolo}")
    public List<Evento> searchEventi(@RequestParam String titolo) {
        return eventoService.searchEventi(titolo);
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @GetMapping("/me")
    public List<Evento> getEventiByOrganizzatore(@AuthenticationPrincipal AppUser organizzatore) {
        return eventoService.getEventiByOrganizzatore(organizzatore);
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento createEvento(@RequestBody EventoRequest eventoRequest, @AuthenticationPrincipal AppUser organizzatore) {
        return eventoService.createEvento(eventoRequest, organizzatore);
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @PutMapping("/{id}")
    public Evento updateEvento(@PathVariable Long id, @RequestBody EventoRequest eventoRequest, @AuthenticationPrincipal AppUser organizzatore) {
        return eventoService.updateEvento(id, eventoRequest, organizzatore);
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvento(@PathVariable Long id, @AuthenticationPrincipal AppUser organizzatore) {
        eventoService.deleteEvento(id, organizzatore);
    }

}
