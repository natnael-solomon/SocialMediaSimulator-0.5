package socialmedia;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Comment implements Postable {
    private String content;
    private String author;
    private Post parentPost;
    private User parentUser;

    private final List<Reply> replies;
    private final LocalDateTime timestamp;

    public Comment(String content, Post parentPost, User parentUser) {
        this.content = content;
        this.author = parentUser.getUsername();
        this.timestamp = LocalDateTime.now();
        this.parentPost = parentPost;
        this.parentUser= parentUser;
        this.replies = new ArrayList<>();
        this.parentPost.addComment(this);
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
        return content != null ? content : "[Comment Removed]";
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
        return timestamp.format(formatter); // Output like: "2025-01-01 14:30:00"
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

    // Reply methods
    public void addReply(Reply reply) {
        replies.add(reply);
    }

    public List<Reply> getReplies() {
        return new ArrayList<>(replies);
    }

    public boolean hasReplies() {
        return !replies.isEmpty();
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }
}
