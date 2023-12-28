package com.toy.knit.request.auth;

import lombok.Data;

@Data
public class Signup {

    private String email;
    private String name;
    private String password;
}
