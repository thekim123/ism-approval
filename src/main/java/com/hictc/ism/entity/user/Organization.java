package com.hictc.ism.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String code;

    @ManyToOne
    private Company company;

    @ManyToOne
    private User teamHead;

    /**
     * 팀장 세터
     *
     * @param teamHead 팀장 userEntity
     */
    public void withTeamHead(User teamHead) {
        this.teamHead = teamHead;
    }

}
