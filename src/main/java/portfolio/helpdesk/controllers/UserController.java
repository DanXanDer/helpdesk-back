package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.request.UserRequestDTO;
import portfolio.helpdesk.mappers.UserMapper;
import portfolio.helpdesk.models.User;
import portfolio.helpdesk.services.IUserService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        userService.findUserByUsernameOrEmail(userRequestDTO.username(), userRequestDTO.email());
        User user = userService.save(userMapper.convertToEntity(userRequestDTO));
        URI location = URI.create(String.format("/user/%d", user.getIdUser()));
        return ResponseEntity.created(location).build();
    }
}
