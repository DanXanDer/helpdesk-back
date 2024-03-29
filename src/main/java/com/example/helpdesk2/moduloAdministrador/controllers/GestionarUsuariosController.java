package com.example.helpdesk2.moduloAdministrador.controllers;

import com.example.helpdesk2.DTO.CambiarEstadoUsuarioDTO;
import com.example.helpdesk2.DTO.DatosClienteDTO;
import com.example.helpdesk2.DTO.RegistrarClienteDTO;
import com.example.helpdesk2.DTO.RegistrarTrabajadorDTO;
import com.example.helpdesk2.models.Usuario;
import com.example.helpdesk2.moduloAdministrador.services.GestionarUsuariosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/modulo-administrador")
public class GestionarUsuariosController {

    private final GestionarUsuariosService gestionarUsuariosService;

    public GestionarUsuariosController(GestionarUsuariosService gestionarUsuariosService) {
        this.gestionarUsuariosService = gestionarUsuariosService;
    }

    @GetMapping("/trabajadores")
    public ResponseEntity<Map<String, Object>> obtenerUsuarios(){
        List<Usuario> usuarios = gestionarUsuariosService.obtenerTrabajadores();
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("trabajadores", usuarios);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respuesta);
    }

    @GetMapping("/clientes")
    public ResponseEntity<Map<String, Object>> obtenerClientes(){
        List<DatosClienteDTO> clientes = gestionarUsuariosService.obtenerTodosLosClientes();
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("clientes", clientes);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respuesta);
    }




    @PostMapping("/usuarios/cambiar-estado")
    public ResponseEntity<Map<String, Object>> cambiarEstadoUsuario(@RequestBody CambiarEstadoUsuarioDTO cambiarEstadoUsuarioDTO){
        gestionarUsuariosService.cambiarEstadoUsuario(cambiarEstadoUsuarioDTO);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("ok", true);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respuesta);
    }

    @Transactional
    @PostMapping("/usuarios/crear-trabajador")
    public ResponseEntity<Map<String, Object>> crearTrabajador(@Valid @RequestBody RegistrarTrabajadorDTO registrarTrabajadorDTO){
        int idUsuario = gestionarUsuariosService.crearUsuario(registrarTrabajadorDTO);
        registrarTrabajadorDTO.setIdUsuario(idUsuario);
        gestionarUsuariosService.crearTrabajador(registrarTrabajadorDTO);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("ok", true);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respuesta);
    }

    @Transactional
    @PostMapping("/usuarios/crear-cliente")
    public ResponseEntity<Map<String, Object>> crearCliente(@Valid @RequestBody RegistrarClienteDTO registrarClienteDTO){
        int idUsuario = gestionarUsuariosService.crearUsuario(registrarClienteDTO);
        registrarClienteDTO.setIdUsuario(idUsuario);
        gestionarUsuariosService.crearCliente(registrarClienteDTO);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("ok", true);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respuesta);
    }

    @GetMapping("/empresas")
    public ResponseEntity<Map<String, Object>> obtenerEmpresas(){
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("empresas", gestionarUsuariosService.obtenerEmpresas());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respuesta);
    }

    @GetMapping("/sedes")
    public ResponseEntity<Map<String, Object>> obtenerSedes(@RequestParam int idEmpresa){
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("sedes", gestionarUsuariosService.obtenerSedesPorEmpresa(idEmpresa));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respuesta);
    }

    @GetMapping("/areas")
    public ResponseEntity<Map<String, Object>> obtenerAreas(@RequestParam int idSede){
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("areas", gestionarUsuariosService.obtenerAreasPorSede(idSede));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respuesta);
    }


}
