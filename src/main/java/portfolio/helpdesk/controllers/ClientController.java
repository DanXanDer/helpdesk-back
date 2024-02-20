package portfolio.helpdesk.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.services.IUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final IUserService userService;

}
