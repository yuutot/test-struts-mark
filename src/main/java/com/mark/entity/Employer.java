package com.mark.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by yuuto on 12/1/17.
 */
@Data
@RequiredArgsConstructor
public class Employer {
    private Integer id = 2;
    private String login;
    private String password;
    private String name;


}
