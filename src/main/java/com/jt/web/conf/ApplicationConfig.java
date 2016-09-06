package com.jt.web.conf;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = "com.jt.web")
@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**")
				.addResourceLocations("/WEB-INF/assets/")
				.setCachePeriod(31556926);
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/templates/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters ) {
    	converters.add(new MappingJackson2HttpMessageConverter());
    	converters.add(new StringHttpMessageConverter());
    }
    
//    @Bean
//    public DataSource setDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("driver.package");
//        dataSource.setUrl("jdbcUrlString");
//        dataSource.setUsername("username");
//        dataSource.setPassword("password");
//         
//        return dataSource;
//    }
//    
//	@Bean
//	@Resource(name = "jdbc/jndiName")
//	public DataSource setJndiDataSource() {
//		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//		if (System.getProperty("os.name").contains("Windows"))
//			dsLookup.setResourceRef(true);
//		DataSource dataSource = dsLookup.getDataSource("jdbc/jndiName");
//		return dataSource;
//	}
}