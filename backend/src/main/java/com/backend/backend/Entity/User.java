package com.backend.backend.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


public abstract class User {
    @Id @Getter
    String uid = "";
    String name = "";
    String email = "";
    String password;

}
