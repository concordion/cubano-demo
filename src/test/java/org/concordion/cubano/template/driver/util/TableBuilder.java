package org.concordion.cubano.template.driver.util;

public class TableBuilder {
    private static final String ROWSPAN_MARKER = "ROWSPAN";

    private StringBuilder table = new StringBuilder();
    private int rowCount = 0;
    private int rowspanCount = 0;
    private boolean useRowSpan = false;

    public TableBuilder() {
        this(true);
    }

    public TableBuilder(boolean addStyle) {
        if (addStyle) {
            appendStyle(table);
        }

        table.append("<table>");
        table.append(System.lineSeparator());
    }

    public TableBuilder withRowSpanSupport(boolean rowspanSupport) {
        useRowSpan = rowspanSupport;
        return this;
    }

    private void appendStyle(StringBuilder table) {
        table.append("<style>");

        table.append("tr.even { white-space: nowrap; background: white; }");
        table.append("tr.even:hover { background: lightgoldenrodyellow; }");

        table.append("tr.odd { white-space: nowrap; background: lightgrey; }");
        table.append("tr.odd:hover { background: lightgoldenrodyellow; }");

        table.append("th {padding: 8px; background-color: #C3D9FF;}");
        table.append("td {padding: 8px; vertical-align: middle; background-color: inherit; }");

        table.append(".nobreak {white-space: nowrap;}");
        table.append(".rowheader {text-align: left; font-weight: bold;}");

        table.append("th, td { border: 1px solid black; padding: 3px;}");
        table.append("table { border-collapse: collapse; empty-cells: show; margin: 8px 0px 8px 0px;}");

        table.append("</style>");
        table.append(System.lineSeparator());
    }

    public void startHeaderRow() {
        table.append("<tr>");
        table.append(System.lineSeparator());
    }

    public void appendColumnHeader(String header) {
        table.append("\t<th>").append(header).append("</th>");
        table.append(System.lineSeparator());
    }

    public void stopHeaderRow() {
        table.append("</tr>");
        table.append(System.lineSeparator());
    }

    public void addPre(String formattedText) {
        table.append("<PRE>");
        table.append(formattedText);
        table.append("</PRE>");
        table.append(System.lineSeparator());
    }

    public void startRow() {
        startRow(false);
    }

    public void startRow(boolean partOfRowSpan) {
        rowspanCount++;

        if (!partOfRowSpan) {
            rowCount++;
        }

        boolean odd = (rowCount & 1) == 1;
        table.append("<tr class=\"").append(odd ? "odd" : "even").append("\">");
        table.append(System.lineSeparator());
    }

    public void appendRowHeader(String header) {
        table.append("\t<th class=\"rowheader\"");
        if (useRowSpan) {
            table.append(" ").append(ROWSPAN_MARKER);
        }

        table.append(">").append(header).append("</th>");
        table.append(System.lineSeparator());
    }

    public void appendColumn(String value) {
        table.append("\t<td>").append(value).append("</td>");
        table.append(System.lineSeparator());
    }

    public void appendColumn(String columnProperties, String value) {
        String spacer = "";
        if (columnProperties != null && !columnProperties.isEmpty()) {
            spacer = " ";
        }

        table.append("\t<td").append(spacer).append(columnProperties).append(">").append(value).append("</td>");
        table.append(System.lineSeparator());
    }

    public void updateRowSpanMarker() {
        if (rowCount > 1) {
            if (rowspanCount > 1) {
                replaceAll(table, "ROWSPAN", "rowspan=\"" + rowspanCount + "\"");
            } else {
                replaceAll(table, "ROWSPAN", "");
            }
        }

        rowspanCount = 0;
    }

    public void stopRow() {
        if (rowCount > 0) {
            table.append("</tr>");
            table.append(System.lineSeparator());
        }
    }

    public void stopTable() {
        table.append("</table>");
        table.append(System.lineSeparator());
    }

    private void replaceAll(StringBuilder builder, String from, String to) {
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length();
            index = builder.indexOf(from, index);
        }
    }

    @Override
    public String toString() {
        return table.toString();
    }
}
