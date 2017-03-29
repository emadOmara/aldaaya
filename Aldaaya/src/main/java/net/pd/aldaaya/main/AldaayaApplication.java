package net.pd.aldaaya.main;

import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.pd.aldaaya.common.AldaayaConstants;

@SpringBootApplication
@ComponentScan(value = { "net.pd.aldaaya" })
@EnableJpaRepositories(basePackages = { "net.pd.aldaaya.dao" })
@EntityScan(basePackages = "net.pd.aldaaya.common.model")
@EnableTransactionManagement
public class AldaayaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AldaayaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AldaayaApplication.class);
	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		b.indentOutput(true).dateFormat(new SimpleDateFormat(AldaayaConstants.DATE_FORMAT));
		return b;
	}

}

@Configuration
@EnableWebMvc
class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
}