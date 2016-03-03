package com.mockedwards.client;

import com.mockedwards.dto.MockIntegrationRequest;
import com.mockedwards.dto.MockIntegrationResponse;
import com.mockedwards.factory.IntegrationFactory;
import com.mockedwards.integration.CCIntegration;
import com.mockedwards.integration.IntegrationType;
import com.mockedwards.integration.OSCIntegration;
import com.mockqueue.service.Message;
import com.mockqueue.service.MockQueueService;
import com.mockqueue.service.MockQueueServiceService;
import java.util.logging.Logger;


public class MockEdwardsClient {
    
    private static final Logger LOGGER = Logger.getLogger("MockEdwardsClient");
    private static final MockQueueService queue = new MockQueueServiceService().getMockQueueServicePort();

    public static void main(String[] args) throws InterruptedException  {
        
        
        while(true) {
            
            Thread.sleep(4000);
            Message message = queue.getNextMessage();
        
            if(message.getId() != -1) {
                
                LOGGER.info("MESSAGE READ");
                
                MockIntegrationResponse ccResponse  = doCCIntegration(new MockIntegrationRequest(
                        IntegrationType.PayTrace, "100.00", "Authorization"));

        //        MockIntegrationResponse oscResponse = doOSCIntegration(new MockIntegrationRequest(
        //                IntegrationType.SalesCloud, "TEST OP WS", "READ"));

                LOGGER.info(ccResponse.getResponse());
        //        LOGGER.info(oscResponse.getResponse());
            } else {
                LOGGER.info("NO MESSAGES");
            }
        }
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
