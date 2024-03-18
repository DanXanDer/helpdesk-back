package portfolio.helpdesk.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import portfolio.helpdesk.DTO.request.BranchRequestDTO;
import portfolio.helpdesk.DTO.request.BranchUpdateDTO;
import portfolio.helpdesk.DTO.response.BranchResponseDTO;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.services.ICompanyService;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {AreaMapper.class})
public abstract class BranchMapper {

    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private ICompanyService companyService;

    public Branch convertToEntity(BranchRequestDTO branchRequestDTO) {
        Branch branch = new Branch();
        branch.setName(branchRequestDTO.getName());
        List<Area> areas = areaMapper.convertToEntityList(branchRequestDTO.getAreas());
        areas.forEach(area -> area.setBranch(branch));
        branch.setAreas(areas);
        if (branchRequestDTO.getIdCompany() != null) {
            Company company = companyService.getReferenceById(branchRequestDTO.getIdCompany());
            branch.setCompany(company);
        }
        return branch;
    }

    public abstract Set<Branch> convertToEntityList(Set<BranchRequestDTO> branches);

    public abstract BranchResponseDTO convertToDTO(Branch branch);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDTO(BranchUpdateDTO branchUpdateDTO, @MappingTarget Branch branch);

}
