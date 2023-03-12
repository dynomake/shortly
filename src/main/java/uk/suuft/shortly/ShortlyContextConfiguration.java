package uk.suuft.shortly;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ShortlyContextConfiguration {

    @Bean
    @Scope(value = "singleton")
    public ShortlyRepository repository() {
        return new ShortlyRepository();
    }
}
