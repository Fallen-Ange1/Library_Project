package ua.univer.maven.CheckStaff;


import ua.univer.maven.Model.User;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class Check {
    List<Integer> list = new ArrayList<Integer>();
    List<User> users = new ArrayList<User>();
    List<String> stringList = new ArrayList<String>();
    int years;
    int mouth;
    int days;
    int sum;
    int curyears;
    int curmounth;
    int curdays;
    int cursum;
    int diff;
    String date = "";
    String curdate = "";
    List<String> HCHDates = new ArrayList<String>();
    List<Integer> idsList = new ArrayList<Integer>();
    List<User> userList = new ArrayList<User>();

    class CheckDAOException extends Exception {
    }

    public Check() throws Exception {
        try (Connection con = getConnection()) {
            DatabaseMetaData metadb = con.getMetaData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Check.CheckDAOException();
        }
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/librarydb?useSSL=false&createDatabaseIfNotExist=true", "librarydb", "ei7veeCh_u4bo");
    }

    public List<User> GetIdsHCH() {

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Date FROM taken_book;");
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT CURRENT_TIMESTAMP;");
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                stringList.add(resultSet.getString("Date"));
            }
            while (resultSet1.next()) {
                curdate = resultSet1.getString("CURRENT_TIMESTAMP");
            }
            String[] CurSubStr;
            String[] SubCurSubStr;
            CurSubStr = curdate.split(" ");
            SubCurSubStr = CurSubStr[0].split("-");
            curyears = Integer.parseInt(String.valueOf(SubCurSubStr[0])) * 365;
            curmounth = Integer.parseInt(String.valueOf(SubCurSubStr[1])) * 30;
            curdays = Integer.parseInt(String.valueOf(SubCurSubStr[2]));
            cursum = curyears + curmounth + curdays;
            String[] substr;
            String[] SubSubstr;
            if (!stringList.isEmpty()) {
                for (String list : stringList) {
                    substr = list.split(" ");
                    SubSubstr = substr[0].split("-");
                    years = Integer.parseInt(String.valueOf(SubSubstr[0])) * 365;
                    mouth = Integer.parseInt(String.valueOf(SubSubstr[1])) * 30;
                    days = Integer.parseInt(String.valueOf(SubSubstr[2]));
                    sum = years + mouth + days;

                    if (cursum - sum > 29) {
                        HCHDates.add(list);
                    }
                }
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT user_id FROM taken_book WHERE Date = ?;");
            for (String current : HCHDates) {
                preparedStatement2.setString(1, current);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if (resultSet2.next()) {
                    idsList.add(resultSet2.getInt("user_id"));
                }
            }
            PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?;");
            for (Integer current : idsList) {
                preparedStatement3.setInt(1, current);
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                if (resultSet3.next()) {
                    userList.add(new User(
                            resultSet3.getInt("user_id"),
                            resultSet3.getString("user_email"),
                            resultSet3.getString("password"),
                            resultSet3.getString("phone_number"),
                            resultSet3.getString("userabil")
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Error with geting id of the user HCH");
            e.printStackTrace();
        }
        return userList;
    }

    public int LengthOfHoldingCheck(int id, int book_id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Date FROM taken_book WHERE user_id = ? AND book_id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, book_id);
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT CURRENT_TIMESTAMP;");
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                date = resultSet.getString("Date");
            }
            while (resultSet1.next()) {
                curdate = resultSet1.getString("CURRENT_TIMESTAMP");
            }
            String[] CurSubStr;
            String[] SubCurSubStr;
            CurSubStr = curdate.split(" ");
            SubCurSubStr = CurSubStr[0].split("-");
            curyears = Integer.parseInt(String.valueOf(SubCurSubStr[0])) * 365;
            curmounth = Integer.parseInt(String.valueOf(SubCurSubStr[1])) * 30;
            curdays = Integer.parseInt(String.valueOf(SubCurSubStr[2]));
            cursum = curyears + curmounth + curdays;
            String[] substr;
            String[] SubSubstr;

            substr = date.split(" ");
            SubSubstr = substr[0].split("-");
            years = Integer.parseInt(String.valueOf(SubSubstr[0])) * 365;
            mouth = Integer.parseInt(String.valueOf(SubSubstr[1])) * 30;
            days = Integer.parseInt(String.valueOf(SubSubstr[2]));
            sum = years + mouth + days;

            diff = cursum - sum;
        } catch (Exception e) {
            System.out.println("Length holding check error");
            e.printStackTrace();
        }
        return diff;
    }
}
