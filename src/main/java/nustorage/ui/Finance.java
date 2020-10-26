package nustorage.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import nustorage.logic.Logic;
import nustorage.model.record.FinanceRecord;

public class Finance extends UiPart<Region> {

    private static final String FXML = "Finance.fxml";

    @FXML
    private TableView<FinanceRecord> tableView;
    @FXML
    private TableColumn<FinanceRecord, String> idCol;
    @FXML
    private TableColumn<FinanceRecord, String> financeIdCol;
    @FXML
    private TableColumn<FinanceRecord, String> amountCol;
    @FXML
    private TableColumn<FinanceRecord, String> dateAndTimeCol;

    /**
     * Sets the display for the Finance tab in the user interface.
     */
    public Finance(Logic logic) {
        super(FXML);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // to prevent side-scrolling
        tableView.getItems().setAll(parseFinanceList(logic));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateAndTimeCol.setCellValueFactory(new PropertyValueFactory<>("datetime"));
        idCol.setCellValueFactory(id -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(String.valueOf(id.getValue().getUiUsableIndex()));
            return property;
        });
        financeIdCol.setCellValueFactory(id -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(String.valueOf(id.getValue().getID()));
            return property;
        });
    }

    /**
     * Parses the filtered list in model to update the indexes and put it into a list.
     * @param logic Logic
     * @return List of Items.
     */
    private List<FinanceRecord> parseFinanceList(Logic logic) {
        List<FinanceRecord> list = new ArrayList<>();
        for (int i = 0; i < logic.getFilteredFinanceList().size(); i++) {
            logic.getFilteredFinanceList().get(i).setUiUsableIndex(i + 1);
            list.add(logic.getFilteredFinanceList().get(i));
        }
        return list;
    }
}
