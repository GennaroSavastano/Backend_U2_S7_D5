package it.epicode.fs_gestione_eventi.eventi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoResponse {
    private Long id;
    private String titolo;
    private String descrizione;
    private String luogo;
    private LocalDate data;
    private int postiDisponibili;
    private int postiPrenotati;
    private String organizzatore;
}
