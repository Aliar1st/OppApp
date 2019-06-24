package loc.aliar.oppapp.config;

import loc.aliar.oppapp.domain.User;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

public class DataRestConfiguration implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getExposureConfiguration()
                .forDomainType(User.class)
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(HttpMethod.POST));
    }
}
