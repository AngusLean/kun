package com.doubleysoft.kun.contexttest;

import lombok.Builder;
import lombok.Data;

/**
 * @author anguslean
 * 18-9-27 下午5:30
 * @since 0.0.1
 */
@Data
@Builder
public class UserDO {
    private String name;
    private String phone;
}
