package services;

import dao.UserHibernateDAO;
import util.DBHelper;
import model.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserService {
    private static UserService userService;

    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getInstance().getSessionFactory());
        }
        return userService;
    }
    public List<User> getAllUsers() {
        return new UserHibernateDAO(sessionFactory.openSession()).getAllUsers();
    }

    public User getUserById(long id)  {
        return new UserHibernateDAO(sessionFactory.openSession()).getUserByID(id);

    }
    public void addUser (User user) {
        if (!isExistUser(user.getName())) {
            new UserHibernateDAO(sessionFactory.openSession()).addUser(user);
        }

    }
    public void update(User user, String name, String password, String email) {
        new UserHibernateDAO(sessionFactory.openSession()).updateUser(user, name, password, email);
    }
    public void deleteUser(User user) {
        new UserHibernateDAO(sessionFactory.openSession()).deleteUser(user);
    }
    public boolean isExistUser(String name) {
        return new UserHibernateDAO(sessionFactory.openSession()).isExistUser(name);
    }


}
