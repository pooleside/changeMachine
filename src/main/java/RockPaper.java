import java.util.Map;
import java.util.HashMap;
import java.io.Console;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class RockPaper {
  public static int pennies1 = 0;
  public static int nickels1 = 0;
  public static int dimes1 = 0;
  public static int quarters1 = 0;


  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/whowon", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/whowon.vtl");

      String requestedMoney1 = request.queryParams("requestedMoney");
      int requestedMoney = Integer.parseInt(requestedMoney1);
      int cents = 98;

      coinReturn(requestedMoney);

      String quarters = String.valueOf(quarters1);
      quarters1 = 0;
      String dimes = String.valueOf(dimes1);
      dimes1 =0;
      String nickels = String.valueOf(nickels1);
      nickels1 =0;
      String pennies = String.valueOf(pennies1);
      pennies1 =0;
      model.put("quarters", quarters);
      model.put("dimes", dimes);
      model.put("nickels", nickels);
      model.put("pennies", pennies);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
    public static void coinReturn(int requestedCents)  {
        while(requestedCents != 0) {
          while((requestedCents - 25) > -1){
            quarters1++;
            requestedCents = (requestedCents - 25);
          }
          while((requestedCents - 10) > -1){
            dimes1++;
            requestedCents = (requestedCents - 10);
          }
          while((requestedCents - 5) > -1){
            nickels1++;
            requestedCents = (requestedCents - 5);
          }
          while((requestedCents - 1) > -1){
            pennies1++;
            requestedCents = (requestedCents - 1);
          }
        }
      }
}
