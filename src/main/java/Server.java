

import static spark.Spark.*;
import com.foundationXX.prometheus.soundjunkie.service.HealthCheckService;
import com.foundationXX.prometheus.soundjunkie.service.PropertyReloadingService;
import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    public static final String configFile = "system.properties";


    public static void main(String[] args) {

        get("/", (req, res) -> "Hello, World!");


        PropertyReloadingService propertyReloadingService = new PropertyReloadingService();
        propertyReloadingService.init();


        Runnable helloRunnable = new Runnable() {
            public void run() {
                HealthCheckService healthCheckService = new HealthCheckService();
                Configuration config = healthCheckService.loadConfig(configFile);
                System.out.println(config.getString("system.port"));
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);
    }
}