package com.example.cloudsky.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "introduction")
    private String introduction;

//    @OneToMany(mappedBy = "user")
//    private List<Post> postList = new ArrayList<>();

    @Transient
    @Column(nullable = true)
    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private UserRoleEnum role;

    public User(String id, String password, String username, String introduction) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.introduction = introduction;
    }
}
