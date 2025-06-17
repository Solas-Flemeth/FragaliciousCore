package org.brassbrewery.fragaliciousLib;

import org.brassbrewery.fragaliciousCore.configs.FragaliciousConfig;
import org.brassbrewery.fragaliciousCore.configs.objects.*;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousPlugin;

import java.util.ArrayList;

public class SimpleConfigTest extends FragaliciousConfig {
    private BooleanConfigObject booleanConfigObject;
    private DoubleConfigObject doubleConfigObject;
    private FloatConfigObject floatConfigObject;
    private IntegerConfigObject integerConfigObject;
    private StringConfigObject stringConfigObject;
    private StringListConfigObject stringListConfigObject;

    public SimpleConfigTest(FragaliciousPlugin plugin, String yamlName) {
        super(plugin, yamlName);
    }

    @Override
    public void registerAllConfigObjects() {
        floatConfigObject = new FloatConfigObject("test.main.float", 29.6f, "A test float");
        super.registerConfigObject(floatConfigObject);

        booleanConfigObject = new BooleanConfigObject("boolean", true, "A simple boolean");
        super.registerConfigObject(booleanConfigObject);

        doubleConfigObject = new DoubleConfigObject("test.main.double", 30.5, "A test double");
        super.registerConfigObject(doubleConfigObject);

        integerConfigObject = new IntegerConfigObject("test.main.integer", 14, "A test int");
        super.registerConfigObject(integerConfigObject);

        stringConfigObject = new StringConfigObject("test.main.superStringMcgee", "This is a test String", "A simple string");
        super.registerConfigObject(stringConfigObject);

        ArrayList<String> testDefaultString = new ArrayList<String>(3);
        testDefaultString.add("Hello");
        testDefaultString.add("World");
        testDefaultString.add("Test");
        stringListConfigObject = new StringListConfigObject("test.main.stringlist", testDefaultString, "A list of strings");
        super.registerConfigObject(stringListConfigObject);
    }
}