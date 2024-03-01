package portfolio.helpdesk.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.WorkerCreationDTO;
import portfolio.helpdesk.DTO.response.WorkerResponseDTO;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.User;
import portfolio.helpdesk.models.Worker;

@Mapper
public interface WorkerMapper {
    WorkerMapper INSTANCE = Mappers.getMapper(WorkerMapper.class);

    default Worker convertToEntity(WorkerCreationDTO workerCreationDTO) {
        Area area = new Area();
        area.setIdArea(workerCreationDTO.user().idArea());
        User user = new User();
        user.setArea(area);
        user.setUsername(workerCreationDTO.user().username());
        user.setPassword(workerCreationDTO.user().password());
        user.setName(workerCreationDTO.user().name());
        user.setLastname(workerCreationDTO.user().lastname());
        user.setEmail(workerCreationDTO.user().email());
        user.setType(workerCreationDTO.user().type());
        user.setArea(area);
        Worker worker = new Worker();
        worker.setUser(user);
        user.setWorker(worker);
        return worker;
    }

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
