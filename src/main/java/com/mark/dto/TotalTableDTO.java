package com.mark.dto;

import lombok.Data;

/**
 * Created by yuuto on 12/2/17.
 */
@Data
public class TotalTableDTO {
    private String employer;
    private String country;
    private Double total;

    public static TotalTableDTO from(String employer, String country, Double total){
        TotalTableDTO dto = new TotalTableDTO();
        dto.setEmployer(employer);
        dto.setCountry(country);
        dto.setTotal(total);
        return dto;
    }
}
