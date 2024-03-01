package portfolio.helpdesk.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.response.RoleResponseDTO;
import portfolio.helpdesk.mappers.RoleMapper;
import portfolio.helpdesk.services.IRoleService;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final IRoleService roleService;
    private final RoleMapper roleMapper = RoleMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<Set<RoleResponseDTO>> findRolesNoAdmin() {
        Set<RoleResponseDTO> roles = roleService.findRolesNoAdmin().stream().map(roleMapper::convertToDTO).collect(Collectors.toSet());
        return ResponseEntity.ok(roles);
    }

}
