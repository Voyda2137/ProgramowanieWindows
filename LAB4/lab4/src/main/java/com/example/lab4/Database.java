package com.example.lab4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.time.LocalDate;

public class Database {

    private static String url = "jdbc:mysql://localhost:3306/pwsw?serverTimezone=UTC";
    private String username= "root";
    private String password = "root";

    public Database() {
        this.url = "jdbc:mysql://localhost:3306/pwsw?serverTimezone=UTC";
        this.username = "root";
        this.password = "root";
    }

    public void insertUser(String name, String surname, String login, String email, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String sql = "INSERT INTO Logowanie (name, surname, login, email, password, registration_date, events) VALUES (?, ?, ?, ?, ?, FROM_UNIXTIME(?), '[]')";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, login);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setLong(6, System.currentTimeMillis() / 1000L);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User inserted successfully.");
            } else {
                System.out.println("Failed to insert user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(String login) {
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String sql = "DELETE from Logowanie where login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User Deleted successfully.");
            } else {
                System.out.println("No such user exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteEvent(String event) {
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String sql = "DELETE from events where name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, event);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("event Deleted successfully.");
            } else {
                System.out.println("No such event exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void resetPwd(String login) {
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String sql = "UPDATE Logowanie SET password = '' WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password for " +login +" has been reset");
            } else {
                System.out.println("Could not reset password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer authenticateUser(String username, String password) {
        Integer permissions = null;
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement statement = connection.prepareStatement("SELECT permissions FROM Logowanie WHERE (login = ? OR email = ?) AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, username);
            statement.setString(3, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            permissions = resultSet.getInt("permissions");
        } catch (SQLException e) {
            return 2;
        }
        return permissions;
    }
    public ObservableList<Event> selectDataBetweenDates(LocalDate startDate, LocalDate endDate) {
        ObservableList<Event> data = FXCollections.observableArrayList();

        String query = "SELECT name, event_date FROM events WHERE event_date BETWEEN ? AND ?";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(startDate));
            statement.setDate(2, Date.valueOf(endDate));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nazwaWydarzenia = resultSet.getString("name");
                    Date terminWydarzenia = resultSet.getDate("event_date");

                    data.add(new Event(nazwaWydarzenia, terminWydarzenia.toLocalDate()));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
    public ObservableList<Event> selectDataBeforeDate(LocalDate endDate) {
        ObservableList<Event> data = FXCollections.observableArrayList();

        String query = "SELECT name, event_date FROM events WHERE event_date < ?";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(endDate));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nazwaWydarzenia = resultSet.getString("name");
                    Date terminWydarzenia = resultSet.getDate("event_date");

                    data.add(new Event(nazwaWydarzenia, terminWydarzenia.toLocalDate()));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
    public ObservableList<Event> selectDataAfterDate(LocalDate startDate) {
        ObservableList<Event> data = FXCollections.observableArrayList();

        String query = "SELECT name, event_date FROM events WHERE event_date > ?";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(startDate));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nazwaWydarzenia = resultSet.getString("name");
                    Date terminWydarzenia = resultSet.getDate("event_date");

                    data.add(new Event(nazwaWydarzenia, terminWydarzenia.toLocalDate()));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
    public void appendDataToJSONArray(String login, String eventName, String agenda, LocalDate termin, String participationType, String catering) {
        String query = "UPDATE Logowanie SET events = JSON_ARRAY_APPEND(events, '$', JSON_OBJECT('Nazwa wydarzenia', ?, 'Agenda', ?, 'Termin', ?, 'Typ uczestnictwa', ?, 'Wyżywienie', ?, 'Potwierdzone', false)) WHERE login = ?";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, eventName);
            statement.setString(2, agenda);
            statement.setDate(3, Date.valueOf(termin));
            statement.setString(4, participationType);
            statement.setString(5, catering);
            statement.setString(6, login);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Data appended to JSON array. Rows affected: " + rowsAffected);
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addEvent(String name, String agenda, LocalDate date) {
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String sql = "INSERT INTO events (name, agenda, event_date) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, agenda);
            statement.setString(3, String.valueOf(date));
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Event inserted successfully.");
            } else {
                System.out.println("Failed to insert event.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void confirmUser(String login, String event) {
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String selectSql = "SELECT events FROM Logowanie WHERE login = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setString(1, login);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    String eventsJson = resultSet.getString("events");

                    JSONArray eventsArray = new JSONArray(eventsJson);

                    for (int i = 0; i < eventsArray.length(); i++) {
                        JSONObject eventObject = eventsArray.getJSONObject(i);
                        String eventName = eventObject.getString("Nazwa wydarzenia");
                        if (eventName.equals(event)) {
                            eventObject.put("Potwierdzone", true);
                            break;
                        }
                    }

                    String updatedEventsJson = eventsArray.toString();

                    String updateSql = "UPDATE Logowanie SET events = ? WHERE login = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, updatedEventsJson);
                    updateStatement.setString(2, login);

                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("User approved.");
                    } else {
                        System.out.println("Failed to approve user.");
                    }
                } else {
                    System.out.println("User not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void unconfirmUser(String login, String event) {
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String selectSql = "SELECT events FROM Logowanie WHERE login = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setString(1, login);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    String eventsJson = resultSet.getString("events");

                    JSONArray eventsArray = new JSONArray(eventsJson);

                    for (int i = 0; i < eventsArray.length(); i++) {
                        JSONObject eventObject = eventsArray.getJSONObject(i);
                        String eventName = eventObject.getString("Nazwa wydarzenia");
                        if (eventName.equals(event)) {
                            eventObject.put("Potwierdzone", false);
                            break;
                        }
                    }

                    String updatedEventsJson = eventsArray.toString();

                    String updateSql = "UPDATE Logowanie SET events = ? WHERE login = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, updatedEventsJson);
                    updateStatement.setString(2, login);

                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("User disapproved.");
                    } else {
                        System.out.println("Failed to disapprove user.");
                    }
                } else {
                    System.out.println("User not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> getUsers() {
        ObservableList<String> data = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String sql = "SELECT login FROM Logowanie WHERE permissions = 0";
            PreparedStatement statement = connection.prepareStatement(sql);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String user = resultSet.getString("login");
                    data.add(user);
                }
            }
            System.out.println("Selected users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ObservableList<String> getEvents() {
        ObservableList<String> data = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String sql = "SELECT name FROM events";
            PreparedStatement statement = connection.prepareStatement(sql);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String user = resultSet.getString("name");
                    data.add(user);
                }
            }
            System.out.println("Selected events");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public String getAgenda(String name) {
        String data = "";
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String sql = "SELECT agenda FROM events WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    data = resultSet.getString("agenda");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public String getEventDate(String name) {
        String data = "";
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String sql = "SELECT event_date FROM events WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    data = resultSet.getString("event_date");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void editEvent(String oldName, String newName, String agenda, String date) {
        try (Connection connection = DriverManager.getConnection(url, username, "root")) {
            String sql = "UPDATE events SET name = ?, agenda = ?, event_date = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setString(2, agenda);
            statement.setString(3, date);
            statement.setString(4, oldName);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Event " + oldName +" has been changed to " + newName);
            } else {
                System.out.println("Could not change event");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

