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
        UserData user = new UserData();
        user.setIdUser(workerCreationDTO.idUser());
        Worker worker = new Worker();
        worker.setUserData(user);
        return worker;
    }

}
