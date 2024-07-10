package ex02;

public class UserIdsGenerator {
    private static UserIdsGenerator INSTANCE;
    private int id = -1;
    private UserIdsGenerator() {}

    public int generateId() {
        id += 1;
        return id;
    }
    public static UserIdsGenerator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserIdsGenerator();
        }
        return INSTANCE;
    }

    public static int getId() {
        return getId();
    }


}
