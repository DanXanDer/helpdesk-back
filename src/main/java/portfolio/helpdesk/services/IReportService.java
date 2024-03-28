package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Report;

import java.util.List;

public interface IReportService extends ICRUD<Report, Integer> {
    public List<Report> findAll();
}
