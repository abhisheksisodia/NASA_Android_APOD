package com.abhi.nasaapodfetcher.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by abhisheksisodia on 2017-08-08.
 */

public class AstroPicture {

    private Date date;

    private String explanation;

    private String hdUrl;

    @SerializedName("media_type")
    private String mediaType;

    @SerializedName("service_version")
    private String serviceVersion;

    private String title;

    private String url;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
