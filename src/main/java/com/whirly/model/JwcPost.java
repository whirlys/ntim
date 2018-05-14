package com.whirly.model;

import java.io.Serializable;
import java.util.Date;

public class JwcPost implements Serializable {
    private Integer jwcpostId;

    private String title;

    private String url;

    private Date createtime;

    private String remarks;

    private Date crawltime;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getJwcpostId() {
        return jwcpostId;
    }

    public void setJwcpostId(Integer jwcpostId) {
        this.jwcpostId = jwcpostId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getCrawltime() {
        return crawltime;
    }

    public void setCrawltime(Date crawltime) {
        this.crawltime = crawltime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}