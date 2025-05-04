package it.epicode.fs_gestione_eventi.eventi;

import it.epicode.gestione_eventi.auth.AppUser;
import it.epicode.gestione_eventi.auth.AppUserService;
import it.epicode.gestione_eventi.auth.Role;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AppUserService appUserService;

    public Evento createEvento (EventoRequest eventoRequest, AppUser appUser) {
        if (!appUser.getRoles().contains(Role.ROLE_ORGANIZER)) {
            throw new SecurityException("Solo gli organizzatori possono creare eventi");
        }
        Evento evento = new Evento();
        BeanUtils.copyProperties(eventoRequest, evento);
        evento.setOrganizzatore(appUser);


        return eventoRepository.save(evento);
    }


    public List<Evento> getAllEventi() {
        return eventoRepository.findAll();
    }

    public Evento getEventoById(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento non trovato con id: " + id));
    }

    public List<Evento> searchEventi(String titolo) {
        return eventoRepository.findAll()
                .stream()
                .filter(evento -> evento.getTitolo().toLowerCase().contains(titolo.toLowerCase()))
                .toList();
    }

    // eventi di un organizzatore
    public List<Evento> getEventiByOrganizzatore(AppUser organizzatore) {
        List <Evento> eventi = eventoRepository.findByOrganizzatore(organizzatore);
        if (!organizzatore.getRoles().contains(Role.ROLE_ORGANIZER)) {
            throw new SecurityException("Solo gli organizzatori possono vedere gli eventi di un organizzatore");
        } else {
            return eventi;
        }
    }

    public Evento updateEvento(Long id, EventoRequest eventoRequest, AppUser organizzatore) {
        Evento evento = getEventoById(id);
        if (evento == null) {
           throw new EntityNotFoundException("Evento non trovato con id: " + id);
        }
        if( !organizzatore.getRoles().contains(Role.ROLE_ORGANIZER) || !organizzatore.equals(evento.getOrganizzatore())) {
            throw new SecurityException("Solo gli organizzatori possono aggiornare eventi");
        }
        BeanUtils.copyProperties(eventoRequest, evento);
        return eventoRepository.save(evento);
    }

    public void deleteEvento(Long id, AppUser organizzatore) {
        Evento evento = getEventoById(id);
        if (evento == null) {
            throw new EntityNotFoundException("Evento non trovato con id: " + id);
        }
        if( !organizzatore.getRoles().contains(Role.ROLE_ORGANIZER) || !organizzatore.equals(evento.getOrganizzatore())) {
            throw new SecurityException("Solo gli organizzatori possono eliminare eventi");
        }
        eventoRepository.delete(evento);
    }

    // aumenta numero di posti prenotati
    public Evento incrementataPostiPrenotati(Long eventoId) {
        Evento evento = getEventoById(eventoId);
        if (evento == null) {
            throw new EntityNotFoundException("Evento non trovato con id: " + eventoId);
        }
        if (evento.postiRestanti() == false) {
            throw new IllegalArgumentException("Non ci sono posti disponibili per questo evento");
        }
        evento.setPostiPrenotati(evento.getPostiPrenotati() + 1);
        return eventoRepository.save(evento);
        }

        // diminuisci numero di posti prenotati
    public Evento decrementataPostiPrenotati(Long eventoId) {
        Evento evento = getEventoById(eventoId);
        if (evento == null) {
            throw new EntityNotFoundException("Evento non trovato con id: " + eventoId);
        }
        evento.setPostiPrenotati(evento.getPostiPrenotati() - 1);
        return eventoRepository.save(evento);
    }


}

