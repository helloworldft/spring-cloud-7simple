package cloud.config.server.model;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by heqingfu on 2017/12/26.
 */
@Entity
@Table
public class ConfigProperties {

    @Id
    @GeneratedValue
    private Long propertiesKey;

    private String propertiesName;
    @Lob
    @Column
    @Type(type = "cloud.config.server.model.StringJsonUserType")
    private Map<String, String> content = new HashMap<>();

    public Long getPropertiesKey() {
        return propertiesKey;
    }

    public void setPropertiesKey(Long propertiesKey) {
        this.propertiesKey = propertiesKey;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }
}
