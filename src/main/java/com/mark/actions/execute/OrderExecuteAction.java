package com.mark.actions.execute;

import com.mark.dao.EmployerDAO;
import com.mark.dao.OrderDAO;
import com.mark.dao.RegionDAO;
import com.mark.entity.Employer;
import com.mark.entity.Region;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderExecuteAction extends ActionSupport {

    private Integer selectedEmployer;
    private Integer selectedRegion;
    private Double total;
    private Date date;

    private List<Employer> employers;
    private List<Region> regions;

    public OrderExecuteAction() {
        regions = RegionDAO.getInstance().findAllRegions();
        employers = EmployerDAO.getInstance().findAllEmployers();
    }

    @Override
    public void validate() {
        if(selectedEmployer == -1){
            addFieldError("selectedEmployer", "Field is empty");
        }
        if (selectedRegion == -1){
            addFieldError("selectedRegion", "Field is empty");
        }
        if(total == null || total <= 0){
            addFieldError("total", "Field is empty");
        }
        if(date == null){
            addFieldError("date", "Field is empty");
        }
    }

    public Date getCurrentDate() {
        return new Date();
    }

    @Override
    public String execute() throws Exception {
        OrderDAO.getInstance().saveOrder(selectedEmployer, selectedRegion, date, total);
        total = null;
        date = getCurrentDate();
        selectedEmployer = -1;
        selectedRegion = -1;
        return SUCCESS;
    }
}