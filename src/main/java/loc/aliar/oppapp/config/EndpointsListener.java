package loc.aliar.oppapp.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointsListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            ((RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping")).getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
                System.out.println(requestMappingInfo);
            });
        }
    }
}