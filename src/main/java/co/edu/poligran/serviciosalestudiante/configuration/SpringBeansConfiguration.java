package co.edu.poligran.serviciosalestudiante.configuration;

import java.io.IOException;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class SpringBeansConfiguration {

	@Bean
	public DozerBeanMapperFactoryBean configDozer() throws IOException {
		DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:dozer-mappings.xml");
        dozerBeanMapperFactoryBean.setMappingFiles(resources);
        return dozerBeanMapperFactoryBean;
	}
}
