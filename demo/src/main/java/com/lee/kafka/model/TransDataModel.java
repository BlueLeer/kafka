package com.lee.kafka.model;

/**
 * @author lee
 * @date 2020/2/23 20:34
 */
public class TransDataModel {
    public String title;
    public String content;
    public String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TransDataModel [title=" + title + ", content=" + content + ", type=" + type + "]";
    }

}
