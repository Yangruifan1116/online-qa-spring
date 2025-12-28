package org.example.online_qa_web_advance1.model;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {
    private int id;
    private int topicId;
    private String content;
    private String author;
    private Date createTime;

    public Reply() {
        this.createTime = new Date();
    }

    public Reply(int id, int topicId, String content, String author) {
        this();
        this.id = id;
        this.topicId = topicId;
        this.content = content;
        this.author = author;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTopicId() { return topicId; }
    public void setTopicId(int topicId) { this.topicId = topicId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}