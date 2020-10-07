package pl.karczmarczyk;

import java.time.LocalDateTime;

/**
 *
 * @author mateusz
 */
public class Message {
    private String from;
    private String fromUserName;
    private String to;
    private String toUserName;
    private String content;
    private Boolean isRoom;
    private LocalDateTime dateCreated;
    private Boolean isTechnicalMessage = false;

    public Message() {
    }

    public String getFrom() {
	return from;
    }

    public void setFrom(String from) {
	this.from = from;
    }

    public String getTo() {
	return to;
    }

    public void setTo(String to) {
	this.to = to;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public String getFromUserName() {
	return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
	this.fromUserName = fromUserName;
    }

    public String getToUserName() {
	return toUserName;
    }

    public void setToUserName(String toUserName) {
	this.toUserName = toUserName;
    }

    public Boolean getIsRoom() {
	return isRoom;
    }

    public void setIsRoom(Boolean isRoom) {
	this.isRoom = isRoom;
    }

    public LocalDateTime getDateCreated() {
	return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
	this.dateCreated = dateCreated;
    }

    public Boolean getIsTechnicalMessage() {
	return isTechnicalMessage;
    }

    public void setIsTechnicalMessage(Boolean isTechnicalMessage) {
	this.isTechnicalMessage = isTechnicalMessage;
    }

    @Override
    public String toString() {
	return "Message{" + "from=" + from + ", fromUserName=" + fromUserName + ", to=" + to + ", toUserName=" + toUserName + ", content=" + content + ", isRoom=" + isRoom + ", dateCreated=" + dateCreated + ", isTechnicalMessage=" + isTechnicalMessage + '}';
    }
    
    
}
