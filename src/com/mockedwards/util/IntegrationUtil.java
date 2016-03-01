package com.mockedwards.util;

import com.mockedwards.dto.InternalRequest;
import com.mockedwards.dto.MockIntegrationRequest;


public class IntegrationUtil {
    
    public static InternalRequest validate(MockIntegrationRequest request) {
        
        switch (request.getIntegrationType()) {
            case PayTrace:
                if(request.getAmount() != null && request.getIntegrationType() != null && request.getTransactionType() != null) {
                    return new InternalRequest(InternalRequest.InternalIntegrationResponseStatus.OK,
                                                request.getIntegrationType(),
                                                request.getAmount(),
                                                request.getTransactionType());
                } else {
                    return new InternalRequest(InternalRequest.InternalIntegrationResponseStatus.ERROR,
                                                request.getIntegrationType(),
                                                null,
                                                null);
                }
            case SalesCloud:
                if(request.getDescription() != null && request.getProcessingOption() != null) {
                    return new InternalRequest(InternalRequest.InternalIntegrationResponseStatus.OK,
                                                request.getIntegrationType(),
                                                request.getDescription(),
                                                request.getProcessingOption());
                } else {
                    return new InternalRequest(InternalRequest.InternalIntegrationResponseStatus.ERROR,
                                                request.getIntegrationType(),
                                                null,
                                                null);
                }
            default:
                return new InternalRequest(InternalRequest.InternalIntegrationResponseStatus.ERROR,
                                            request.getIntegrationType(),
                                            request.getDefaultValueOne(),
                                            request.getDefaultValueTwo());
        }
    }
}
