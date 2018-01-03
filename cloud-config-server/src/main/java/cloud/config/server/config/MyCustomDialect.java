package cloud.config.server.config;

import org.hibernate.dialect.MySQL5Dialect;

import java.sql.Types;

/**
 * Created by heqingfu on 2017/12/27.
 */
public class MyCustomDialect extends MySQL5Dialect {
    public MyCustomDialect() {
        super();
        this.registerColumnType(Types.JAVA_OBJECT, "json");
    }
}
