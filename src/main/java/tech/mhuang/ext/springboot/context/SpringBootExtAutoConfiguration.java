package tech.mhuang.ext.springboot.context;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.mhuang.core.id.BaseIdeable;
import tech.mhuang.core.id.SnowflakeIdeable;
import tech.mhuang.ext.spring.start.SpringContextHolder;

/**
 * springboot启动装载类
 *
 * @author mhuang
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(SpringBootExtProperties.class)
@ConditionalOnProperty(prefix = "mhuang.springboot", name = "enable", havingValue = "true", matchIfMissing = true)
public class SpringBootExtAutoConfiguration {

    /**
     * create context process tool
     *
     * @return SpringContextHolder
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringContextHolder contextHolder() {
        return new SpringContextHolder();
    }

    /**
     * create generator id.
     *  default used snowflake
     * @return BaseIdeable
     */
    @Bean
    @ConditionalOnMissingBean
    public BaseIdeable snowflake() {
        return new SnowflakeIdeable();
    }
}
