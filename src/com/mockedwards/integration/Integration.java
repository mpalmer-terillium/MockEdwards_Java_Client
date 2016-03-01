package com.mockedwards.integration;

import com.mockedwards.dto.MockIntegrationRequest;
import com.mockedwards.dto.MockIntegrationResponse;

public interface Integration {
    public MockIntegrationResponse processIntegration(MockIntegrationRequest request);
}
