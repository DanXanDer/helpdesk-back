package portfolio.helpdesk.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.WorkerCreationDTO;
import portfolio.helpdesk.DTO.response.WorkerResponse;
import portfolio.helpdesk.models.Worker;

@Mapper(uses = {UserMapper.class})
public interface WorkerMapper {
    WorkerMapper INSTANCE = Mappers.getMapper(WorkerMapper.class);

    @Mapping(target = "userData.id", source = "id")
    Worker convertToEntity(WorkerCreationDTO workerCreationDTO);

    @Mapping(target = "user", source = "userData")
    @Mapping(target = "user.role", source = "userData.role.name")
    WorkerResponse convertToDTO(Worker worker);

   /* default Worker convertToEntity(WorkerCreationDTO workerCreationDTO) {
        UserData user = new UserData();
        user.setIdUser(workerCreationDTO.id());
        Worker worker = new Worker();
        worker.setUserData(user);
        return worker;
    }*/
}
