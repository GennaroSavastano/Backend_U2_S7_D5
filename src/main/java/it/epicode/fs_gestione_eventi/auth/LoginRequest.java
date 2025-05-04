package it.epicode.fs_gestione_eventi.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
