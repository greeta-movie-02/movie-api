package net.greeta.movie.organization.mapper;

import net.greeta.movie.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import net.greeta.movie.organization.OrganizationDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganizationMapper {
    OrganizationDTO organizationToOrganizationDTO(Organization organization);
    Organization organizationDTOToOrganization(OrganizationDTO organizationDTO);
}
