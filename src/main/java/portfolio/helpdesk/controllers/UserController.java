package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.request.VerifyUserExistenceDTO;
import portfolio.helpdesk.mappers.UserMapper;
import portfolio.helpdesk.services.IUserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @GetMapping("/verify-existence")
    public ResponseEntity<Void> verifyUserExistence(@Valid @RequestBody VerifyUserExistenceDTO verifyUserExistenceDTO) {
        userService.findUserByUsernameOrEmail(verifyUserExistenceDTO.username(), verifyUserExistenceDTO.email());
        return ResponseEntity.ok().build();
    }
}
