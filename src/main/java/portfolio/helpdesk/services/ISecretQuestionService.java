package portfolio.helpdesk.services;

import portfolio.helpdesk.models.SecretQuestion;

import java.util.List;

public interface ISecretQuestionService extends ICRUD<SecretQuestion, Integer>{
    List<SecretQuestion> findAll();
}
