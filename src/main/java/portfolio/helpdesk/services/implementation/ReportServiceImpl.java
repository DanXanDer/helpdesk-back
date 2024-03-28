package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.Report;
import portfolio.helpdesk.repositories.IGenericRepo;
import portfolio.helpdesk.repositories.IReportRepo;
import portfolio.helpdesk.services.IReportService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl extends CrudImpl<Report, Integer> implements IReportService {

    private final IReportRepo repo;

    @Override
    protected IGenericRepo<Report, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Report> findAll() {
        return getRepo().findAll();
    }
}
