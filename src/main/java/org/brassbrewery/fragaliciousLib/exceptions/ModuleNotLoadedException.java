package org.brassbrewery.fragaliciousLib.exceptions;

public class ModuleNotLoadedException extends Exception {
    private final String moduleName;

    public ModuleNotLoadedException(String moduleName){
        super("Module "+ moduleName + " is not loaded");
        this.moduleName = moduleName;
    }
    public String getModuleName(){
        return moduleName;
    }
}
