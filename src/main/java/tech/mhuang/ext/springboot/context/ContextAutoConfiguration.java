package tech.mhuang.ext.springboot.context;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(prefix = "mhuang.holder", name = "enable", havingValue = "true", matchIfMissing = true)
public class ContextAutoConfiguration {

    /**
     * create context process tool
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringContextHolder contextHolder() {
        return new SpringContextHolder();
    }

    /**
     * create generator id.
     *  default used snowflake
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public BaseIdeable snowflake() {
        return new SnowflakeIdeable();
    }
}
