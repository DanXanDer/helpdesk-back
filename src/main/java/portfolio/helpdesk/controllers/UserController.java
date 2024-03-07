package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.request.ValidateUserDataRequestDTO;
import portfolio.helpdesk.DTO.request.ValidateUserSecretAnswer;
import portfolio.helpdesk.DTO.response.ValidateUserDataResponseDTO;
import portfolio.helpdesk.mappers.RestorePasswordMapper;
import portfolio.helpdesk.mappers.UserMapper;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.services.IUserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final RestorePasswordMapper restorePasswordMapper = RestorePasswordMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        userService.validatePasswords(userCreationDTO.password(), userCreationDTO.rePassword());

        userService.save(userCreationDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/complete-registration")
    public ResponseEntity<Void> completeRegistration(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.validatePasswords(userUpdateDTO.password(), userUpdateDTO.rePassword());
        userService.completeRegistration(userUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate-user-data")
    public ResponseEntity<ValidateUserDataResponseDTO> validateUserData(@Valid @RequestBody ValidateUserDataRequestDTO validateUserDataRequestDTO) {
        UserData user = userService.validateUserData(validateUserDataRequestDTO);
        return ResponseEntity.ok(restorePasswordMapper.convertToDTO(user));
    }

    @PostMapping("/validate-secret-answer")
    public ResponseEntity<Void> validateSecretAnswer(@Valid @RequestBody ValidateUserSecretAnswer validateUserSecretAnswer) {
        userService.validateSecretAnswer(validateUserSecretAnswer);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/restore-password")
    public ResponseEntity<Void> restorePassword(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.validatePasswords(userUpdateDTO.password(), userUpdateDTO.rePassword());
        userService.restorePassword(userUpdateDTO);
        return ResponseEntity.ok().build();
    }

}
