package nustorage.testutil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import nustorage.logic.parser.ParserUtil;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.record.InventoryRecord;

public class InventoryRecordBuilder {

    public static final String DEFAULT_NAME = "MacBook Pro";
    public static final String DEFAULT_DATE = "2020-04-20";
    public static final String DEFAULT_TIME = "13:00";
    public static final double DEFAULT_COST = 30;
    public static final int DEFAULT_QUANTITY = 55;
    public static final int DEFAULT_FINANCE_ID = 0;

    private String itemName;
    private LocalDateTime dateTime;
    private Double cost;
    private int financeId;
    private int quantity;

    /**
     * Creates a {@code InventoryRecordBuilder} with the default details.
     */
    public InventoryRecordBuilder() {
        itemName = DEFAULT_NAME;
        dateTime = LocalDateTime.of(LocalDate.parse(DEFAULT_DATE), LocalTime.parse(DEFAULT_TIME));
        cost = DEFAULT_COST;
        quantity = DEFAULT_QUANTITY;
        financeId = DEFAULT_FINANCE_ID;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public InventoryRecordBuilder(InventoryRecord recordToCopy) {
        itemName = recordToCopy.getItemName();
        dateTime = recordToCopy.getDateTime();
        cost = recordToCopy.getUnitCost();
        quantity = recordToCopy.getQuantity();
        financeId = recordToCopy.getFinanceId();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public InventoryRecordBuilder withName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    /**
     * Sets the record's unit cost and returns the record.
     * @param cost updated cost of inventory record.
     * @return Inventory record builder
     */
    public InventoryRecordBuilder withCost(double cost) {
        this.cost = cost;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public InventoryRecordBuilder withDatetime(String dateTime) {
        try {
            this.dateTime = ParserUtil.parseDatetime(dateTime);
        } catch (ParseException e) {
            return this;
        }
        return this;
    }

    public InventoryRecordBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * sets a specific finance ID to the builder
     * @param financeId the finance ID to be set
     * @return the builder with the updated finance ID
     */
    public InventoryRecordBuilder withFinanceId(int financeId) {
        this.financeId = financeId;
        return this;
    }

    public InventoryRecord build() {
        return new InventoryRecord(itemName, quantity, cost, dateTime);
    }

    /**
     * builds with a defined ID
     * @return a inventory record with a specific finance ID
     */
    public InventoryRecord buildWithDefinedFinanceID() {
        return new InventoryRecord(itemName, quantity, cost, dateTime, financeId);
    }
}
