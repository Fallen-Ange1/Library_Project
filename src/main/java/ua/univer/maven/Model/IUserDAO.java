package ua.univer.maven.Model;

import java.util.List;

public interface IUserDAO {
    List<User> getAllUsers();

    List<User> getUserByTransId(int id);

    List<User> getUserByBook(String name);

    int getUserIdByUserL(String user_l);

    int getTransactionId();
}
