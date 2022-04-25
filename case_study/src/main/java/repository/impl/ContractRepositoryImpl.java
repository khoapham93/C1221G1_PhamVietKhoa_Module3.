package repository.impl;

import dto.ContractDTO;
import dto.FacilityDTO;
import models.Contract;
import models.Facility;
import repository.BaseRepository;
import repository.IContractRepository;
import repository.IFacilityRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractRepositoryImpl implements IContractRepository {
    private BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_ALL_CONTRACT_DTO = "SELECT ct.contract_id,\n" +
            "       ct.date_signed,\n" +
            "       ct.date_end,\n" +
            "       ct.deposit,\n" +
            "       e.name,\n" +
            "       c.name,\n" +
            "       f.facility_name\n" +
            "FROM contract ct\n" +
            "         LEFT JOIN employee e ON ct.employee_id = e.employee_id\n" +
            "         LEFT JOIN customer c ON ct.customer_id = c.customer_id\n" +
            "         LEFT JOIN facility f ON ct.facility_id = f.facility_id";

    private static final String INSERT_CONTRACT = "INSERT INTO contract (\n" +
            "                      date_signed,\n" +
            "                      date_end,\n" +
            "                      deposit,\n" +
            "                      employee_id,\n" +
            "                      customer_id,\n" +
            "                      facility_id)\n" +
            "VALUES(?,?,?,?,?,?)";

    @Override
    public List<ContractDTO> getList() {
        List<ContractDTO> contractDTOList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_CONTRACT_DTO);

            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id;
            LocalDate startDate;
            LocalDate endDate;
            Double deposit;
            Double total = null;
            String employee;
            String customer;
            String facility;

            while (resultSet.next()) {
                id = resultSet.getInt("contract_id");
                startDate = resultSet.getDate("date_signed").toLocalDate();
                endDate = resultSet.getDate("date_end").toLocalDate();
                deposit = resultSet.getDouble("deposit");
                employee = resultSet.getString("e.name");
                customer = resultSet.getString("c.name");
                facility = resultSet.getString("facility_name");

                contractDTOList.add(new ContractDTO(
                        id,
                        startDate,
                        endDate,
                        deposit,
                        total,
                        employee,
                        customer,
                        facility
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contractDTOList;

    }

    @Override
    public Contract findById(Integer id) {
        return null;
    }

    @Override
    public List<ContractDTO> search(String fieldSearch1, String fieldSearch2, String fieldSearch3, String searchKey1, String searchKey2, String searchKey3) {
        return null;
    }

    @Override
    public void save(Contract contract) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(INSERT_CONTRACT);
            preparedStatement.setDate(1, Date.valueOf(contract.getStartDate()));
            preparedStatement.setDate(2, Date.valueOf(contract.getEndDate()));
            preparedStatement.setDouble(3, contract.getDeposit());
            preparedStatement.setInt(4, contract.getEmployeeId());
            preparedStatement.setInt(5, contract.getCustomerId());
            preparedStatement.setInt(6, contract.getFacilityId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public boolean update(Contract contract) {
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }
}
