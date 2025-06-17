package org.brassbrewery.fragaliciousCore.exceptions;

public class ServiceNotLoadedException extends Exception {
    private final String serviceName;
    public ServiceNotLoadedException(String serviceName) {
        super("The service " + serviceName + " has not been loaded.");
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
