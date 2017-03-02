package excelDataFiles;

import builders.sewerageTax.ConnectionDetailsBuilder;
import entities.sewerageTax.ConnectionDetails;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class SewerageTaxDataReader extends ExcelReader {

    Sheet connectionDetailsSheet;

    public SewerageTaxDataReader(String testData){
        super(testData);
        connectionDetailsSheet = workbook.getSheet("connectionDetails");
    }

    public ConnectionDetails getConnectionDetails(String connectionDetailsDataId){
        Row dataRow = readDataRow(connectionDetailsSheet,connectionDetailsDataId);

        String propertyType = getCellData(connectionDetailsSheet,dataRow,"propertyType").getStringCellValue();
        String numOfClosets = convertNumericToString(connectionDetailsSheet,dataRow,"noOfClosets");
        String documentNum = convertNumericToString(connectionDetailsSheet,dataRow,"documentNumber");

        return new ConnectionDetailsBuilder()
                   .withPropertyType(propertyType)
                   .withNumberOfClosets(numOfClosets)
                   .withDocumentNum(documentNum)
                   .build();
    }
}
