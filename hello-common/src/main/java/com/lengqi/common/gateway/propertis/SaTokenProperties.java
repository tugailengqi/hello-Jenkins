package com.lengqi.common.gateway.propertis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "lengqi.cloud")
public class SaTokenProperties {
    /**
     * 只有通过网关才能获取资源
     * 默认为true
     */

    private Boolean onlyFetchByGateway = Boolean.FALSE;
}
