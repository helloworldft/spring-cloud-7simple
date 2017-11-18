package cloud.simple.service.web;

import cloud.simple.service.conf.DataSourceProperties;
import cloud.simple.service.domain.UserService;
import cloud.simple.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

	//@Value("${spring.redis.host}")
	private String redisHost;

	//@Value("${my.name}")
	private String name;

	@Autowired
	UserService userService;

	@Autowired
	DataSourceProperties dataSourceProperties;
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public List<User> readUserInfo(){
		System.out.println("host is "+ redisHost);
		System.out.println("my name is  "+ name);
		List<User> ls=userService.searchAll();
		return ls;
	}
}
