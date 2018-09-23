package com.doubleysoft.kun;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * @author anguslean
 * 18-9-6 下午10:07
 * @since 0.0.1
 */
class KunConfig {
    /**
     * default cached class pool size. the capicity is 1
     */
    private static int defaultPoolSize = 32;
    private static Set<Class> anotations = new HashSet<Class>() {{
        add(Inject.class);
    }};

    static int getDefaultPoolSize() {
        return defaultPoolSize;
    }

    /**
     * get the default inject annotations class
     * @return
     */
    static final Set<Class> getInjectAnotations() {
        return anotations;
    }

}
