package dao;

import java.util.List;

public interface UserDAO<T>{
    public List<T> getAllUsers();
    public void addUser(T t);
    public void updateUser(T t, String name, String password, String email);
    public void deleteUser(T t);
    public T getUserByID(long id);
    public boolean isExistUser(String name);

}
