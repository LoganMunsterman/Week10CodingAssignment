package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Cat;

public class CatDao {
  
  private Connection connection;
  private final String GET_CATS_QUERY = "SELECT * FROM cats";
  private final String UPDATE_CATTITUDE_QUERY = "UPDATE cats SET attitude = ? WHERE id = ?";
  private final String GET_CAT_BY_ID_QUERY = "SELECT * FROM cats WHERE id = ?";
  private final String ADD_NEW_CAT_QUERY = "INSERT INTO cats(name, attitude, gender) VALUES(?,?,?)";
  private final String REMOVE_CAT_BY_ID_QUERY = "DELETE FROM cats WHERE id = ?";
  
  public CatDao() {
    connection = DBConnection.getConnection();    
  }
  
  public List<Cat> getCats() throws SQLException {
    ResultSet rs = connection.prepareStatement(GET_CATS_QUERY).executeQuery();
    List<Cat> cats = new ArrayList<Cat>();
    
    while(rs.next()) {
      cats.add(populateCat(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
    }
    return cats;
  }
  
  private Cat populateCat(int id, String name, String attitude, String gender) {
    return new Cat(id, name, attitude, gender);
  }
  
  public boolean updateCattitude(int id, String attitude) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(UPDATE_CATTITUDE_QUERY);
    ps.setString(1, attitude);
    ps.setInt(2,  id);
    int numberRowsUpdated = ps.executeUpdate();
    return numberRowsUpdated == 1;
  }
  public Cat getCat(int id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(GET_CAT_BY_ID_QUERY);
    ps.setInt(1,  id);
    ResultSet rs = ps.executeQuery();
    rs.next();
    return populateCat(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
    }
  
  public void addNewCat(String name, String attitude, String gender) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(ADD_NEW_CAT_QUERY);
    ps.setString(1, name);
    ps.setString(2, attitude);
    ps.setString(3, gender);
    ps.executeUpdate();
  }
  
  public void removeCatById(int id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(REMOVE_CAT_BY_ID_QUERY);
    ps.setInt(1, id);
    ps.executeUpdate();
  }

}