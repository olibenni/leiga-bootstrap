package Leiga.controller;

import Leiga.controller.dto.UserDTO;
import Leiga.persistance.usersDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

@Controller
@RequestMapping("/service")
public class ExampleService {
  private Logger logger = Logger.getLogger(ExampleService.class.getName());
  private usersDAO db = new usersDAO();

  @RequestMapping(method= RequestMethod.GET)
  public @ResponseBody String helloworld()
  {
    String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
    logger.info("Incoming request for path /service from: " + sessionId);
    return "Hello World!";
  }

  @RequestMapping(value= "/hello/{name}", method= RequestMethod.GET)
  public @ResponseBody String helloname(@PathVariable(value="name") String name)
  {
    String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
    logger.info("Incoming request for path /service/hello/{name} from: " + sessionId + ". With name = " + name);
    return "Hello " + name;
  }

  @RequestMapping(value= "/user", method= RequestMethod.GET)
  public @ResponseBody String getUserFromUrl(@NotNull(message="name required in params") @RequestParam(value="name") String name)
  {
    String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
    logger.info("Incoming request for path /service/user?name= from: " + sessionId + ". With params name = " + name);
    return "Hello " + name;
  }

  @RequestMapping(value= "/json", method= RequestMethod.GET)
  public @ResponseBody UserDTO getJson()
  {
    String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
    logger.info("Incoming request for path /service/user from: " + sessionId);
    return new UserDTO(20, "somename", sessionId);
  }
}
