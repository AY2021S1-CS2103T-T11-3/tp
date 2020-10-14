package nustorage.storage;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import nustorage.commons.exceptions.IllegalValueException;
import nustorage.model.record.FinanceRecord;


/**
 * Jackson-friendly version of {@link FinanceRecord}.
 */
class JsonAdaptedFinanceRecord {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Finance record's %s field is missing!";

    private final int id;
    private final double amount;
    private final LocalDateTime dateTime;


    /**
     * Constructs a {@code JsonAdaptedFinanceRecord} with the given record details.
     */
    @JsonCreator
    public JsonAdaptedFinanceRecord(@JsonProperty("id") int id,
                                    @JsonProperty("amount") double amount,
                                    @JsonProperty("dateTime") LocalDateTime dateTime) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
    }


    /**
     * Converts a given {@code FinanceRecord} into this class for Jackson use.
     */
    @JsonCreator
    public JsonAdaptedFinanceRecord(FinanceRecord source) {
        this.id = source.getID();
        this.amount = source.getAmount();
        this.dateTime = source.getDatetime();
    }


    /**
     * Converts this Jackson-friendly adapted finance record object into the model's {@code FinanceRecord} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public FinanceRecord toModelType() throws IllegalValueException {

        if (this.id < 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "id"));
        }
        final int modelId = this.id;

        if (this.amount < 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "amount"));
        }
        final double modelAmount = this.amount;

        if (this.dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date-time"));
        }
        final LocalDateTime modelDateTime = this.dateTime;

        return new FinanceRecord(modelId, modelAmount, modelDateTime);

    }

}
