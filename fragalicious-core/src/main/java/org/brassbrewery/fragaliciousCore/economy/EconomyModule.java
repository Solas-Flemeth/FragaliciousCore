package org.brassbrewery.fragaliciousCore.economy;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousModule;

public class EconomyModule extends FragaliciousModule<BasicEconomyAPI> {
    private TransactionService transactionService;
    public EconomyModule() {
        super(FragaliciousCore.getInstance());
    }

    @Override
    protected BasicEconomyAPI createAPI(boolean isEnabled) {
        return new BasicEconomyAPI(this);
    }

    @Override
    public String moduleName() {
        return "Economy";
    }

    @Override
    public boolean canLaunchModule() {
        return  isPluginEnabled("Vault");
    }

    @Override
    public void preInit() {

    }

    @Override
    public void registerListeners() {

    }

    @Override
    public void registerServices() {
        transactionService = new TransactionService(this);
    }

    @Override
    public void registerCommands() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void onReload() {

    }
    protected TransactionService getTransactionService(){
        return this.transactionService;
    }
}
