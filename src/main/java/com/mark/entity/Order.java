package com.mark.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by yuuto on 12/1/17.
 */

@Data
public class Order {
    private Integer id;
    private Employer employer;
    private Region region;
    private Date ordersDate;
    private Double total;
}
