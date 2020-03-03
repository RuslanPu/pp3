package dao;

import model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.text.html.parser.Entity;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

public class UserHibernateDAO implements UserDAO<User> {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }
    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {



                Transaction tx = session.beginTransaction();
                session.save(user);
                tx.commit();
                session.close();


    }



    @Override
    public void updateUser(User user, String name, String password, String email) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET name = :nameUser, email = :emailUser, password = :passUser WHERE id = :userId");
        query.setParameter("nameUser", name);
        query.setParameter("emailUser", email);
        query.setParameter("passUser", password);
        query.setParameter("userId", user.getId());
        query.executeUpdate();
        transaction.commit();
//set all resume values

    }

    @Override
    public void deleteUser(User user) {
        Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
        session.close();
    }


    @Override
    public User getUserByID(long id) {
        Query query = session.createQuery("FROM User WHERE id = :idUser");
        query.setParameter("idUser", id);
        List<User> listUser = query.list();
        User user = new User(listUser.get(0).getId(),listUser.get(0).getName(),listUser.get(0).getPass(),listUser.get(0).getEmail());
        return user;
    }

    @Override
    public boolean isExistUser(String name) {
        return getAllUsers()
                .stream()
                .map(User::getName)
                .anyMatch(x -> x.equals(name));
    }
}
