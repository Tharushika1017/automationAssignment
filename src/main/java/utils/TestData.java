package utils;

public class TestData {
    private String searchTerm;
    private int quantity;
    private String expectedSubtotal;
    private boolean cartPopup;
    private String cartItemCount;
    private String subtotal;
    private boolean chatIcon;

    public TestData(String searchTerm, int quantity, String expectedSubtotal, boolean cartPopup, String cartItemCount, String subtotal, boolean chatIcon) {
        this.searchTerm = searchTerm;
        this.quantity = quantity;
        this.expectedSubtotal = expectedSubtotal;
        this.cartPopup = cartPopup;
        this.cartItemCount = cartItemCount;
        this.subtotal = subtotal;
        this.chatIcon = chatIcon;
    }

    // Getters for each field
}
