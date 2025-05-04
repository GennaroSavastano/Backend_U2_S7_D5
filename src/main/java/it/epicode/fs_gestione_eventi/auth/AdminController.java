package it.epicode.fs_gestione_eventi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private AppUserService appUserService;

    // endpoint per visualizzare tutti gli utenti
    @GetMapping("/users")
    public ResponseEntity <List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserService.findAllUsers();
         return ResponseEntity.ok(users);
    }

    // endpoint per impostare il ruolo su ORGANIZER
    @PutMapping("/set-organizer-role")
    public ResponseEntity <AppUser> setOrganizerRole(String username) {
        AppUser appUser = appUserService.setOrganizerRole(username);
        return ResponseEntity.ok(appUser);
    }
}
