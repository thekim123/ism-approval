package com.hictc.ism.dto.user;

import com.hictc.ism.entity.user.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private Long id;
    private String company;
    private UserDto.Get teamHead;

    public OrganizationDto entityToDto(Organization organization) {
        this.id = organization.getId();
        this.company = organization.getCompany().getCompanyName();
        this.teamHead = new UserDto.Get().entityToDto(organization.getTeamHead());
        return this;
    }
}
