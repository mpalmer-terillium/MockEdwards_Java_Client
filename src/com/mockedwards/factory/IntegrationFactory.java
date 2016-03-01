package com.mockedwards.factory;

import com.mockedwards.integration.CCIntegration;
import com.mockedwards.integration.Integration;
import com.mockedwards.integration.IntegrationType;
import com.mockedwards.integration.OSCIntegration;


public final class IntegrationFactory {
    
    public static Integration getIntegration(IntegrationType integrationType) {
        
        switch (integrationType) {
            case PayTrace:
                return new CCIntegration();
            case SalesCloud:
                return new OSCIntegration();
            default:
                return null;
        }
    }
}
