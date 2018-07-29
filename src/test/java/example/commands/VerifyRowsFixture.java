package example.commands;

import example.CubanoDemoFixture;
import org.concordion.api.MultiValueResult;

import java.util.ArrayList;
import java.util.List;

public class VerifyRowsFixture extends CubanoDemoFixture {

    public List<MultiValueResult> verifyActualDataReturned() throws Exception {

        ArrayList<MultiValueResult> result = new ArrayList<MultiValueResult>();

        result.add(new MultiValueResult()
                .with("expectedDataValue1", "Col1Row1")
                .with("expectedDataValue2", "Col2Row1"));

        result.add(new MultiValueResult()
                .with("expectedDataValue1", "Col1Row2")
                .with("expectedDataValue2", "Col2Row2"));

        result.add(new MultiValueResult()
                .with("expectedDataValue1", "Col1Row3")
                .with("expectedDataValue2", "Col2Row3"));

        return result;
    }

}
