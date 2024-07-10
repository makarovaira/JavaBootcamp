package ex03;

import ex02.User;
import ex02.UserNotFoundException;
import ex02.UsersList;

public class UsersArrayList implements UsersList {
    private int arraySize = 10;
    private ex02.User[] usersArray = new ex02.User[arraySize];
    private int count = 0;

    @Override
    public void addUser(ex02.User userToAdd) {
        if (this.count == arraySize) {
            ex02.User[] newUsersArray = new ex02.User[usersArray.length * 2];
            System.arraycopy(usersArray, 0, newUsersArray, 0, usersArray.length);
            usersArray = newUsersArray;
        }
        usersArray[count++] = userToAdd;
    }

    @Override
    public ex02.User getUserById(int id) {
        for (int i = 0; i < this.count; i++) {
            if(usersArray[i].getId() == id) {
                return usersArray[i];
            }
        }
        throw new ex02.UserNotFoundException("User not found by id = " + id);
    }

    @Override
    public User getUserByIndex(int index) {
        if (index >= 0 && index < this.count) {
            return usersArray[index];
        }
        throw new UserNotFoundException("User not found by index = " + index);
    }

    @Override
    public int getCountOfUsers() {
        return this.count;
    }

    public int getArraySize() {return arraySize;}


    public void returnArrayInfo() {
        for (int i = 0; i < this.count; i++) {
            System.out.println("id: " + usersArray[i].getId());
            System.out.println("Name: " + usersArray[i].getName());
            System.out.println("Balance: " + usersArray[i].getBalance());
        }
        System.out.println();
        System.out.println("Amount of users: " + getCountOfUsers());
        System.out.println("Array length: " + usersArray.length);
    }
}
