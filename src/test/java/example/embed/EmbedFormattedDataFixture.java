package example.embed;

import org.concordion.api.extension.Extensions;
import org.concordion.api.option.ConcordionOptions;
import org.concordion.cubano.template.driver.util.TableBuilder;
import org.concordion.ext.EmbedExtension;

import example.ConcordionFixture;

@Extensions({ EmbedExtension.class })
@ConcordionOptions(declareNamespaces = { "ext", "urn:concordion-extensions:2010" })
public class EmbedFormattedDataFixture extends ConcordionFixture {

    public String embedExample() throws Exception {

        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<html><head><title>").append("My Title" + " - Results</title></head>");
        htmlBuilder.append("<body>");

        htmlBuilder.append("<h3><br>Embedded Tabled Results:</br></h3>");
        addEmbeddedTable(htmlBuilder);

        htmlBuilder.append("<h3><br>Some other data:</br></h3>");
        htmlBuilder.append("Some text describing that data");

        htmlBuilder.append("</body></html>");

        return htmlBuilder.toString();
    }

    private void addEmbeddedTable(StringBuilder htmlBuilder) {
        TableBuilder embedTable = new TableBuilder().withRowSpanSupport(false);

        // Add Header
        embedTable.startHeaderRow();
        embedTable.appendColumnHeader("Header One");
        embedTable.appendColumnHeader("Header Two");
        embedTable.appendColumnHeader("Header Three");
        embedTable.stopHeaderRow();

        int j = 4;
        for (int i = 1; i < j; i++) {
            embedTable.startRow();

            for (int x = 1; x < j; x++) {
                embedTable.appendColumn(String.format("Column Content for Row '%s' and Column '%s'", i, x));

            }

            embedTable.stopRow();
        }

        embedTable.stopTable();

        htmlBuilder.append(embedTable);
    }

}
