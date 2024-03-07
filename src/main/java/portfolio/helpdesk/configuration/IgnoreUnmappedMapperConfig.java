package portfolio.helpdesk.configuration;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IgnoreUnmappedMapperConfig {
}
