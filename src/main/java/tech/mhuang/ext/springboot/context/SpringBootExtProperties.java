package tech.mhuang.ext.springboot.context;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * SpringBoot扩展配置
 *
 * @author mhuang
 * @since 1.3.0
 */
@Data
@ConfigurationProperties(prefix = "mhuang.springboot")
public class SpringBootExtProperties {

    private boolean enable = Boolean.TRUE;
}
