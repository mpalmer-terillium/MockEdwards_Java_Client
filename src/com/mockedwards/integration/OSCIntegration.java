package com.mockedwards.integration;

import com.mockedwards.dto.InternalRequest;
import com.mockedwards.dto.MockIntegrationRequest;
import com.mockedwards.dto.MockIntegrationResponse;
import com.mockedwards.util.IntegrationUtil;
import com.osc.optyservice.service.OptyServiceImplService;
import com.osc.optyservice.service.OscIntegrationRequest;
import com.osc.optyservice.service.OscIntegrationResponse;


public class OSCIntegration implements Integration {

    @Override
    public MockIntegrationResponse processIntegration(MockIntegrationRequest request) {
        return internalProcessIntegration(IntegrationUtil.validate(request));
    }

    private MockIntegrationResponse internalProcessIntegration(InternalRequest request) {
        OscIntegrationRequest oscReq  = new OscIntegrationRequest();
        
        oscReq.setOptyName(request.getFirstvalue());
        oscReq.setTransactionType(request.getSecondvalue());
        
        return new MockIntegrationResponse(new OptyServiceImplService().getOptyServiceImplPort().processOSCIntegrationRequest(oscReq).getResponse());
    }
    
}
