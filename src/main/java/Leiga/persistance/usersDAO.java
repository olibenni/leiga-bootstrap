package Leiga.persistance;

import Leiga.controller.dto.UserDTO;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usersDAO {
  private Logger logger = Logger.getLogger(usersDAO.class.getName());
  private Connection connection;

  //URL FOR DATABASE
  private final String DATA_BASE_URL = ""; // TODO: Add database url

  private List<UserDTO>  usersCache    = new ArrayList<UserDTO>();
  private final String[] columnNames = new String[]{"id", "age", "name", "sessionId"};
  private final String   FETCH_USERS = "select id, age, name from users;";
  private final String   INSERT_USER = "insert into users(age, name, sessionId) values(?,?,?);";
  private final String   DELETE_USER = "delete from users where sessionId = ?;";


  /**
   * [createConnection description]
   * Use: createConnection()
   * After: static variable connection is now connected to database data.db
   */
  public void createConnection()
  {
    connection = null;
    try {
      connection = DriverManager.getConnection(DATA_BASE_URL);
    } catch ( Exception e ) {
      logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  public void deleteUser(String sessionId)
  {
    createConnection();
    try{
      connection.setAutoCommit(false);
      PreparedStatement statement = connection.prepareStatement(DELETE_USER);
      statement.setString(1, sessionId);

      logger.info("SQL query: " + statement.toString());

      statement.executeUpdate();
      connection.commit();
      connection.close();

    } catch (SQLException ex) {
      logger.log(Level.SEVERE, "SQL exception " + ex.getMessage(), ex);
    } catch (Exception e){
      logger.log(Level.SEVERE, "Delete Error", e);
    }
    updateCache();
  }

  public void insertUser(int age, String name, String sessionId)
  {
    createConnection();
    try{
      connection.setAutoCommit(false);
      PreparedStatement statement = connection.prepareStatement(INSERT_USER);

      statement.setInt(1,age);
      statement.setString(2,name);
      statement.setString(3, sessionId);

      logger.info("SQL query: " + statement.toString());

      statement.executeUpdate();
      statement.close();
      connection.commit();
      connection.close();

    } catch (SQLException ex) {
      logger.log(Level.SEVERE, "SQL exception: " + ex.getMessage(), ex);
    } catch (Exception e){
      logger.log(Level.SEVERE, "Insert Ride Error", e);
    }
    updateCache();
  }

  public void updateCache(){
    usersCache= new ArrayList<UserDTO>();
    createConnection();
    try{
      connection.setAutoCommit(false);
      PreparedStatement statement = connection.prepareStatement(FETCH_USERS);

      logger.info("SQL query: " + statement.toString());

      ResultSet resultSet = statement.executeQuery();

      while(resultSet.next()) {
        int age = resultSet.getInt(columnNames[1]);
        String name = resultSet.getString(columnNames[2]);
        String sessionID = resultSet.getString(columnNames[3]);
        UserDTO dao = new UserDTO(age, name, sessionID);
        usersCache.add(dao);
      }

      resultSet.close();
      statement.close();
      connection.close();

    } catch (SQLException ex) {
      logger.log(Level.SEVERE, "SQL exception: " + ex.getMessage(), ex);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Update Cache Error", e);
    }
  }

  public List<UserDTO> fetchRides()
  {
    if(this.usersCache.isEmpty()){
      updateCache();
    }
    return this.usersCache;
  }
}