package com.mark.dao;

import com.mark.dao.config.MySQLDataSourceConfig;
import com.mark.entity.Employer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by yuuto on 12/1/17.
 */
public class EmployerDAO {

    private static volatile EmployerDAO instance;
    private static final String FIND_ALL_EMPLOYER_QUERY = "SELECT * FROM employes";
    private static final String FIND_BY_LOGIN = "SELECT * FROM employes where login = ? limit 1";

    private EmployerDAO() {
    }

    public static EmployerDAO getInstance() {
        EmployerDAO localInstance = instance;
        if (localInstance == null) {
            synchronized (EmployerDAO.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EmployerDAO();
                }
            }
        }
        return localInstance;
    }

    public List<Employer> findAllEmployers() {
        List<Employer> employers = new ArrayList<>();

        try (Connection connection = MySQLDataSourceConfig.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_EMPLOYER_QUERY)) {

            while (resultSet.next()) {
                Employer employer = new Employer();
                employer.setId(resultSet.getInt("employes.id"));
                employer.setLogin(resultSet.getString("employes.login"));
                employer.setPassword(resultSet.getString("employes.password"));
                employer.setName(resultSet.getString("employes.name"));
                employers.add(employer);
            }

        } catch (SQLException e) {
            //LOGER
        }

        return employers;
    }

    public Optional<Employer> findEmployerByLogin(String login) {

        try (Connection connection = MySQLDataSourceConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN)) {

            statement.setString(1, login);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Employer employer = new Employer();
                    employer.setId(resultSet.getInt("employes.id"));
                    employer.setLogin(resultSet.getString("employes.login"));
                    employer.setPassword(resultSet.getString("employes.password"));
                    employer.setName(resultSet.getString("employes.name"));
                    return Optional.of(employer);
                }
            }

        } catch (SQLException e) {
            //LOGER
        }

        return Optional.empty();
    }

}
