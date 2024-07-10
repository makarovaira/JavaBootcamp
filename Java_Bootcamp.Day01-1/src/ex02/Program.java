package ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList newUserList = new UsersArrayList();

        User user1 = new User("Mike", 124);
        User user2 = new User("Ann", 765);
        User user3 = new User("Kate", 234);
        User user4 = new User("Bill", 723);
        User user5 = new User("Tom", 908);
        User user6 = new User("Jake", 265);
        User user7 = new User("Rob", 373);
        User user8 = new User("Tom", 908);
        User user9 = new User("Jake", 265);
        User user10 = new User("Rob", 373);
        User user11 = new User("Tom", 908);
        User user12 = new User("Jake", 265);

        newUserList.addUser(user1);
        newUserList.addUser(user2);
        newUserList.addUser(user3);
        newUserList.addUser(user4);
        newUserList.addUser(user5);
        newUserList.addUser(user6);
        newUserList.addUser(user7);
        newUserList.addUser(user8);
        newUserList.addUser(user9);
        newUserList.addUser(user10);
        newUserList.addUser(user11);
        newUserList.addUser(user12);

        newUserList.returnArrayInfo();
    }
}
