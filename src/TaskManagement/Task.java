package TaskManagement;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private String title;
    private String description;
    private String priority;
    private Date dueDate;
    private String status;

    public Task(String title, String description, String priority, Date dueDate, String status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task [Title: " + title + ", Priority: " + priority + ", Due Date: " + dueDate + ", Status: " + status + "]";
    }
}
