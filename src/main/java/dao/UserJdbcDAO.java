package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO<User> {
    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        User user;
        try (PreparedStatement pr = connection.prepareStatement("select * from users")) {
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setPass(rs.getString(3));
                user.setEmail(rs.getString(4));
                list.add(user);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return list;
    }

    @Override
    public boolean isExistUser(String name) {
        return getAllUsers()
                .stream()
                .map(User::getName)
                .anyMatch(x -> x.equals(name));
    }

    @Override
    public void addUser(User user) {
        if (!isExistUser(user.getName())) {
            try(PreparedStatement pr = connection.prepareStatement("insert into users (name, password, email) values (?,?,?)")) {
                pr.setString(1, user.getName());
                pr.setString(2,user.getPass());
                pr.setString(3,user.getEmail());
                pr.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Users is exist");
        }
    }

    @Override
    public void updateUser(User user, String name, String password, String email) {
        if (isExistUser(user.getName())) {
            try (PreparedStatement pr = connection.prepareStatement("update users set name = ?,password = ?, email = ? where id = ?")) {
                pr.setString(1,name);
                pr.setString(2,password);
                pr.setString(3,email);
                pr.setLong(4, user.getId());
                pr.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User don't exist!");
        }
    }

    @Override
    public void deleteUser(User user) {
        if (isExistUser(user.getName())) {
            try (PreparedStatement pr = connection.prepareStatement("delete from users where id = ?")) {
                pr.setLong(1, user.getId());
                pr.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else System.out.println("user don't delete, because user don't exist");
    }

    @Override
    public User getUserByID(long id) {
        User user = new User();
        try (PreparedStatement pr = connection.prepareStatement("select * from users where id=?")) {


            pr.setLong(1, id);
            ResultSet resultSet = pr.executeQuery();
            resultSet.next();
            user.setId(resultSet.getLong(1));
            user.setName(resultSet.getString(2));
            user.setPass(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));



        } catch (SQLException e) {
            e.printStackTrace();
        } return user;
    }


}
