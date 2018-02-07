package cloud.simple.service.web;

import cloud.simple.service.conf.DataSourceProperties;
import cloud.simple.service.domain.UserService;
import cloud.simple.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
public class UserController {

	//@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${businessConfig.name}")
	private String name;

	@Value("${businessConfig.age}")
	private Integer age;

	@Autowired
	UserService userService;

	@Autowired
	DataSourceProperties dataSourceProperties;
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public List<User> readUserInfo(){
		System.out.println("host is "+ redisHost);
		System.out.println("business config name is  "+ name);
		System.out.println("business config age is  "+ age);
		List<User> ls=userService.searchAll();
		return ls;
	}
}
