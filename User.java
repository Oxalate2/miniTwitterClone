import java.util.ArrayList;
import java.util.Stack;
import java.time.LocalDateTime;

public class User {
    private String userID;
    private ArrayList<String> followers;
    private ArrayList<String> followings;
    private TweetManager newsFeed;
    private LocalDateTime timestamp;
    private long lastUpdateTime;

    public User(String userID) {
        this.userID = userID;
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.timestamp = LocalDateTime.now();
    }

    public String getUserID() {
        return userID;
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public ArrayList<String> getFollowings() {
        return followings;
    }

    public boolean isGroup() {
        return false;
    }

    public Stack<String> getNewsFeed() {
        return newsFeed.getTweets();
    }

    public void addFollower(String followerID) {
        followers.add(followerID);
    }

    public void addFollowing(String followingID) {

    }

    public void postTweet(String tweet) {
        // newsFeed.add(tweet);
        updateFollowers(tweet);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void changeUpdateTime(long timeStamp) {
        lastUpdateTime = timeStamp;
    }

    public long getUpdateTime() {
        return lastUpdateTime;
    }

    private void updateFollowers(String tweet) {
        // Notify followers about new tweet
        // Use observer pattern to implement
    }
}
