package portfolio.helpdesk.repositories;
import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.SecretQuestion;

@Repository
public interface ISecretQuestionRepo extends IGenericRepo<SecretQuestion, Integer>{
}
