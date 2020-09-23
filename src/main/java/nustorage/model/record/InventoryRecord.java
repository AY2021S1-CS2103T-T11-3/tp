package nustorage.model.record;

import java.time.LocalDate;
import java.time.LocalTime;

public class InventoryRecord {

    private final LocalDate date;
    private final LocalTime time;
    private final int quantity;
    private final String itemName;

    public InventoryRecord(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public InventoryRecord(String itemName, int quantity, LocalDate date) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.date = date;
        this.time = LocalTime.of(0, 0);
    }

    public InventoryRecord(String itemName, int quantity, LocalDate date, LocalTime time) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InventoryRecord) {
            return (((InventoryRecord) obj).quantity == this.quantity
                    && ((InventoryRecord) obj).itemName.equals(this.itemName)) ;
        }
        return false;
    }
    @Override
    public String toString() {
        return "Record on " + date + " at " + time + ": " + itemName + " changed by " + quantity;
    }

}
