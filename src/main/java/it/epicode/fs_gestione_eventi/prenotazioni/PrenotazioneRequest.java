package it.epicode.fs_gestione_eventi.prenotazioni;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {
    @NotNull(message = "L'ID dell'evento è obbligatorio")
    private Long eventoId;
}
