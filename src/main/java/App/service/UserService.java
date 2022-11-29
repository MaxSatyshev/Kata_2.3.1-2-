package App.service;

import App.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser (int id);
    void save(User user);
    public void update(int id, User user);
    void delete(int id);
}
