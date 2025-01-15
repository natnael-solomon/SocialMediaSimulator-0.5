package socialmedia;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Post implements Postable {

    private String content;
    private String author;
    private int numberOfLikes;
    private User parentUser;
    private final LocalDateTime timestamp;
    private final List<Comment> comments;

    // Constructor
    public Post(String content, User user) {
        this.content = content;
        this.author = user.getUsername();
        this.comments = new ArrayList<>();
        this.timestamp = LocalDateTime.now();  //timestamp to the current time
        this.parentUser = user;
    }

    @Override
    public String getElapsedTime() {
        Duration duration = Duration.between(timestamp, LocalDateTime.now());

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        if (days > 0) {
            return days + " day" + (days > 1 ? "s" : "") + " ago"; // e.g., "2 days ago"
        } else if (hours > 0) {
            return hours + " hour" + (hours > 1 ? "s" : "") + " ago"; // e.g., "3 hours ago"
        } else if (minutes > 0) {
            return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago"; // e.g., "10 minutes ago"
        } else if (seconds > 0) {
            return seconds + " second" + (seconds > 1 ? "s" : "") + " ago"; // e.g., "30 seconds ago"
        } else {
            return "Just now";
        }
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    void addComment(Comment comment) {
        comments.addFirst(comment);
    }

    int getNumberOfLikes(){
        return numberOfLikes;
    }

    int getNumberOfComments(){
        return comments.size();
    }

    User getParentUser(){
        return parentUser;
    }


    @Override
    public String getContent() {
        return content != null ? content : "[Content Removed]";
    }

    @Override
    public void removeContent() {
        this.content = null;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }

}
