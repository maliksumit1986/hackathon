package hackathon;

public class Stock {
    String symbol;
    String price;
    Double change;
    Double changePercentage;

    public Double getChange() {
        return change;
    }

    public Double getChangePercentage() {
        return changePercentage;
    }

    public String getPrice() {
        return price;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public void setChangePercentage(Double changePercentage) {
        this.changePercentage = changePercentage;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Stock(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "symbol: " + symbol + " price: " + price + " change: " + change + " changePercentage: " + changePercentage;
    }
}
