package repository.impl;

import models.Position;
import repository.BaseRepository;
import repository.IPositionRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionRepositoryImpl implements IPositionRepository {
    private BaseRepository baseRepository = new BaseRepository();

    private static final String SELECT_ALL_POSITION = "SELECT position_id, position_name FROM position";

    @Override
    public List<Position> getList() {
        List<Position> positions = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.baseRepository.getConnection().prepareStatement(SELECT_ALL_POSITION);
            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id;
            String position;
            while (resultSet.next()) {
                id = resultSet.getInt("position_id");
                position = resultSet.getString("position_name");
                positions.add(new Position(id, position));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return positions;
    }
}
