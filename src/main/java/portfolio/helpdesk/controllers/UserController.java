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
        userService.findByUsernameOrEmail(userCreationDTO.username(), userCreationDTO.email());
        userService.validatePasswords(userCreationDTO.password(), userCreationDTO.rePassword());
        UserResponse user = userMapper.convertToUserDTO(userService.save(userCreationDTO));
        URI location = URI.create(String.format("/users/%s", user.id()));
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Integer id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        UserData user = userService.findById(id);
        if (userUpdateDTO.password() != null && userUpdateDTO.rePassword() != null) {
            userService.validatePasswords(userUpdateDTO.password(), userUpdateDTO.rePassword());
        }
        userMapper.updateFromDTO(userUpdateDTO, user);
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    /*@PutMapping("/{id}/complete-registration")
    public ResponseEntity<Void> completeRegistration(@PathVariable("id") Integer id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.validatePasswords(userUpdateDTO.password(), userUpdateDTO.rePassword());
        userService.completeRegistration(id, userUpdateDTO);
        return ResponseEntity.ok().build();
    }*/

    @PostMapping("/validate-user-data")
    public ResponseEntity<ValidateUserDataResponse> validateUserData(@Valid @RequestBody ValidateUserDataRequestDTO validateUserDataRequestDTO) {
        UserData user = userService.validateUserData(validateUserDataRequestDTO);
        return ResponseEntity.ok(userMapper.convertToValidateUserDataDTO(user));
    }

    @PostMapping("/{id}/validate-secret-answer")
    public ResponseEntity<Void> validateSecretAnswer(@PathVariable("id") Integer id, @Valid @RequestBody ValidateUserSecretAnswerDTO validateUserSecretAnswerDTO) {
        userService.validateSecretAnswer(id, validateUserSecretAnswerDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/restore-password")
    public ResponseEntity<Void> restorePassword(@PathVariable("id") Integer id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.validatePasswords(userUpdateDTO.password(), userUpdateDTO.rePassword());
        userService.restorePassword(id, userUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> changeStatus(@PathVariable("id") Integer id) {
        userService.changeStatusById(id);
        return ResponseEntity.ok().build();
    }

}
