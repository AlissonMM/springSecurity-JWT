package alisson.springSecurityJWT.beansFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class Beans {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}