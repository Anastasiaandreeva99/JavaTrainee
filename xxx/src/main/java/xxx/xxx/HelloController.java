package xxx.xxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    @Autowired
    Service1 service1;

    @RequestMapping("/hello1")
    String hello() {
        return "Hello "+ service1.method1();
    }

}
