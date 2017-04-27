package data.config.optimistic.locking.vm;

import com.vladmihalcea.concurrent.aop.OptimisticConcurrencyControlAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @author Anghel Leonard
 */
@Configuration
@EnableAspectJAutoProxy
public class SHConfigurationTest {

    @Bean
    public OptimisticConcurrencyControlAspect optimisticConcurrencyControlAspect() {
        return new com.vladmihalcea.concurrent.aop.OptimisticConcurrencyControlAspect();
    }
}
