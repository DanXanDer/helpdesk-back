package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.SecretQuestionDTO;
import portfolio.helpdesk.mappers.SecretQuestionMapper;
import portfolio.helpdesk.models.SecretQuestion;
import portfolio.helpdesk.services.ISecretQuestionService;

import java.net.URI;


@RestController
@RequestMapping("/secret-questions")
public class SecretQuestionController {
    private final ISecretQuestionService secretQuestionService;

    public SecretQuestionController(ISecretQuestionService secretQuestionService) {
        this.secretQuestionService = secretQuestionService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SecretQuestionDTO secretQuestionDTO) {
        SecretQuestion secretQuestion = secretQuestionService.save(SecretQuestionMapper.INSTANCE.convertToEntity(secretQuestionDTO));
        URI location = URI.create(String.format("/secret-questions/%d", secretQuestion.getIdSecretQuestion()));
        return ResponseEntity.created(location).build();
    }
}
