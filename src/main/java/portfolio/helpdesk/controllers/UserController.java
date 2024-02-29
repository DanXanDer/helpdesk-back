package portfolio.helpdesk.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.mappers.UserMapper;
import portfolio.helpdesk.services.IUserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

  /*  @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        userService.findUserByUsernameOrEmail(userRequestDTO.username(), userRequestDTO.email());
        User user = userService.save(userMapper.convertToEntity(userRequestDTO));
        URI location = URI.create(String.format("/user/%d", user.getIdUser()));
        return ResponseEntity.created(location).build();
    }*/
}
