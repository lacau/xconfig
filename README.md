# xconfig
## What it is
**xconfig** is a small java api that helps you to use ***properties file*** to store/retrieve key/value pairs.
## Config
You just need to add a **xconfig.properties** file to your application **classpath**.
## Code example
#### *xconfig.properties*
* Defining configs.
* xconfig.properties file **should** be in your **classpath**, or it will throw a **MissingResourceException**.
```java
key.to.my.config.a=My config value A
key.to.my.config.b=My config value B
```
#### *XConfigServiceTest.java*
* Test class.
```java
import br.com.xconfig.exception.ConfigNotFoundException;
import br.com.xconfig.model.model.XConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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
```
