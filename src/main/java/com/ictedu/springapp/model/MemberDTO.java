package com.ictedu.springapp.model;

import lombok.Data;

@Data
public class MemberDTO {
    private String id;
    private String email;
    private String password;
    private String name;
    private String profilePicture;
}
