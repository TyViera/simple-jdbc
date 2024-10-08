package com.travelport.driver;

import com.github.javafaker.Faker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

  private static final Faker FAKER = new Faker();

  public static void main(String[] args) throws SQLException {
    try (var connection = DriverManager.getConnection("jdbc:h2:mem:testdb")) {

      try (var statement = connection.createStatement()) {
        statement.execute(
            "CREATE TABLE users (id INT, character_name VARCHAR(255), house VARCHAR(1000))");
      }

      var insertResult = createUser(connection, 250);
      System.out.println("Result of insert: " + insertResult);

      // connection.prepareCall("");//For store procedures
      // connection.prepareStatement("CALL SP (?, ?)"); // TBD

      var selectQuery = "SELECT id, character, house FROM users WHERE character = ?";
      //var selectQuery2 = "SELECT id, character, house FROM users WHERE character = :character?";
      try (var statement = connection.prepareStatement(selectQuery)) {
        statement.setString(1, "Daenerys Targaryen");
        ///statement.setString("character", "Daenerys Targaryen");
        var resultSet = statement.executeQuery();

        //JPA - JAVA PERSISTENCE API
        while (resultSet.next()) {
          var id = resultSet.getInt("id");
          var character = resultSet.getString("character");
          var house = resultSet.getString("house");
          System.out.println(
              "I've found user with id: %d, character: %s and house: %s"
                  .formatted(id, character, house));
        }
      }
    }
  }

  private static int createUser(Connection connection, int number) throws SQLException {
    var sql = "INSERT INTO users (id, character, house) VALUES (?, ?, ?);";
    var sum = 0;
    for (var i = 0; i < number; i++) {
      var character = FAKER.gameOfThrones();
      try (var statement = connection.prepareStatement(sql)) {
        statement.setInt(1, i + 1);
        statement.setString(2, character.character());
        statement.setString(3, character.house());
        sum += statement.executeUpdate();
      }
    }
    return sum;
  }
}
