package com.example.helpdesk2.repositories;

import com.example.helpdesk2.DTO.*;
import com.example.helpdesk2.models.Cliente;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    @Modifying
    @Query("INSERT INTO cliente (id_usuario, id_area, anydesk, teamviewer) VALUES " +
            "(:#{#registrarClienteDTO.idUsuario}, " +
            ":#{#registrarClienteDTO.idArea}, " +
            ":#{#registrarClienteDTO.anydesk}, " +
            ":#{#registrarClienteDTO.teamviewer})")
    void registrarCliente(RegistrarClienteDTO registrarClienteDTO);

    @Query("SELECT id_cliente, anydesk, teamviewer FROM cliente WHERE id_usuario = :idUsuario")
    Cliente buscarClientePorIdUsuario(int idUsuario);

    @Modifying
    @Query("UPDATE cliente SET " +
            "anydesk = :#{#actualizarDatosClienteDTO.anydesk}, " +
            "teamviewer = :#{#actualizarDatosClienteDTO.teamviewer} " +
            "WHERE id_cliente =:idCliente")
    void actualizarDatosCliente(ActualizarDatosClienteDTO actualizarDatosClienteDTO, int idCliente);

    @Modifying
    @Query("UPDATE usuario SET clave = :#{#cambiarClaveClienteDTO.clave} WHERE id_usuario = :idUsuario")
    void actualizarClave(CambiarClaveClienteDTO cambiarClaveClienteDTO, int idUsuario);

    @Query("SELECT u.nombre_usuario, u.nombres, u.apellidos, u.correo, a.nombre_area, s.nombre_sede, e.nombre_empresa, c.anydesk, c.teamviewer " +
            "FROM cliente c " +
            "INNER JOIN usuario u " +
            "ON c.id_usuario = u.id_usuario " +
            "INNER JOIN area a " +
            "ON c.id_area = a.id_area " +
            "INNER JOIN sede s " +
            "ON a.id_sede = s.id_sede " +
            "INNER JOIN empresa e " +
            "ON s.id_empresa = e.id_empresa " +
            "WHERE c.id_cliente = :idCliente")
    DatosClienteDTO buscarDatosCliente(int idCliente);

    @Query("SELECT u.id_usuario, u.nombre_usuario, u.nombres, u.apellidos, u.correo, u.estado, a.nombre_area, s.nombre_sede, e.nombre_empresa, c.anydesk, c.teamviewer " +
            "FROM cliente c " +
            "INNER JOIN usuario u " +
            "ON c.id_usuario = u.id_usuario " +
            "INNER JOIN area a " +
            "ON c.id_area = a.id_area " +
            "INNER JOIN sede s " +
            "ON a.id_sede = s.id_sede " +
            "INNER JOIN empresa e " +
            "ON s.id_empresa = e.id_empresa ")
    List<DatosClienteDTO> buscarTodosLosClientes();

}
