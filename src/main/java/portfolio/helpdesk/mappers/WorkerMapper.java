package portfolio.helpdesk.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import portfolio.helpdesk.DTO.request.WorkerRequestDTO;
import portfolio.helpdesk.DTO.response.WorkerResponseDTO;
import portfolio.helpdesk.models.Worker;


@Mapper(componentModel = "spring", uses = {UserMapper.class})
public abstract class WorkerMapper {

    @Mapping(target = "userData", source = "user")
    public abstract Worker convertToEntity(WorkerRequestDTO workerRequestDTO);

    @Mapping(target = "user", source = "userData")
    @Mapping(target = "user.role", source = "userData.role.name")
    public abstract WorkerResponseDTO convertToDTO(Worker worker);
}
