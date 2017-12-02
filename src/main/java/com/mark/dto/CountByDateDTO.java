package com.mark.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by yuuto on 12/2/17.
 */
@Data
public class CountByDateDTO {
    private Date date;
    private Integer count;

    public static CountByDateDTO from(Date date, Integer count){
        CountByDateDTO dto = new CountByDateDTO();
        dto.setDate(date);
        dto.setCount(count);
        return dto;
    }
}
