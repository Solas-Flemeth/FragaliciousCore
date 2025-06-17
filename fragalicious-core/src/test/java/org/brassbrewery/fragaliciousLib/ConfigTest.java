package org.brassbrewery.fragaliciousLib;



import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.configs.SimpleConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;

public class ConfigTest {
    private ServerMock server;
    private FragaliciousCore plugin;

    @BeforeEach
    public void setUp() throws Exception {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(FragaliciousCore.class);
    }

    @Test
    public void testFirstConfig() throws Exception {
        SimpleConfigTest simpleConfigTest = new SimpleConfigTest(plugin, "testconfig");
        assert (!simpleConfigTest.getDirectoryPath().isEmpty());
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }
}
