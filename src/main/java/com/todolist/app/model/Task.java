package com.todolist.app.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index")
    private long id;

    @Column(name = "name")
    @NotNull
    @Size(max = 50)
    private String name;

    @Column(name = "description")
    @Size(min = 10, max = 500)
    @NotNull
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @Column(name = "status")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean done = false;

    @Column(name = "created")
    private LocalDateTime createdTask = LocalDateTime.now().withNano(0);

    @Column(name = "completed")
    private LocalDateTime completedTask;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
