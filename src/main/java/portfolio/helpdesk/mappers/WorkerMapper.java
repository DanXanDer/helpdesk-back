package portfolio.helpdesk.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.WorkerRequestDTO;
import portfolio.helpdesk.DTO.response.WorkerResponseDTO;
import portfolio.helpdesk.models.Worker;

@Mapper
public interface WorkerMapper {
    WorkerMapper INSTANCE = Mappers.getMapper(WorkerMapper.class);

    @Mapping(source = "idArea", target = "user.area.idArea")
    @Mapping(source = "username", target = "user.username")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "name", target = "user.name")
    @Mapping(source = "lastname", target = "user.lastname")
    @Mapping(source = "email", target = "user.email")
    @Mapping(source = "type", target = "user.type")
    Worker convertToEntity(WorkerRequestDTO workerRequestDTO);

    @Mapping(source = "user.idUser", target = "idUser")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.lastname", target = "lastname")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.type", target = "type")
    @Mapping(source = "user.enabled", target = "enabled")
    @Mapping(source = "user.area.name", target = "area")
    @Mapping(source = "user.area.branch.name", target = "branch")
    @Mapping(source = "user.area.branch.company.name", target = "company")
    WorkerResponseDTO convertToDTO(Worker worker);
}
