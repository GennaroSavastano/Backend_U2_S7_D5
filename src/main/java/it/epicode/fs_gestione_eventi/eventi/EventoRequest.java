package it.epicode.fs_gestione_eventi.eventi;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoRequest {

    @NotBlank(message = "Il titolo è obbligatorio")
    private String titolo;
    @NotBlank(message = "La descrizione é obbligatoria")
    private String descrizione;
    @NotBlank(message = "Il luogo é obbligatorio")
    private String luogo;
    @NotNull(message = "La data é obbligatoria")
    @Future(message = "La data deve essere futura")
    private LocalDate data;
    @Min(value = 1, message = "I posti disponibili devono essere maggiori di 0")
    private int postiDisponibili;
}
