package it.epicode.fs_gestione_eventi.eventi;

import it.epicode.fs_gestione_eventi.auth.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository <Evento, Long>{
    boolean existsByTitolo(String titolo);
    List <Evento> findByOrganizzatore(AppUser organizzatore);
}
