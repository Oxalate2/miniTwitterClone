import java.time.LocalDateTime;

public class Tweet {
    private String content;
    private LocalDateTime timestamp;

    public Tweet(String content) {
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "]: " + content;
    }
}