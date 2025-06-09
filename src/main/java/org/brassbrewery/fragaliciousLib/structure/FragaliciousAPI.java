package org.brassbrewery.fragaliciousLib.structure;

public abstract class FragaliciousAPI<T extends FragaliciouModule> {
    private T fragaliciouModule;
    public FragaliciousAPI(T fragaliciouModule) {
        this.fragaliciouModule = fragaliciouModule;
    }
    protected T getModule(){
        return fragaliciouModule;
    }
    public boolean isEnabled(){
        return fragaliciouModule.isEnabled();
    }
}
