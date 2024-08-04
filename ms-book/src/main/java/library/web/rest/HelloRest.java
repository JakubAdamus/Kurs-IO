package library.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloRest {

    //@RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    @ResponseBody
    String sayHello() {
        return "Hi World!";
    }
}
