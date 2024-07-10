package ex03;

import ex02.User;

public interface UsersList {
    void addUser(ex02.User user);
    ex02.User getUserById(int id);
    User getUserByIndex(int index);
    int getCountOfUsers();
}
