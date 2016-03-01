package com.mockedwards.integration;

import com.mockedwards.dto.InternalRequest;
import com.mockedwards.dto.MockIntegrationRequest;
import com.mockedwards.dto.MockIntegrationResponse;
import com.mockedwards.util.IntegrationUtil;
import src.com.paytrace.integration.service.CCIntegrationService;
import src.com.paytrace.integration.service.ExternalValueObject;


public class CCIntegration implements Integration {

    @Override
    public MockIntegrationResponse processIntegration(MockIntegrationRequest request) {
        return internalProcessIntegration(IntegrationUtil.validate(request));
    }

    private MockIntegrationResponse internalProcessIntegration(InternalRequest internalRequest) {
        
        ExternalValueObject evo = new ExternalValueObject();
        
        evo.setAmount(Double.parseDouble(internalRequest.getFirstvalue()));
        evo.setTransactionType(internalRequest.getSecondvalue());
        evo.setCreditCardNumber("4012881888818888");
        evo.setExpirationMonth(12);
        evo.setExpirationYear(16);
        evo.setTerms("Y");
        evo.setParmName("PARMLIST");
        evo.setMethod("processtranx");
        
        return new MockIntegrationResponse(new CCIntegrationService().getIntegrationPort().processExternalRequest(evo).getResponse());
    }
}
