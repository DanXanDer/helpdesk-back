package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.CompleteRegistrationDTO;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.mappers.UserMapper;
import portfolio.helpdesk.models.SecretQuestion;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.services.IUserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder enconder;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserCreationDTO userDTO) {
        userService.findByUsernameOrEmail(userDTO.username(), userDTO.email());
        UserData user = userMapper.convertToEntity(userDTO);
        user.setPassword(enconder.encode(userDTO.password()));
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/complete-registration")
    public ResponseEntity<Void> completeRegistration(@Valid @RequestBody CompleteRegistrationDTO completeRegistrationDTO) {
        userService.validatePasswords(completeRegistrationDTO.password(), completeRegistrationDTO.rePassword());
        SecretQuestion secretQuestion = new SecretQuestion();
        secretQuestion.setIdSecretQuestion(completeRegistrationDTO.idSecretQuestion());
        UserData user = userService.findById(completeRegistrationDTO.idUser());
        user.setSecretQuestion(secretQuestion);
        user.setPassword(enconder.encode(completeRegistrationDTO.password()));
        user.setSecretAnswer(enconder.encode(completeRegistrationDTO.secretAnswer()));
        userService.save(user);
        return ResponseEntity.ok().build();
    }
}
