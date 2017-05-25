package co.edu.poligran.serviciosalestudiante.configuration;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class ConfiguracionCache implements JCacheManagerCustomizer {
    @Override
    public void customize(CacheManager cacheManager) {
        cacheManager.createCache("bloques", new MutableConfiguration<>()
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, 600)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true));

        cacheManager.createCache("reservas", new MutableConfiguration<>()
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, 600)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true));

        cacheManager.createCache("reservas-vigentes-grafica", new MutableConfiguration<>()
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, 600)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true));

        cacheManager.createCache("reservas-historico-grafica", new MutableConfiguration<>()
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, 600)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true));
    }
}