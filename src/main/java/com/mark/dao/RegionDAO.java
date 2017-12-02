package com.mark.dao;

import com.mark.dao.config.MySQLDataSourceConfig;
import com.mark.entity.Country;
import com.mark.entity.Region;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuuto on 12/1/17.
 */
public class RegionDAO {

    private static final String FIND_ALL_REGION_QUERY = "SELECT * FROM regions JOIN countries ON countries.id = regions.countryId";
    private static volatile RegionDAO instance;

    private RegionDAO() {
    }

    public static RegionDAO getInstance() {
        RegionDAO localInstance = instance;
        if (localInstance == null) {
            synchronized (RegionDAO.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RegionDAO();
                }
            }
        }
        return localInstance;
    }


    public List<Region> findAllRegions() {
        List<Region> regions = new ArrayList<>();

        try (Connection connection = MySQLDataSourceConfig.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_REGION_QUERY)) {

            while (resultSet.next()) {
                Country country = new Country();
                country.setId(resultSet.getInt("countries.id"));
                country.setName(resultSet.getString("countries.name"));
                Region region = new Region();
                region.setId(resultSet.getInt("regions.id"));
                region.setName(resultSet.getString("regions.name"));
                region.setCountry(country);
                regions.add(region);
            }

        } catch (SQLException e) {
            //LOGER
        }
        return regions;
    }



}
