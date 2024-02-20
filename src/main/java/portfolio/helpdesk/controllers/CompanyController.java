package portfolio.helpdesk.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.services.ICompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final ICompanyService companyService;

    @PostMapping

}
