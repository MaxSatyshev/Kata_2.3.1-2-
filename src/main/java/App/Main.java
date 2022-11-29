package App;

import App.config.DBConfig;
import App.dao.UserDao;
import App.dao.UserDaoImp;
import App.model.User;
import App.service.UserService;
import App.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DBConfig.class);

        UserDaoImp test = applicationContext.getBean(UserDaoImp.class);
        test.save(new User("mama", "sita", 14, 1234567));
    }
}
