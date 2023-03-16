package com.hictc.ism.dto.approval;

import com.hictc.ism.dto.user.OrganizationDto;
import com.hictc.ism.dto.user.UserDto;
import com.hictc.ism.entity.approval.ApprovalLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalLineDto {

    private Long id;
    private Integer step;
    private String approvalStatus;
    private UserDto.Get requestUser;
    private OrganizationDto organizationDto;
    private Long approvalId;


    public void entityToDto(ApprovalLine approvalLine) {
        this.id = approvalLine.getId();
        this.step = approvalLine.getStep();
        this.approvalStatus = approvalLine.getApprovalStatus().toString();
        this.requestUser = new UserDto.Get().entityToDto(approvalLine.getRequestUser());
        this.organizationDto = new OrganizationDto().entityToDto(approvalLine.getOrganization());
        this.approvalId = approvalLine.getApproval().getId();
    }

}
