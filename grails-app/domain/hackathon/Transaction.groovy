package hackathon

class Transaction {

    Long id
//    Stock stock
//    Portfolio portfolio
    User user

    Double quantity
    Double unitPrice
    BuyOrSell buyOrSell

    Date transactionDate;
    static constraints = {
    }

    static belongsTo = [portfolio: Portfolio, stock: Stock]
}
