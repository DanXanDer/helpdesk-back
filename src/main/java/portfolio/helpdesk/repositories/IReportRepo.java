package portfolio.helpdesk.repositories;

import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Report;

@Repository
public interface IReportRepo extends IGenericRepo<Report, Integer> {
}
