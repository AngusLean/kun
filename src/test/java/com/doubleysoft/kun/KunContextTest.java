package com.doubleysoft.kun;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by anguslean
 * 18-9-9 下午6:50
 */
public class KunContextTest {
    private KunContext kunContext;

    @Before
    public void setUp() throws Exception {
        kunContext = new KunContext();
    }

    @Test
    public void init() {
        kunContext.init();
    }
}