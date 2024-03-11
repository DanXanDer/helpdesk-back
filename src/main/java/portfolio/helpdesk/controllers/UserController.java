package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.request.ValidateUserDataRequestDTO;
import portfolio.helpdesk.DTO.request.ValidateUserSecretAnswerDTO;
import portfolio.helpdesk.DTO.response.UserResponse;
import portfolio.helpdesk.DTO.response.ValidateUserDataResponse;
import portfolio.helpdesk.mappers.UserMapper;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.services.IUserService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        userService.validatePasswords(userCreationDTO.password(), userCreationDTO.rePassword());
        UserResponse user = userMapper.convertToUserDTO(userService.save(userCreationDTO));
        URI location = URI.create(String.format("/users/%s", user.idUser()));
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idUser}/complete-registration")
    public ResponseEntity<Void> completeRegistration(@PathVariable("idUser") Integer idUser, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.validatePasswords(userUpdateDTO.password(), userUpdateDTO.rePassword());
        userService.completeRegistration(idUser, userUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate-user-data")
    public ResponseEntity<ValidateUserDataResponse> validateUserData(@Valid @RequestBody ValidateUserDataRequestDTO validateUserDataRequestDTO) {
        UserData user = userService.validateUserData(validateUserDataRequestDTO);
        return ResponseEntity.ok(userMapper.convertToValidateUserDataDTO(user));
    }

    @PostMapping("/{idUser}/validate-secret-answer")
    public ResponseEntity<Void> validateSecretAnswer(@PathVariable("idUser") Integer idUser, @Valid @RequestBody ValidateUserSecretAnswerDTO validateUserSecretAnswerDTO) {
        userService.validateSecretAnswer(idUser, validateUserSecretAnswerDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idUser}/restore-password")
    public ResponseEntity<Void> restorePassword(@PathVariable("idUser") Integer idUser, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.validatePasswords(userUpdateDTO.password(), userUpdateDTO.rePassword());
        userService.restorePassword(idUser, userUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{idUser}/status")
    public ResponseEntity<Void> changeStatus(@PathVariable("idUser") Integer idUser) {
        userService.changeStatusById(idUser);
        return ResponseEntity.ok().build();
    }

}
