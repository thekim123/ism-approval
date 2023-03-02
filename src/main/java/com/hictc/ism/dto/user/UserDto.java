package com.hictc.ism.dto.user;

import com.hictc.ism.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Builder
@ToString
public class UserDto {

    @Getter
    @Builder
    public static class Create {
        private String username;
        private String password;
        private String name;
        private String email;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthDay;
    }


    @Getter
    @NoArgsConstructor
    public static class Get {

        private Long id;
        private String username;
        private String name;
        private String email;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthDay;

        public Get entityToDto(User entity) {
            this.id = entity.getId();
            this.username = entity.getUsername();
            this.name = entity.getName();
            this.email = entity.getEmail();
            return this;
        }
    }

}
