package me.ramon.multitenant.multitenancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by KADOOS on 01/21/2017.
 */

@Configuration
public class TannentIdInterceptorConfigurer extends WebMvcConfigurerAdapter {
  @Autowired
  TannentIdInterceptor tannentIdInterceptor;
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(tannentIdInterceptor);
  }

}