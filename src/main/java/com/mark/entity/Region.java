package com.mark.entity;

import lombok.Data;

/**
 * Created by yuuto on 12/1/17.
 */

@Data
public class Region {
    private Integer id;
    private String name;
    private Country country;

}
