package com.mark.actions;
import com.google.gson.Gson;
import com.mark.dao.OrderDAO;
import com.mark.dto.CountByDateDTO;
import com.mark.dto.TotalTableDTO;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;

import java.util.List;

@Data
public class StatsAction extends ActionSupport {

    private String graphJson;
    private String tableJson;
    private Gson gson;


    public StatsAction() {
        List<CountByDateDTO> countByDates = OrderDAO.getInstance().findCountOfOrdersForAllOrdersDate();
        List<TotalTableDTO> totalTables = OrderDAO.getInstance().findTotalForEmployersAndCountry();
        gson = new Gson();
        graphJson = gson.toJson(countByDates);
        tableJson = gson.toJson(totalTables);
    }

    public String display() {
        return INPUT;
    }
}
