package com.example.telcoapp.model;

import com.google.gson.annotations.SerializedName;

public class EventData {
    @SerializedName("event_name")
    private String eventName;

    @SerializedName("event_time")
    private String eventTime;

    @SerializedName("product")
    private String product;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("description")
    private String description;

    public EventData(String eventName, String eventTime, String product, String deviceId, String description) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.product = product;
        this.deviceId = deviceId;
        this.description = description;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
