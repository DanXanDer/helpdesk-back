package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.UserRequestDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.request.UserValidationDTO;
import portfolio.helpdesk.DTO.response.UserSecretQuestionResponseDTO;
import portfolio.helpdesk.mappers.UserMapper;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.services.IUserService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final UserMapper userMapper;
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserData user = userMapper.convertToEntity(userRequestDTO);
        userService.save(user);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        UserData user = userService.findById(id);
        if (userUpdateDTO.getPassword() != null && userUpdateDTO.getRePassword() != null) {
            userService.validatePasswords(userUpdateDTO.getPassword(), userUpdateDTO.getRePassword());
        }
        userMapper.updateFromDTO(userUpdateDTO, user);
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate-user-data")
    public ResponseEntity<UserSecretQuestionResponseDTO> validateUserData(@Valid @RequestBody UserValidationDTO userValidationDTO) {
        UserData user = userService.validateUserData(userValidationDTO.getUsername(), userValidationDTO.getName(), userValidationDTO.getLastname());
        return ResponseEntity.ok(userMapper.convertToUserSecretQuestionResponseDTO(user));
    }

    @PostMapping("/{id}/validate-secret-answer")
    public ResponseEntity<Void> validateSecretAnswer(@PathVariable("id") Integer id, @RequestBody UserValidationDTO userValidationDTO) {
        userService.validateSecretAnswer(id, userValidationDTO.getSecretAnswer());
        return ResponseEntity.ok().build();
    }
}
