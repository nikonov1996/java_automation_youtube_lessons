package readProperties;

import com.typesafe.config.ConfigFactory;
import com.typesafe.config.Config;

public interface ConfigProvider {

    Config config = ConfigFactory.load("helpdesk.conf");


    static Config readConfig(){
        return /*ConfigFactory.systemProperties().hasPath("testProfile")
                        ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                        :*/ ConfigFactory.load("application.config");
    }

    String URL = config.getString("url");
    Integer AGE = config.getInt("age");
    String ADMIN_LOGIN = config.getString("usersParams.admin.login");
    String ADMIN_PASSWORD = config.getString("usersParams.admin.password");
    Boolean IS_ADMIN_ADMIN = config.getBoolean("usersParams.admin.isAdmin");

    String DEMO_PASSWORD = config.getString("usersParams.demo.password");
    String DEMO_LOGIN = config.getString("usersParams.demo.login");
    Boolean IS_DEMO_ADMIN = config.getBoolean("usersParams.demo.isAdmin");





}
