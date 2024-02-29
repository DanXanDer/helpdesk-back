package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.AreaRequestDTO;
import portfolio.helpdesk.DTO.request.CampusRequestDTO;
import portfolio.helpdesk.DTO.request.CompanyRequestDTO;
import portfolio.helpdesk.DTO.response.CompanyResponseDTO;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Campus;
import portfolio.helpdesk.models.Company;

import java.util.List;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    default Company convertToEntity(CompanyRequestDTO companyRequestDTO) {
        Company company = new Company();
        company.setName(companyRequestDTO.name());
        List<Campus> campusList = companyRequestDTO.campus().stream().map(campusRequestDTO -> {
            Campus campus = this.convertToEntity(campusRequestDTO);
            List<Area> areasList = campusRequestDTO.areas().stream().map(this::convertToEntity).toList();
            campus.setAreas(areasList);
            areasList.forEach(area -> area.setCampus(campus));
            return campus;
        }).toList();
        campusList.forEach(campus -> campus.setCompany(company));
        company.setCampus(campusList);
        return company;
    }

    default Campus convertToEntity(CampusRequestDTO campusRequestDTO) {
        Campus campus = new Campus();
        campus.setName(campusRequestDTO.name());
        return campus;
    }

    default Area convertToEntity(AreaRequestDTO areaRequestDTO) {
        Area area = new Area();
        area.setName(areaRequestDTO.name());
        return area;
    }

    /*@Mapping(target = "enabled", ignore = true)
    @Mapping(target = "campus.enabled", ignore = true)
    @Mapping(target = "campus.areas.enabled", ignore = true)
    Company convertToEntity(CompanyRequestDTO companyRequestDTO);*/
    CompanyResponseDTO convertToDTO(Company company);
}
