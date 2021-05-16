package com.github.grading.config;

import com.github.grading.handlers.WebsocketHandler;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.websocket.server.Constants;
import org.apache.tomcat.websocket.server.WsContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class ServerConfig {

    private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class);

    public static ServerRunner tomcat() throws ServletException {
        Tomcat tomcat = new Tomcat();
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080"; //TODO - make 5432
        }

        tomcat.setPort(Integer.parseInt(webPort));
        Context ctx = tomcat.addWebapp("/", new File(".").getAbsolutePath());
        ctx.addApplicationListener(WsContextListener.class.getName());

        tomcat.addServlet(ctx, "AdminHandler", HandlerConfig.getAdminHandler());
        ctx.addServletMappingDecoded("/admin/*", "AdminHandler");

        tomcat.addServlet(ctx, "DefaultHandler", HandlerConfig.getDefaultHandler());
        ctx.addServletMappingDecoded("/*", "DefaultHandler");

        return new ServerRunner(tomcat, ctx, List.of(chatWebsocketHandler));
    }

    private static Consumer<Context> chatWebsocketHandler = ctx -> {
        ServerContainer scon = (ServerContainer) ctx.getServletContext().getAttribute(Constants.SERVER_CONTAINER_SERVLET_CONTEXT_ATTRIBUTE);
        try {
            scon.addEndpoint(ServerEndpointConfig.Builder.create(WebsocketHandler.class, "/chat")
                    .configurator(new ServerEndpointConfig.Configurator() {
                        @Override
                        public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
                            return (T) AppConfig.getWebsocketHandler();
                            //TODO уйти от каждого раза создания
                        }
                    }).build());
        } catch (DeploymentException e) {
            e.printStackTrace();
        }
    };


    public static void tomcatRun() {
        Tomcat tomcat = new Tomcat();
        try {
            tomcat.setPort(8080);
            String str = new File(".").getAbsolutePath();
            tomcat.addWebapp("",str);
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException | ServletException e) {
            logger.warn("Enter {}", e.getMessage());
        }
    }
}
