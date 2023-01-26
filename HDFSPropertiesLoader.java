import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

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
