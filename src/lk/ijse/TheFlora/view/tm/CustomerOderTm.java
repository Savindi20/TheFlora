package lk.ijse.TheFlora.view.tm;

import javafx.scene.control.Button;

public class CustomerOderTm {
    private String id;
    private String description;
    private String price;
    private String qty;
    private String total;
    private Button button;

    public CustomerOderTm() {
    }

    public CustomerOderTm(String id, String description, String price, String qty, String total, Button button) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.qty = qty;
        this.total = total;
        this.button = button;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "CustomerOderTm{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", qty='" + qty + '\'' +
                ", total='" + total + '\'' +
                ", button=" + button +
                '}';
    }
}
