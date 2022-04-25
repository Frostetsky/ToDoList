package com.todolist.app.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index")
    private long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRole(Role role) {
        this.role = role;
    }
}
