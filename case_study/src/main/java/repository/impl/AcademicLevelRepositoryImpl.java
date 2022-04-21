package repository.impl;

import models.AcademicLevel;
import repository.BaseRepository;
import repository.IAcademicLevelRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcademicLevelRepositoryImpl implements IAcademicLevelRepository {
   private BaseRepository baseRepository = new BaseRepository();
   private static final String SELECT_ALL_ACADEMIC_LEVEL = "SELECT academic_id, level_name FROM academic_level";
    @Override
    public List<AcademicLevel> getList() {
        List<AcademicLevel> academicLevelList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_ACADEMIC_LEVEL);
            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id;
            String level;
            while (resultSet.next()){
                id = resultSet.getInt("academic_id");
                level = resultSet.getString("level_name");
                academicLevelList.add(new AcademicLevel(id,level));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return academicLevelList;
    }
}
