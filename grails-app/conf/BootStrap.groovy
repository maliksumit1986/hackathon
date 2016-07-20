import hackathon.StockCacheReloadJob

import java.util.concurrent.TimeUnit

class BootStrap {

    def init = { servletContext ->
        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new StockCacheReloadJob(), 0, 1, TimeUnit.MINUTES);
    }
    def destroy = {
    }
}
