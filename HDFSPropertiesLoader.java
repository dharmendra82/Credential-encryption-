import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import org.apache.hadoop.conf.Configuration;

@Configuration
public class HDFSPropertiesLoader {
    @Autowired
    private Environment environment;

    @Value("${fs.defaultFS}")
    String hdfsUri;

    @Bean
    public Configuration configuration() {
        Configuration config = new Configuration();
        config.set("fs.defaultFS", hdfsUri);
        return config;
    }

    @Bean
    public void loadPropertiesFromHDFS() throws IOException {
        FileSystem fs = FileSystem.get(configuration());
        Path path = new Path("/path/to/file.properties");
        Resource resource = new DefaultResourceLoader().getResource(path.toUri().toString());
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);

        PropertySource propertySource = new MapPropertySource("HDFS", properties);
        ((StandardEnvironment) environment).getPropertySources().addLast(propertySource);
    }
}
