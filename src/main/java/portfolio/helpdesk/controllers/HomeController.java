package portfolio.helpdesk.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.LoginRequestDTO;
import portfolio.helpdesk.security.CustomUserDetails;

@RequiredArgsConstructor
@RestController
@RequestMapping("/home")
public class HomeController {
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<CustomUserDetails> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest request) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequestDTO.username(),
                loginRequestDTO.password()
        );
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticationResponse);
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
        CustomUserDetails userDetails = this.activeUser();
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/check-login")
    public ResponseEntity<Boolean> login() {
        boolean isAuthenticated = true;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            if (userDetails.isFirstLogin()) {
                isAuthenticated = false;
                this.logout();
            }
        } else {
            isAuthenticated = false;
        }
        return ResponseEntity.ok(isAuthenticated);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }


    @GetMapping("/active-user")
    public CustomUserDetails activeUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }
}
