package com.example.telcoapp.model;

import com.google.gson.annotations.SerializedName;

public class EventRequest {
    @SerializedName("event_tracking_id")
    private String eventTrackingId;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("msisdn")
    private String msisdn;

    @SerializedName("event")
    private EventData event;

    public EventRequest(String eventTrackingId, String deviceId, String msisdn, EventData event) {
        this.eventTrackingId = eventTrackingId;
        this.deviceId = deviceId;
        this.msisdn = msisdn;
        this.event = event;
    }

    public String getEventTrackingId() {
        return eventTrackingId;
    }

    public void setEventTrackingId(String eventTrackingId) {
        this.eventTrackingId = eventTrackingId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public EventData getEvent() {
        return event;
    }

    public void setEvent(EventData event) {
        this.event = event;
    }
}
