package Leiga.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomException extends Exception {
  private Logger logger = Logger.getLogger(CustomException.class.getName());

  public CustomException(String message){
    super(message);
  }

  public CustomException(Throwable cause){
    super(cause);
  }

  public CustomException(String message, Throwable cause){
    super(message, cause);
  }

  public CustomException(String message, Level severity, Exception exception)
  {
    super(message);
    logger.log(severity, message, exception);
  }
}
