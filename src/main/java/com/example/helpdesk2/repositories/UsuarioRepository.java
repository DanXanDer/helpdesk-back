package com.example.helpdesk2.repositories;

import com.example.helpdesk2.DTO.*;
import com.example.helpdesk2.models.Usuario;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {


    @Query("SELECT u.id_usuario, u.nombre_usuario, u.nombres, u.apellidos, u.primer_login FROM usuario u WHERE " +
            "nombre_usuario = :#{#checkPrimerLoginDTO.nombreUsuario} AND " +
            "clave = :#{#checkPrimerLoginDTO.clave} " +
            "AND estado = 1")
    Optional<Usuario> buscarUsuarioPorCredenciales(CheckPrimerLoginDTO checkPrimerLoginDTO);

    @Query("SELECT u.id_usuario, u.nombres, u.apellidos, u.tipo FROM usuario u WHERE id_usuario = :idUsuario")
    Usuario buscarUsuarioPorId(int idUsuario);

    @Modifying
    @Query("UPDATE usuario SET " +
            "primer_login = 0, " +
            "clave = :#{#completarDatosDTO.clave}, " +
            "id_pregunta_seguridad = :#{#completarDatosDTO.preguntaSeguridad}, " +
            "rpta_secreta = :#{#completarDatosDTO.rptaSecreta} " +
            "WHERE id_usuario = :#{#completarDatosDTO.idUsuario}")
    void completarDatosUsuario(CompletarDatosDTO completarDatosDTO);

    @Query("SELECT u.id_usuario, u.id_pregunta_seguridad FROM usuario u WHERE " +
            "nombre_usuario = :#{#validarDatosUsuarioDTO.nombreUsuario} AND " +
            "nombres = :#{#validarDatosUsuarioDTO.nombres} AND " +
            "apellidos = :#{#validarDatosUsuarioDTO.apellidos} AND " +
            "estado = 1 AND " +
            "primer_login = 0")
    Optional<Usuario> buscarUsuarioPorDatos(ValidarDatosUsuarioDTO validarDatosUsuarioDTO);


    @Query("SELECT rpta_secreta FROM usuario WHERE id_usuario = :#{#validarRptaSecretaDTO.idUsuario}")
    String buscarRptaSecreta(ValidarRptaSecretaDTO validarRptaSecretaDTO);

    @Modifying
    @Query("UPDATE usuario SET clave = :#{#reestablecerClaveDTO.clave} WHERE id_usuario = :#{#reestablecerClaveDTO.idUsuario}")
    void actualizarClave(ReestablecerClaveDTO reestablecerClaveDTO);

    @Query("SELECT u.id_usuario, u.nombre_usuario, u.nombres, u.apellidos, u.correo, u.tipo, u.estado " +
            "FROM usuario u WHERE u.tipo != 'Administrador'")
    List<Usuario> buscarUsuariosNoAdministradores();

    @Query("SELECT u.id_usuario, u.nombre_usuario, u.nombres, u.apellidos FROM usuario u WHERE " +
            "(:filtro = 'nombre_usuario' AND u.nombre_usuario LIKE CONCAT(:valor, '%')) OR " +
            "(:filtro = 'nombres' AND u.nombres LIKE CONCAT(:valor, '%')) OR " +
            "(:filtro = 'apellidos' AND u.apellidos LIKE CONCAT(:valor, '%')) OR " +
            "(:filtro = 'tipo' AND u.tipo = :valor)")
    Optional<List<Usuario>> buscarUsuariosPorFiltro(String filtro, String valor);

    @Modifying
    @Query("UPDATE usuario SET estado = :#{#cambiarEstadoUsuarioDTO.estado} WHERE id_usuario = :#{#cambiarEstadoUsuarioDTO.idUsuario}")
    void cambiarEstadoUsuario(CambiarEstadoUsuarioDTO cambiarEstadoUsuarioDTO);

    @Query("SELECT u.nombre_usuario FROM usuario u WHERE nombre_usuario = :nombreUsuario")
    Optional<String> buscarNombreUsuarioExistente(String nombreUsuario);

    @Query("SELECT u.correo FROM usuario u WHERE correo = :correo")
    Optional<String> buscarCorreoUsuarioExistente(String correo);

    @Modifying
    @Query("INSERT INTO usuario (nombre_usuario, clave, nombres, apellidos, correo, tipo) " +
            "VALUES (:#{#registrarUsuarioDTO.nombreUsuario}, " +
            ":#{#registrarUsuarioDTO.clave}, " +
            ":#{#registrarUsuarioDTO.nombres}, " +
            ":#{#registrarUsuarioDTO.apellidos}, " +
            ":#{#registrarUsuarioDTO.correo}, " +
            ":#{#registrarUsuarioDTO.tipo})")
    void registrarUsuario(RegistrarUsuarioDTO registrarUsuarioDTO);

    @Query("SELECT LAST_INSERT_ID()")
    int obtenerUltimoIdUsuario();

    @Modifying
    @Query("UPDATE usuario SET correo =:correo WHERE id_usuario =:idUsuario")
    void actualizarCorreo(String correo, int idUsuario);

    @Query("SELECT correo from usuario WHERE id_usuario = :idUsuario")
    String buscarCorreoUsuarioPorId(int idUsuario);

}
