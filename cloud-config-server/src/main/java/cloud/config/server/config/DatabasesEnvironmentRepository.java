package cloud.config.server.config;

import cloud.config.server.model.ConfigProperties;
import cloud.config.server.service.ConfigRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.util.StringUtils;
import cloud.config.server.model.*;

/**
 * Created by heqingfu on 2017/12/26.
 */
public class DatabasesEnvironmentRepository implements EnvironmentRepository {

    @Autowired
    private ConfigRepositoryService configRepositoryService;

    @Override
    public Environment findOne(String application, String profile, String label) {
        if (StringUtils.isEmpty(application) || StringUtils.isEmpty(profile))
            return null;
        ConfigRepository configRepositories = configRepositoryService.findByApplicationAndProfileAndLabel(application, profile, label);
        if (configRepositories != null) {
            Environment environment = new Environment(application, StringUtils.commaDelimitedListToStringArray(profile), label, configRepositories.getVersion(),null);
            for (ConfigProperties configProperties : configRepositories.getConfigPropertiesList()) {
                environment.add(new PropertySource(configProperties.getPropertiesName(), configProperties.getContent()));
            }
            return environment;
        }
        return new Environment(application, profile);
    }
}
