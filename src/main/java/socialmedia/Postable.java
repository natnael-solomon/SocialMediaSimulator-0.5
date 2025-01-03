package socialmedia;

import java.time.LocalDateTime;


public interface Postable {

    void setContent(String content);
    String getContent();
    void removeContent();

    void setAuthor(String author);
    String getAuthor();

    LocalDateTime getTimestamp();
    String getFormattedTimestamp();
    String getElapsedTime();
}
