package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.SecretQuestion;
import portfolio.helpdesk.repositories.ISecretQuestionRepo;
import portfolio.helpdesk.services.ISecretQuestionService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SecretQuestionServiceImpl extends CrudImpl<SecretQuestion, Integer> implements ISecretQuestionService {

    private final ISecretQuestionRepo secretQuestionRepo;

    @Override
    protected ISecretQuestionRepo getRepo() {
        return secretQuestionRepo;
    }

    @Override
    public List<SecretQuestion> findAll() {
        return getRepo().findAll();
    }
}
