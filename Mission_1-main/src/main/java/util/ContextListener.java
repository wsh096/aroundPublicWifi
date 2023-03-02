package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener  {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{

            String relativePath = "/sql/mission1.sql";
            String realPath = sce.getServletContext().getRealPath(relativePath);
            DatabaseUtil.init(realPath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
