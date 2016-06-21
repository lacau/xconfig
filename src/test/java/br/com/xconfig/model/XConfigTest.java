package br.com.xconfig.model;

import java.util.List;

import br.com.xconfig.model.model.XConfig;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lacau on 15/06/16.
 */
public class XConfigTest {

    private static final String TEST_KEY = "testKey";

    private static final String TEST_VALUE_A = "testValue A";

    private static final String TEST_VALUE_B = "testvalue B";

    @Test
    public void testGetValues() {
        final XConfig config = new XConfig(TEST_KEY, TEST_VALUE_A + XConfig.MULTI_VALUE_SEPARATOR + TEST_VALUE_B);
        Assert.assertNotNull(config);

        final List<String> values = config.getValues();
        Assert.assertNotNull(values);
        Assert.assertTrue(values.size() == 2);
        Assert.assertEquals(values.get(0), TEST_VALUE_A);
        Assert.assertEquals(values.get(1), TEST_VALUE_B);
    }

    @Test
    public void testGetValuesWhenSingleValue() {
        final XConfig config = new XConfig(TEST_KEY, TEST_VALUE_A);
        Assert.assertNotNull(config);
        Assert.assertNull(config.getValues());
    }
}