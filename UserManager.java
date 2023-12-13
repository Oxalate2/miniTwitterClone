import java.util.ArrayList;

public class UserManager {
    private static final UserManager instance = new UserManager();
    private static ArrayList<User> users;
    private static ArrayList<String> userNames;

    public UserManager() {
        users = new ArrayList<>();
        userNames = new ArrayList<>();
    }

    public UserManager(User newUser) {
        users = new ArrayList<>();
        userNames = new ArrayList<>();
        addUser(newUser);
    }

    public static UserManager getInstance() {
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<String> getUserNames() {
        return userNames;
    }

    public void addUser(User user) {
        users.add(user);
        userNames.add(user.getUserID());
    }

    public int getUsertotal() {
        return users.size();

    }

    public static boolean ifExists(String input) {
        return userNames.contains(input);
        
    }

}
