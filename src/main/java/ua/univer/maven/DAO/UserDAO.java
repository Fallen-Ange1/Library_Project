package ua.univer.maven.DAO;

import ua.univer.maven.Model.IUserDAO;
import ua.univer.maven.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    int us_id;
    int trans_id;

    class UserDAOException extends Exception {
    }

    public UserDAO() throws Exception {
        try (Connection con = getConnection()) {
            DatabaseMetaData metadb = con.getMetaData();
            System.out.println("Connect to " + metadb.getDatabaseProductName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserDAO.UserDAOException();
        }
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/librarydb?useSSL=false&createDatabaseIfNotExist=true", "librarydb", "ei7veeCh_u4bo");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("user_email"),
                        resultSet.getString("password"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("userabil"));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error with get all users occurred");
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getUserByTransId(int id) {
        List<User> userList = new ArrayList<User>();
        int userId = 0;
        try {
            Connection con = getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT user_id FROM taken_book WHERE transaction_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }
            con.close();
            Connection con2 = getConnection();
            PreparedStatement preparedStatement2 = con2.prepareStatement("SELECT * FROM users WHERE user_id =?");
            preparedStatement2.setInt(1, userId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                User user = new User(
                        userId,
                        resultSet2.getString("user_email"),
                        resultSet2.getString("password"),
                        resultSet2.getString("phone_number"),
                        resultSet2.getString("userabil")
                );
                userList.add(user);
            }
            con2.close();
        } catch (Exception e) {
            System.out.println("Error with geting user by trans_id");
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> getUserByBook(String name) {
        List<User> userList = new ArrayList<User>();
        int userId = 0;
        try {
            Connection con = getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT user_id FROM taken_book WHERE book_id = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }
            con.close();
            Connection con2 = getConnection();
            PreparedStatement preparedStatement2 = con2.prepareStatement("SELECT * FROM users WHERE user_id =?");
            preparedStatement2.setInt(1, userId);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                User user = new User(
                        userId,
                        resultSet2.getString("user_email"),
                        resultSet2.getString("password"),
                        resultSet2.getString("phone_number"),
                        resultSet2.getString("userabil")
                );
                userList.add(user);
            }
            con2.close();
        } catch (Exception e) {
            System.out.println("Error with geting user by book");
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int getUserIdByUserL(String user_l) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_id FROM users WHERE user_email =?");
            preparedStatement.setString(1, user_l);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                us_id = resultSet.getInt("user_id");
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error with geting user id by email");
            e.printStackTrace();
        }
        return us_id;
    }

    @Override
    public int getTransactionId() {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(transaction_id) FROM taken_book;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trans_id = resultSet.getInt(1);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error with geting trans id by email");
            e.printStackTrace();
        }
        return trans_id + 1;
    }
}
