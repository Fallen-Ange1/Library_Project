package ua.univer.maven.Model;

;

import java.util.List;

public interface IbookDAO {
    List<Book> getAllBooks();

    List<Integer> getBooksIds();

    List<Book> getBooksById(int id);

    List<Book> getBooksByAuthorID(int author_id);

    List<Book> getBooksByName(String name);

    List<Integer> getIDsOfTakenBooks();

    List<TakenBook> showTakenBooks(List<Integer> ids);

    public int getMaxBookId();

    void addBook(Book p);

    void setBook(Book p);

    void removeBook(int id);

    void takeBook(int id, int user_id, int transaction_id);

    List<Book> getAvailableBooks();
}