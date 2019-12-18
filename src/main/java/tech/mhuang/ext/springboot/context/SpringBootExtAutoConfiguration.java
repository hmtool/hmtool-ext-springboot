package tech.mhuang.ext.springboot.context;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import tech.mhuang.core.id.BaseIdeable;
import tech.mhuang.core.id.SnowflakeIdeable;
import tech.mhuang.ext.spring.start.SpringContextHolder;

import javax.servlet.MultipartConfigElement;

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

    private final SpringBootExtProperties properties;

    public SpringBootExtAutoConfiguration(SpringBootExtProperties properties){
        this.properties = properties;
    }

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

    @Configuration
    @ConditionalOnWebApplication
    @ConditionalOnClass(Tomcat.class)
    @EnableConfigurationProperties(TomcatExtProperties.class)
    @ConditionalOnProperty(prefix = "mhuang.springboot.tomcat", name = "enable", havingValue = "true", matchIfMissing = true)
    static class SpringBootTomcatExtProperties{

        private final TomcatExtProperties tomcatExtProperties;

        public SpringBootTomcatExtProperties(TomcatExtProperties tomcatExtProperties){
            this.tomcatExtProperties = tomcatExtProperties;
        }

        @Bean
        @ConditionalOnMissingBean
        public MultipartConfigElement multipartConfigElement(){
            MultipartConfigFactory config = new MultipartConfigFactory();
            config.setMaxFileSize(DataSize.ofMegabytes(tomcatExtProperties.getMaxFileSize()));
            config.setMaxRequestSize(DataSize.ofMegabytes(tomcatExtProperties.getMaxRequestSize()));
            config.setLocation(tomcatExtProperties.getFileLocation());
            config.setFileSizeThreshold(DataSize.ofMegabytes(tomcatExtProperties.getFileSizeThreshold()));
            return config.createMultipartConfig();
        }
    }
}
