package cloud.simple.service.web;

import cloud.simple.service.conf.DataSourceProperties;
import cloud.simple.service.domain.UserService;
import cloud.simple.service.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@Api(value = "测试", description = "测试1")
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


	@ApiOperation(value = "获取配置")
	@RequestMapping(value="/get/config",method=RequestMethod.POST)
	public Map getConfig(@RequestParam(required = false) Map<String, String> params){
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("host", redisHost);
		resultMap.put("name", name);
		resultMap.put("age", String.valueOf(age));
		return resultMap;
	}
}
