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
import java.util.logging.Level;
import java.util.logging.Logger;


/*
    convert this to a webapp and have it run this on startup - this way it will
    be listening at all times to the queue.  Might not be the best solution, but
    see if it works for now.

    reference:
    http://stackoverflow.com/questions/158336/is-there-a-way-to-run-a-method-class-only-on-tomcat-startup
*/
public class MockEdwardsClient {

    private static final Logger logger = Logger.getLogger("MockEdwardsClient");
    private static final MockQueueService queue = new MockQueueServiceService().getMockQueueServicePort();
    
//    use this to generate the new jar file for the queue service
//    wsimport -d ./MockQueue -keep -clientjar mockqueue.jar http://localhost:8080/MockQueueService/MockQueueService?wsdl

    public static void main(String[] args) throws InterruptedException {

        while (true) {

            Thread.sleep(4000);
            Message message = queue.getNextMessage();

            if (message.getId() != -1) {
                routeMessage(message);
            }
        }
    }

    private static void routeMessage(Message m) {

        switch (m.getType()) {

            case PAY_TRACE:
                MockIntegrationResponse ccResponse  = doCCIntegration(new MockIntegrationRequest(
                        IntegrationType.PayTrace, "100.00", "Authorization"));
                logger.info(ccResponse.getResponse());
                break;

            case SALES_CLOUD:
                MockIntegrationResponse oscResponse = doOSCIntegration(new MockIntegrationRequest(
                        IntegrationType.SalesCloud, "TEST OP WS", "READ"));
                logger.info(oscResponse.getResponse());
                break;

            default:
                logger.log(Level.INFO, "Unable to route unknown message type: {0}", m.getType());
                break;
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
