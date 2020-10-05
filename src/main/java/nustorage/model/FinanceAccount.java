package nustorage.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nustorage.commons.core.index.Index;
import nustorage.model.record.FinanceRecord;

public class FinanceAccount {

    private final List<FinanceRecord> financeRecords;

    public FinanceAccount() {
        financeRecords = new ArrayList<>();
    }

    public FinanceAccount(List<FinanceRecord> financeRecords) {
        this.financeRecords = financeRecords;
    }

    public List<FinanceRecord> getFinanceRecords() {
        return this.financeRecords;
    }

    public void addRecord(FinanceRecord record) {
        financeRecords.add(record);
    }

    public boolean hasRecord(FinanceRecord record) {
        return this.financeRecords.contains(record);
    }

    /**
     * Removes the finance record with the corresponding index
     *
     * @param targetIndex Index of finance record to be removed
     * @return Optional containing removed finance record if index is valid, else an empty optional
     */
    public Optional<FinanceRecord> removeRecord(Index targetIndex) {

        if (targetIndex.getZeroBased() >= financeRecords.size()) {
            return Optional.empty();
        }

        return Optional.of(financeRecords.remove(targetIndex.getZeroBased()));
    }

    public int count() {
        return financeRecords.size();
    }

    /**
     * Returns the net transaction amount of all finance records
     *
     * @return Net transaction amount of all finance records
     */
    public double netProfit() {
        return financeRecords.stream()
                .mapToDouble(FinanceRecord::getAmount)
                .sum();
    }

    public List<FinanceRecord> filterRecords (Predicate<FinanceRecord> filter) {
        return financeRecords.stream().filter(filter).collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return financeRecords.isEmpty();
    }

    @Override
    public String toString() {
        return financeRecords.stream()
                .map(FinanceRecord::toString)
                .collect(Collectors.joining("\n"));
    }
}
