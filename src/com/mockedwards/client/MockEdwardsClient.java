package com.mockedwards.client;

import com.mockedwards.dto.MockIntegrationRequest;
import com.mockedwards.dto.MockIntegrationResponse;
import com.mockedwards.factory.IntegrationFactory;
import com.mockedwards.integration.CCIntegration;
import com.mockedwards.integration.IntegrationType;
import com.mockedwards.integration.OSCIntegration;
import java.util.logging.Logger;


public class MockEdwardsClient {
    
    private static final Logger LOGGER = Logger.getLogger("MockEdwardsClient");

    public static void main(String[] args) {
        
        MockIntegrationResponse ccResponse  = doCCIntegration(new MockIntegrationRequest(
                IntegrationType.PayTrace, "100.00", "Authorization"));
        
        MockIntegrationResponse oscResponse = doOSCIntegration(new MockIntegrationRequest(
                IntegrationType.SalesCloud, "TEST OP WS", "READ"));
        
        LOGGER.info(ccResponse.getResponse());
        LOGGER.info(oscResponse.getResponse());
    }

    private static MockIntegrationResponse doCCIntegration(MockIntegrationRequest mockRequest) {
        CCIntegration ccInt = (CCIntegration) IntegrationFactory.getIntegration(mockRequest.getIntegrationType());
        return ccInt.processIntegration(mockRequest);
    }

    private static MockIntegrationResponse doOSCIntegration(MockIntegrationRequest mockRequest) {
        OSCIntegration oscInt = (OSCIntegration) IntegrationFactory.getIntegration(mockRequest.getIntegrationType());
        return oscInt.processIntegration(mockRequest);
    }
}
