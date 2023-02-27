package com.hictc.ism.reserve;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@DiscriminatorValue("visitor")
public class Visitor extends Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime birthDay;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
