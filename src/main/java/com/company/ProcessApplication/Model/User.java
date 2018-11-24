package com.company.ProcessApplication.Model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "USER_NAME", nullable = false)
    @Getter @Setter
    private String name;

    @Column(name = "USER_PASS", nullable = false)
    @Getter @Setter
    private String pass;
}
