package org.brassbrewery.fragaliciousCore.structure;

public abstract class FragaliciousAPI<T extends FragaliciousModule> {
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
