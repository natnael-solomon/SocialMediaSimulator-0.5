package socialmedia;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reply implements Postable {
    private String content;
    private String author;
    private User parentUser;
    private Comment parentComment;
    private final LocalDateTime timestamp;

    public Reply(String content, Comment parentComment, User parentUser) {
        this.content = content;
        this.author = parentUser.getUsername();
        this.parentComment = parentComment;
        this.timestamp = LocalDateTime.now();
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public User getParentUser() {
        return parentUser;
    }

    public void setParentUser(User parentUser) {
        this.parentUser = parentUser;
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
    public String getContent() {
        return content != null ? content : "[Reply Removed]";
    }

    @Override
    public void removeContent() {
        this.content = null;
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

    @Override
    public String getElapsedTime() {
        Duration duration = Duration.between(timestamp, LocalDateTime.now());
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;

        if (days > 0) return days + " day" + (days > 1 ? "s" : "") + " ago";
        if (hours > 0) return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
        if (minutes > 0) return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago";
        return "just now";
    }
}