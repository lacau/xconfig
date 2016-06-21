package br.com.xconfig.service;

import br.com.xconfig.exception.ConfigNotFoundException;
import br.com.xconfig.model.model.XConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by lacau on 15/06/16.
 */
public class XConfigServiceTest {

    private static final String TEST_KEY = "testKey";

    private static final String TEST_VALUE = "testValue";

    private static XConfigService service;

    @BeforeClass
    public static void init() {
        service = XConfigService.getInstance();
    }

    @Test(expected = ConfigNotFoundException.class)
    public void testPutGetRemove() {
        service.put(TEST_KEY, TEST_VALUE);

        XConfig config = service.get(TEST_KEY);
        Assert.assertNotNull(config);
        Assert.assertEquals(config.getKey(), TEST_KEY);
        Assert.assertEquals(config.getValue(), TEST_VALUE);

        service.remove(TEST_KEY);

        service.get(TEST_KEY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWhenKeyNull() {
        service.get(null);
    }

    @Test(expected = ConfigNotFoundException.class)
    public void testGetWhenConfigKeyNotFound() {
        service.get(TEST_KEY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutWhenKeyNull() {
        service.put(null, TEST_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutWhenValueNull() {
        service.put(TEST_KEY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutWhenKeyAndValueNull() {
        service.put(null, null);
    }
}