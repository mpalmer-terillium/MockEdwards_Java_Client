package com.mockedwards.dto;

import com.mockedwards.integration.IntegrationType;


public class InternalRequest {
    
    public enum InternalIntegrationResponseStatus { OK, ERROR }
    
    private InternalIntegrationResponseStatus status;
    private IntegrationType type;
    private String firstvalue;
    private String secondvalue;
    
    public InternalRequest(IntegrationType type, InternalIntegrationResponseStatus status) {
        this.type = type;
        this.status = status;
    }
    
    public InternalRequest(InternalIntegrationResponseStatus status, IntegrationType type, String valueone, String valuetwo) {
        this(type, status);
        this.firstvalue = valueone;
        this.secondvalue = valuetwo;
    }

    public InternalIntegrationResponseStatus getStatus() {
        return status;
    }

    public void setStatus(InternalIntegrationResponseStatus status) {
        this.status = status;
    }

    public IntegrationType getType() {
        return type;
    }

    public void setType(IntegrationType type) {
        this.type = type;
    }

    public String getFirstvalue() {
        return firstvalue;
    }

    public void setFirstvalue(String firstvalue) {
        this.firstvalue = firstvalue;
    }

    public String getSecondvalue() {
        return secondvalue;
    }

    public void setSecondvalue(String secondvalue) {
        this.secondvalue = secondvalue;
    }
}
