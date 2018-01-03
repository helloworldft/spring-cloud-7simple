package cloud.config.server.repository;

import cloud.config.server.model.ConfigRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by heqingfu on 2017/12/26.
 */
@Repository
public interface ConfigDbRepository extends JpaRepository<ConfigRepository, Long> {
    ConfigRepository findFirstByApplicationAndProfileAndLabel(String application, String profile, String label);
}
