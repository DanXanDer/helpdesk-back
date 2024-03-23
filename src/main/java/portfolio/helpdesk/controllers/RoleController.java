package portfolio.helpdesk.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.RoleDTO;
import portfolio.helpdesk.mappers.RoleMapper;
import portfolio.helpdesk.services.IRoleService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final IRoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findRolesNoAdmin() {
        List<RoleDTO> roles = roleService.findRolesNoAdmin().stream().map(roleMapper::convertToDTO).toList();
        return ResponseEntity.ok(roles);
    }

}
