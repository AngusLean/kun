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

    private static int defaultPoolSize = 32;
    private static Set<Class> anotations = new HashSet<Class>() {{
        add(Inject.class);
    }};

    static int getDefaultPoolSize() {
        return defaultPoolSize;
    }


    static final Set<Class> getInjectAnotations() {
        return anotations;
    }

}
