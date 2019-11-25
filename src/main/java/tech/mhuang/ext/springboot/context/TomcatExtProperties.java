package tech.mhuang.ext.springboot.context;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * tomcat 配置
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "mhuang.springboot.tomcat")
public class TomcatExtProperties {

    /**
     * create tomcat configuration. default <code>true</code>
     */
    private boolean enable = Boolean.TRUE;
    /**
     * file temp location default system profile
     */
    private String fileLocation;

    /**
     * max file size(mb) default 80mb
     */
    private Integer maxFileSize = 80;

    /**
     * max request size(mb) default  100mb
     */
    private Integer maxRequestSize = 100;

    /**
     * save file size(mb) default 0 mb
     */
    private Integer fileSizeThreshold = 0;
}
