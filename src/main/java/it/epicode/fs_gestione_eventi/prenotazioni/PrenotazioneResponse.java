package it.epicode.fs_gestione_eventi.prenotazioni;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneResponse {
    private Long id;
    private Long eventoId;
    private String username;
    private String titoloEvento;
    private LocalDate dataPrenotazione;
}
