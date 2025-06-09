package org.brassbrewery.fragaliciousLib.configs;



import org.brassbrewery.fragaliciousLib.FragaliciousLib;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;

public class ConfigTest {
    private ServerMock server;
    private FragaliciousLib plugin;

    @BeforeEach
    public void setUp() throws Exception {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(FragaliciousLib.class);
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
