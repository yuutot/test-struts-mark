package com.mark.actions;

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
public class OrderAction extends ActionSupport {

    private List<Employer> employers;
    private List<Region> regions;

    public OrderAction() {
        regions = RegionDAO.getInstance().findAllRegions();
        employers = EmployerDAO.getInstance().findAllEmployers();
    }

    public Date getCurrentDate() {
        return new Date();
    }

    public String display() {
        return NONE;
    }
}
