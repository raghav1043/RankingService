package rankingService.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfig {

    public JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder("http://localhost:9200")
                        .multiThreaded(true)
                        .defaultMaxTotalConnectionPerRoute(2)
                        .maxTotalConnection(10)
                        .build());
        return factory.getObject();
    }

}
