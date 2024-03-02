package portfolio.helpdesk.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.WorkerCreationDTO;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.models.Worker;

@Mapper
public interface WorkerMapper {
    WorkerMapper INSTANCE = Mappers.getMapper(WorkerMapper.class);
    UserMapper userMapper = UserMapper.INSTANCE;

    default Worker convertToEntity(WorkerCreationDTO workerCreationDTO) {
        UserData userData = userMapper.convertToEntity(workerCreationDTO.user());
        Worker worker = new Worker();
        worker.setUserData(userData);
        userData.setWorker(worker);
        return worker;
    }

    /*@Mapping(source = "user.idUser", target = "idUser")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.lastname", target = "lastname")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.type", target = "type")
    @Mapping(source = "user.enabled", target = "enabled")
    WorkerResponseDTO convertToDTO(Worker worker);*/
}
