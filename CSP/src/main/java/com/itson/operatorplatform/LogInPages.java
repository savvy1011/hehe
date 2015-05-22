/**
 * l
 * @author Gurtej Phangureh
 */
package com.itson.operatorplatform;
import com.itson.servicedesigncenter.Browser;




public class LogInPages extends OperatorPlatformPagesAbstract{
  


  public LogInPages(Browser browser) {
    super(browser);
  }
  public String view(String viewGiven) {
    view = viewGiven;
    return view;
  }

}
