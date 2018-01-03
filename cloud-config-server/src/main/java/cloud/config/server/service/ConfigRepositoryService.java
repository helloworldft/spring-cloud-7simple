package cloud.config.server.service;

import cloud.config.server.model.ConfigRepository;
import cloud.config.server.repository.ConfigDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by heqingfu on 2017/12/26.
 */
@Service
public class ConfigRepositoryService {
    @Autowired
    private ConfigDbRepository configDbRepository;

    public ConfigRepository findByApplicationAndProfileAndLabel(String application, String profile, String label) {
        return configDbRepository.findFirstByApplicationAndProfileAndLabel(application, profile, label);
    }
}