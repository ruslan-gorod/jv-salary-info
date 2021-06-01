package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        List<String> reportRecords = new ArrayList<>();
        for (String name : names) {
            double sum = 0;
            for (String value : data) {
                Record record = new Record(value);
                if (name.equals(record.getUserName()) && record.getDate().isAfter(from) && record.getDate().isBefore(to)) {
                    sum += record.getSalary() * record.getCountDays();
                }
            }
            reportRecords.add(name + " - " + sum);
        }
        return createReport(reportRecords, from, to);
    }

    private String createReport(List<String> reportRecords, LocalDate from, LocalDate to) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ");
        stringBuilder.append(from.format(FORMATTER));
        stringBuilder.append(" - ");
        stringBuilder.append(to.format(FORMATTER));
        stringBuilder.append("\n");
        for (String reportRecord : reportRecords) {
            stringBuilder.append(reportRecord).append("\n");
        }
        return stringBuilder.toString();
    }
}
