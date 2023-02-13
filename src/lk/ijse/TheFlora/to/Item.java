package lk.ijse.TheFlora.to;

public class Item {
    private String itemID;
    private String It_name;
    private String Price;
    private String description;
    private String stockID;
    private int qty;

    public Item() {
    }

    public Item(String itemID, String it_name, String price, String description, String stockID, int qty) {
        this.itemID = itemID;
        It_name = it_name;
        Price = price;
        this.description = description;
        this.stockID = stockID;
        this.qty = qty;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getIt_name() {
        return It_name;
    }

    public void setIt_name(String it_name) {
        It_name = it_name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID='" + itemID + '\'' +
                ", It_name='" + It_name + '\'' +
                ", Price='" + Price + '\'' +
                ", description='" + description + '\'' +
                ", stockID='" + stockID + '\'' +
                ", qty=" + qty +
                '}';
    }
}
