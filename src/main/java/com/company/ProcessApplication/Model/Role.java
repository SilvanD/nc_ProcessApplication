package com.company.ProcessApplication.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ROLE_ID", nullable = false)
    @Getter
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "USER_NAME", nullable = false)
    @Getter @Setter
    private User user;

    @Column(name = "ROLE_NAME")
    @Getter @Setter
    private String rolename;

    public Role(User user, String rolename) {
        this.user = user;
        this.rolename = rolename;
    }
}
