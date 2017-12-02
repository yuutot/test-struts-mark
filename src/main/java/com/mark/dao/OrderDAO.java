package com.mark.dao;

import com.mark.dao.config.MySQLDataSourceConfig;
import com.mark.dto.CountByDateDTO;
import com.mark.dto.TotalTableDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yuuto on 12/1/17.
 */
public class OrderDAO {

    private static volatile OrderDAO instance;
    private static final String SAVE_ORDER_QUERY = "INSERT INTO `orders` (employerId, regionId, orders_date, total) VALUES (?, ?, ?, ?)";
    private static final String FIND_WITHOUT_DATE_QUERY = "SELECT orders_date, COUNT(id) FROM orders GROUP BY orders_date";
    private static final String FIND_WITH_DATE_QUERY = "SELECT orders_date, COUNT(id) FROM orders GROUP BY orders_date HAVING orders_date BETWEEN ? AND ?";
    private static final String FIND_GROUPED_BY_COUNTRY_AND_EMPLOYER =
            "SELECT employes.name, countries.name, SUM(orders.total) " +
            "FROM orders JOIN regions on regions.id = orders.regionId " +
            "JOIN employes on employes.id = orders.employerId " +
            "JOIN countries on countries.id = regions.countryId " +
            "GROUP BY employes.name, countries.id";

    private OrderDAO() {
    }

    public static OrderDAO getInstance() {
        OrderDAO localInstance = instance;
        if (localInstance == null) {
            synchronized (OrderDAO.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new OrderDAO();
                }
            }
        }
        return localInstance;
    }

    public void saveOrder(Integer employerId, Integer orderId, Date orderDate, Double total) {
        try (Connection connection = MySQLDataSourceConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_ORDER_QUERY)) {

            statement.setInt(1, employerId);
            statement.setInt(2, orderId);
            statement.setObject(3, orderDate);
            statement.setDouble(4, total);
            statement.executeUpdate();

        } catch (SQLException e) {
            //LOGER
        }
    }

    public List<CountByDateDTO> findCountOfOrdersForAllOrdersDate() {
        List<CountByDateDTO> countByDate = new ArrayList<>();

        try (Connection connection = MySQLDataSourceConfig.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_WITHOUT_DATE_QUERY)) {

            while (resultSet.next()) {
                Date date = resultSet.getDate(1);
                Integer count = resultSet.getInt(2);
                countByDate.add(CountByDateDTO.from(date, count));
            }

        } catch (SQLException e) {
            //LOGER
        }

        return countByDate;
    }

    public List<CountByDateDTO> findCountOfOrdersForAllOrdersDateForSpecificDate(Date from, Date to) {
        List<CountByDateDTO> countByDate = new ArrayList<>();

        try (Connection connection = MySQLDataSourceConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_WITH_DATE_QUERY)) {
            statement.setObject(1, from);
            statement.setObject(2, to);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Date date = resultSet.getDate(1);
                    Integer count = resultSet.getInt(2);
                    countByDate.add(CountByDateDTO.from(date, count));
                }
            }
        } catch (SQLException e) {
            //LOGER
        }
        return countByDate;
    }

    public List<TotalTableDTO> findTotalForEmployersAndCountry() {
        List<TotalTableDTO> countByDate = new ArrayList<>();

        try (Connection connection = MySQLDataSourceConfig.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_GROUPED_BY_COUNTRY_AND_EMPLOYER)) {

            while (resultSet.next()) {
                String employer = resultSet.getString(1);
                String country = resultSet.getString(2);
                Double total = resultSet.getDouble(3);
                countByDate.add(TotalTableDTO.from(employer, country, total));
            }

        } catch (SQLException e) {
            //LOGER
        }

        return countByDate;
    }

}
