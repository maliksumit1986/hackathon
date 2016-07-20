package hackathon;

/**
 * Created by sumitmalik on 20/7/16.
 */
public class StockCacheReloadJob implements Runnable {
    @Override
    public void run() {
        try {
            StockService.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
