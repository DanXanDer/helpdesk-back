package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.SecretQuestionCreationDTO;
import portfolio.helpdesk.DTO.response.SecretQuestionResponse;
import portfolio.helpdesk.mappers.SecretQuestionMapper;
import portfolio.helpdesk.models.SecretQuestion;
import portfolio.helpdesk.services.ISecretQuestionService;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/secret-questions")
public class SecretQuestionController {
    private final ISecretQuestionService secretQuestionService;
    private final SecretQuestionMapper sqMapper = SecretQuestionMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SecretQuestionCreationDTO secretQuestionCreationDTO) {
        SecretQuestion secretQuestion = secretQuestionService.save(sqMapper.convertToEntity(secretQuestionCreationDTO));
        URI location = URI.create(String.format("/secret-questions/%d", secretQuestion.getIdSecretQuestion()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<SecretQuestionResponse>> findAll() {
        List<SecretQuestionResponse> secretQuestionsDTO = secretQuestionService.findAll().stream().map(sqMapper::convertToDTO).toList();
        return ResponseEntity.ok(secretQuestionsDTO);
    }
}
