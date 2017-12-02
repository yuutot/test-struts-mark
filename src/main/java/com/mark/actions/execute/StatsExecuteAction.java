package com.mark.actions.execute;

import com.google.gson.Gson;
import com.mark.dao.OrderDAO;
import com.mark.dto.CountByDateDTO;
import com.mark.dto.TotalTableDTO;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StatsExecuteAction extends ActionSupport {

    private String graphJson;
    private String tableJson;
    private Date from;
    private Date to;
    private Gson gson;

    @Override
    public void validate() {
        if (from == null) {
            addFieldError("from", "Field is empty");
        }
        if (to == null) {
            addFieldError("to", "Field is empty");
        }
    }

    public StatsExecuteAction() {
        List<CountByDateDTO> countByDates = OrderDAO.getInstance().findCountOfOrdersForAllOrdersDate();
        List<TotalTableDTO> totalTables = OrderDAO.getInstance().findTotalForEmployersAndCountry();
        gson = new Gson();
        graphJson = gson.toJson(countByDates);
        tableJson = gson.toJson(totalTables);
    }

    @Override
    public String execute() throws Exception {
        List<CountByDateDTO> countByDates = OrderDAO.getInstance().findCountOfOrdersForAllOrdersDateForSpecificDate(from, to);
        graphJson = gson.toJson(countByDates);
        return SUCCESS;
    }
}
