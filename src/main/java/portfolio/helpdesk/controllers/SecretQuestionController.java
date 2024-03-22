package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.SecretQuestionDTO;
import portfolio.helpdesk.mappers.SecretQuestionMapper;
import portfolio.helpdesk.services.ISecretQuestionService;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/secret-questions")
public class SecretQuestionController {
    private final ISecretQuestionService secretQuestionService;
    private final SecretQuestionMapper sqMapper;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SecretQuestionDTO secretQuestionDTO) {
        Integer secretQuestionId = secretQuestionService.save(sqMapper.convertToEntity(secretQuestionDTO)).getIdSecretQuestion();
        URI location = URI.create(String.format("/secret-questions/%d", secretQuestionId));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<SecretQuestionDTO>> findAll() {
        List<SecretQuestionDTO> secretQuestionsDTO = secretQuestionService.findAll().stream().map(sqMapper::convertToDTO).toList();
        return ResponseEntity.ok(secretQuestionsDTO);
    }
}
