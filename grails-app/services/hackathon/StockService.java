package hackathon;



import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sumitmalik on 20/7/16.
 */
public class StockService {

    static String[] symbols = new String[] {"SBIN", "HDFC", "TCS"};//, "AIR.PA", "YHOO"};
    static ConcurrentMap<String, Stock> stocks = new ConcurrentHashMap<String,Stock>();
    static final String GOOGLE_FINANCE_URL = "http://finance.google.com/finance/info?q=";

    public static void reload() throws Exception {

        for (String symbol : symbols) {
            stocks.put( symbol, getStockFromHTML( GOOGLE_FINANCE_URL, "BSE:", symbol) );
        }

    }

    public static Stock getStockFromHTML(String googleUrl, String se, String stockName) throws Exception {
        String urlToRead = googleUrl + se + stockName;
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        Map<String,Object> resultJson = new Gson().fromJson(result.toString().substring(4, result.length()-1), Map.class);

        Stock stock = new Stock(stockName);
        stock.setPrice((String) resultJson.get("l_cur"));
        stock.setChange(Double.parseDouble(((String) resultJson.get("c")).substring(1)));
        stock.setChangePercentage((Double.parseDouble(((String) resultJson.get("cp")))));
        return stock;
    }


    public static Map<String, Stock> getStocksMapBySymbols (String[] symbols) {
        Map<String, Stock> stocksMap = new HashMap<>();
        for (String symbol : symbols) {
            Stock stock = getStockBySymbol(symbol);
            if (stock != null) {
                stocksMap.put(symbol, stock);
            }
        }
        return stocksMap;
    }

    public static Stock getStockBySymbol(String symbol) {
        return stocks.get(symbol);
    }

    public static void main(String[] args) throws Exception {
        StockService ss = new StockService();
        ss.reload();

        for (String s : stocks.keySet()) {
            System.out.println(stocks.get(s).toString());
        }
    }

    public static String getPrice(String symbol) {
        if (stocks.get(symbol) != null) {
            return stocks.get(symbol).getPrice();
        }
        return null;
    }
}