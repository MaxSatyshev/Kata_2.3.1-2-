package App.dao;

import App.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImp implements UserDao{
    private static int USERS_COUNT = 3; //КОЛИЧЕСТВО ЮЗЕРОВ В ТАБЛИЦЕ, ИЗНАЧАЛЬНО 3
    @PersistenceContext
    private EntityManager em;

    public UserDaoImp() {
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User").getResultList();
    }

    @Override
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) {
        user.setId(++USERS_COUNT);
        em.persist(user);
    }

    @Override
    public void update(int id, User user) {
        User userForUpdate = getUser(id);
        userForUpdate.setName(user.getName());
        userForUpdate.setSurname(user.getSurname());
        userForUpdate.setAge(user.getAge());
        userForUpdate.setPhoneNumber(user.getPhoneNumber());
    }

    @Override
    public void delete(int id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }
}
