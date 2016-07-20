package hackathon

class Portfolio {

    static constraints = {
    }

    Long id
    String name
    String description

    static hasMany = [portfolioStockMaps: PortfolioStockMap]
    static belongsTo = [user: User]




}
