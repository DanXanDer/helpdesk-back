package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.UserRequestDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.request.ValidateUserDataRequestDTO;
import portfolio.helpdesk.DTO.request.ValidateUserSecretAnswerDTO;
import portfolio.helpdesk.DTO.response.ValidateUserDataResponse;
import portfolio.helpdesk.mappers.UserMapper;
import portfolio.helpdesk.models.SecretQuestion;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.services.ISecretQuestionService;
import portfolio.helpdesk.services.IUserService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final ISecretQuestionService secretQuestionService;
    private final UserMapper userMapper;


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserData user = userMapper.convertToEntity(userRequestDTO);
        userService.save(user);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

    @PatchMapping(value = "/{id}/update")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        UserData user = userService.findById(id);
        if (userUpdateDTO.password() != null && userUpdateDTO.rePassword() != null) {
            userService.validatePasswords(userUpdateDTO.password(), userUpdateDTO.rePassword());
        }
        userMapper.updateFromDTO(userUpdateDTO, user);
        if (userUpdateDTO.secretQuestion() != null) {
            SecretQuestion secretQuestion = secretQuestionService.getReferenceById(userUpdateDTO.secretQuestion());
            user.setSecretQuestion(secretQuestion);
        }
        userService.save(user);
        return ResponseEntity.ok().build();
    }

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

}
