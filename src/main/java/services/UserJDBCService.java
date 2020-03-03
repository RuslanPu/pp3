package services;

import dao.UserJdbcDAO;
import model.User;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserJDBCService {
    private static UserJDBCService INSTANCE;
    private UserJDBCService() {};
    public static UserJDBCService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserJDBCService();
        }
        return INSTANCE;
    }

    private UserJdbcDAO dao = getUserJdbcDAO();
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public User getUserById(long id)  {
        return dao.getUserByID(id);
    }
    public void addUser (User user) {
        dao.addUser(user);
    }
    public void update(User user, String name, String password, String email) {
        dao.updateUser(user, name, password, email);
    }
    public void deleteUser(User user) {
        dao.deleteUser(user);
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root").       //password
                    append("&autoReconnect=true&useSSL=false&serverTimezone=UTC");       //SSL

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserJdbcDAO getUserJdbcDAO() {
        return new UserJdbcDAO(getMysqlConnection());
    }
}
