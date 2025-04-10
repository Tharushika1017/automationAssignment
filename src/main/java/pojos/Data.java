package pojos;

public class Data {
    private final String searchQuery;
    private final String sortOrder;
    private final String homePageTitle;
    private final String searchResultHeader;
    private final String errorCountMismatch;
    private int quantity;

    public Data(String searchQuery, String sortOrder, String homePageTitle, String searchResultHeader, String errorCountMismatch, int quantity) {
        this.searchQuery = searchQuery;
        this.sortOrder = sortOrder;
        this.homePageTitle = homePageTitle;
        this.searchResultHeader = searchResultHeader;
        this.errorCountMismatch = errorCountMismatch;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getSearchQuery() {
        return searchQuery;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public String getHomePageTitle() {
        return homePageTitle;
    }

    public String getSearchResultHeader() {
        return searchResultHeader;
    }

    public String getErrorCountMismatch() {
        return errorCountMismatch;
    }


    public String getErrorSortMismatch() {
        return errorCountMismatch;
    }

    public int getQuantity() {
        return quantity;
    }
}
