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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "realname", nullable = false)
    private String realname;

    @Column(name = "introduction")
    private String introduction;

//    @OneToMany(mappedBy = "user")
//    private List<Post> postList = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, String realname, String introduction, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.introduction = introduction;
        this.role = role;
    }
}
// role을 없애는 대신 모든 유저의 권한을 USER 로 설정하도록 했습니다.