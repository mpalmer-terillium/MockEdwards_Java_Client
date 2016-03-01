package com.mockedwards.dto;

import com.mockedwards.integration.IntegrationType;


public class MockIntegrationRequest {
    
    private IntegrationType integrationType;
    private String amount;
    private String transactionType;
    private String description;
    private String processingOption;
    private String defaultValueOne;
    private String defaultValueTwo;

    public MockIntegrationRequest(IntegrationType type, String firstvalue, String secondvalue) {
        this.integrationType = type;
        
        if(this.integrationType == IntegrationType.PayTrace) {
            this.amount = firstvalue;
            this.transactionType = secondvalue;
        } else if(this.integrationType == IntegrationType.SalesCloud) {
            this.description = firstvalue;
            this.processingOption = secondvalue;
        } else {
            this.defaultValueOne = firstvalue;
            this.defaultValueTwo = secondvalue;
        }
    }

    public IntegrationType getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(IntegrationType integrationType) {
        this.integrationType = integrationType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcessingOption() {
        return processingOption;
    }

    public void setProcessingOption(String processingOption) {
        this.processingOption = processingOption;
    }

    public String getDefaultValueOne() {
        return defaultValueOne;
    }

    public void setDefaultValueOne(String defaultValueOne) {
        this.defaultValueOne = defaultValueOne;
    }

    public String getDefaultValueTwo() {
        return defaultValueTwo;
    }

    public void setDefaultValueTwo(String defaultValueTwo) {
        this.defaultValueTwo = defaultValueTwo;
    }
}
