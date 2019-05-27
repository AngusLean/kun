package com.doubleysoft.kun.ioc.context;

import lombok.Builder;
import lombok.Data;

/**
 * @author cupofish@gmail.com
 * 5/27/19 21:13
 */
@Data
@Builder
public class Depend {
    /**
     * usually class complete name
     */
    private String name;

    /**
     * usually field name or method parameter name
     */
    private String simpleName;

    @Builder.Default
    private boolean isField = true;

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof Depend ? ((Depend) obj).getName().equals(name) : false);
    }
}
