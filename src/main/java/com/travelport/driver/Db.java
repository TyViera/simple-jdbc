package com.travelport.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

  public Connection connect() throws SQLException {
    return DriverManager.getConnection("jdbc:h2:mem:testdb");
  }

}
