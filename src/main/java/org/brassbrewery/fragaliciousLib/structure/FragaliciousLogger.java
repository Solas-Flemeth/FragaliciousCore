package org.brassbrewery.fragaliciousLib.structure;

import org.slf4j.Logger;


public class FragaliciousLogger {
    private final Logger logger;
    private final String serviceName;
    public FragaliciousLogger(FragaliciousPlugin plugin, String serviceName){
        logger = plugin.getSLF4JLogger();
        this.serviceName = serviceName;
    }

    public void fine(String message){
        logger.atInfo().log(serviceName + ": " + message);
    }
    public void warning(String message){
        logger.atWarn().log(serviceName + ": " + message);
    }
    public void error(String message){
        logger.atError().log(serviceName + ": " + message);
    }
    public void debug(String message){
        logger.atDebug().log(serviceName + ": " + message);
    }
    public boolean isDebugEnabled(){
        return logger.isDebugEnabled();
    }
}
