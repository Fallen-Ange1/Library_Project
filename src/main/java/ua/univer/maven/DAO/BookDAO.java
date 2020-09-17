package ua.univer.maven.DAO;

import org.w3c.dom.ls.LSOutput;
import ua.univer.maven.Model.Book;
import ua.univer.maven.Model.IbookDAO;
import ua.univer.maven.Model.TakenBook;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class BookDAO implements IbookDAO {
    int id = 0;

    class BookDAOException extends Exception {
    }

    public BookDAO() throws Exception {
        try (Connection con = getConnection()) {
            DatabaseMetaData metadb = con.getMetaData();
            System.out.println("Connect to " + metadb.getDatabaseProductName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BookDAOException();
        }
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/librarydb?useSSL=false&createDatabaseIfNotExist=true", "librarydb", "ei7veeCh_u4bo");
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "select * from books;";
        try (Connection con = getConnection()) {
            ResultSet result = con.createStatement().executeQuery(sql);
            while (result.next()) {
                Book p = new Book(
                        result.getString("name"),
                        result.getInt("id"),
                        result.getInt("number_of_books"),
                        result.getInt("author_id"));
                list.add(p);
            }
        } catch (Exception e) {
            Logger log = Logger.getLogger(getClass().getName());
            try {
                log.addHandler(new FileHandler("Log.txt"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            log.severe(e.getMessage());
        }

        return list;

    }

    @Override
    public List<Integer> getBooksIds() {
        List<Integer> booksIds = new ArrayList<Integer>();
        try {
            Connection con = getConnection();
            ResultSet rs = con.createStatement().executeQuery("select id from books");
            while (rs.next()) {
                booksIds.add(rs.getInt("id"));
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksIds;
    }

    @Override
    public List<Book> getBooksById(int id) {
        List<Book> books = new ArrayList<Book>();
        try {
            Connection con = getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT name,id,number_of_books,author_id " + "FROM books " + "WHERE id =?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Book book = null;
            while (resultSet.next()) {
                Book p = new Book(
                        resultSet.getString("name"),
                        id,
                        resultSet.getInt("author_id"),
                        resultSet.getInt("number_of_books"));
                books.add(p);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getBooksByAuthorID(int author_id) {
        List<Book> books = new ArrayList<Book>();
        try {
            Connection con = getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * " + "FROM books " + "WHERE author_id =?");
            preparedStatement.setInt(1, author_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Book book = null;
            while (resultSet.next()) {
                Book p = new Book(
                        resultSet.getString("name"),
                        resultSet.getInt("id"),
                        author_id,
                        resultSet.getInt("number_of_books"));
                books.add(p);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getBooksByName(String name) {
        List<Book> books = new ArrayList<Book>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " + "FROM books " + "WHERE name =?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            Book book = null;
            while (resultSet.next()) {
                Book p = new Book(
                        name,
                        resultSet.getInt("id"),
                        resultSet.getInt("author_id"),
                        resultSet.getInt("number_of_books")
                );
                books.add(p);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error in get books by name");
            e.printStackTrace();
        }
        return books;
    }

    public List<Integer> getIDsOfTakenBooks() {
        List<Integer> ids = new ArrayList<Integer>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT book_id FROM taken_book;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt("book_id"));
            }
            connection.close();

        } catch (Exception e) {
            System.out.println("Error with geting IDs of taken books");
            e.printStackTrace();
        }
        return ids;
    }

    @Override
    public List<TakenBook> showTakenBooks(List<Integer> ids) {
        List<TakenBook> takenBooks = new ArrayList<TakenBook>();
        int id;
        for (Integer current : ids) {
            id = current;

            try {
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM taken_book WHERE book_id = ?;");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    takenBooks.add(new TakenBook(resultSet.getInt("user_id"), resultSet.getInt("transaction_id"), resultSet.getString("Date"), resultSet.getInt("book_id")));
                }
                connection.close();
            } catch (Exception e) {
                System.out.println("Error Show taken_books");
                e.printStackTrace();
            }
        }
        return takenBooks;
    }

    public int getMaxBookId() {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT MAX(id) FROM books;");
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error with geting max book_id occurred");
        }
        return id + 1;
    }

    @Override
    public void addBook(Book book) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books " + "(name,id,number_of_books,author_id) " + "VALUES (?,?,?,?);");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getId());
            preparedStatement.setInt(3, book.getAuthor_id());
            preparedStatement.setInt(4, book.getNumber_of_books());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error with adding new book in library");
            e.printStackTrace();
        }

    }

    @Override
    public void setBook(Book book) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE books " + "SET name=?,number_of_books=?,author_id=? " + "WHERE id = ?;");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(4, book.getId());
            preparedStatement.setInt(3, book.getAuthor_id());
            preparedStatement.setInt(2, book.getNumber_of_books());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error in seting book");
            e.printStackTrace();
        }
    }

    @Override
    public void removeBook(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books " + "WHERE id=?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error in removing book");
            e.printStackTrace();
        }
    }

    @Override
    public void takeBook(int id, int user_id, int transaction_id) {
        try {
            Connection connection = getConnection();
            int amount = 0;
            PreparedStatement preparedStatementAm = connection.prepareStatement("SELECT number_of_books FROM books WHERE id =?");
            preparedStatementAm.setInt(1, id);
            ResultSet resultSetAm = preparedStatementAm.executeQuery();
            while (resultSetAm.next()) {
                amount = resultSetAm.getInt("number_of_books");
            }
            if (amount > 0) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO taken_book (user_id,transaction_id,Date,book_id) VALUES (?,?,CURRENT_TIMESTAMP,?);");
                preparedStatement.setInt(1, user_id);
                preparedStatement.setInt(2, transaction_id);
                preparedStatement.setInt(3, id);
                PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE books SET number_of_books = number_of_books-1 WHERE id = ?;");
                preparedStatement2.setInt(1, id);
                preparedStatement.executeUpdate();
                preparedStatement2.executeUpdate();
                connection.close();
            } else {
                throw new BookDAOException();
            }
        } catch (Exception e) {
            System.out.println("Error with taking book");
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAvailableBooks() {
        List<Book> books = new ArrayList<Book>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE number_of_books>0");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                books.add(new Book(resultSet.getString("name"),
                        resultSet.getInt("id"),
                        resultSet.getInt("author_id"),
                        resultSet.getInt("number_of_books")));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error show available books");
            e.printStackTrace();
        }
        return books;
    }
}