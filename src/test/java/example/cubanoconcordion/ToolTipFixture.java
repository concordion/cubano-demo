package example.cubanoconcordion;

import example.ConcordionFixture;

public class ToolTipFixture extends ConcordionFixture {

    public boolean addToolTipLogging() {

        addConcordionTooltip("Look at me, just hovering over here!");
        return true;
    }

}
