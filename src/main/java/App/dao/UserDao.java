package App.dao;

import App.model.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUser (int id);
    void save(User user);
    void update(int id, User user);
    void delete(int id);
}
