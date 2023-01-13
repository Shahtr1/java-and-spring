package com.example.dto;

import java.time.LocalDateTime;

public class MessageDto {
    private String content;
    private Long makeVisibleAt;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String status;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMakeVisibleAt() {
        return makeVisibleAt;
    }

    public void setMakeVisibleAt(Long makeVisibleAt) {
        this.makeVisibleAt = makeVisibleAt;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
