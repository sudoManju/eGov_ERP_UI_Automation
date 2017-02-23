package excelDataFiles;

import builders.wcms.ApplicantInfoBuilder;
import builders.wcms.ConnectionInfoBuilder;
import builders.wcms.EnclosedDocumentBuilder;
import builders.wcms.FieldInspectionDetailsBuilder;
import entities.wcms.ApplicantInfo;
import entities.wcms.ConnectionInfo;
import entities.wcms.EnclosedDocument;
import entities.wcms.FieldInspectionDetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class WaterChargesDataReader extends ExcelReader {

    Sheet connectionDetailsSheet;
    Sheet enclosedDocumentsSheet;
    Sheet applicantParticularsSheet;
    Sheet fieldInseptionDetailsForWaterConnectionSheet;

    public WaterChargesDataReader(String testData) {
        super(testData);
        connectionDetailsSheet = workbook.getSheet("connectionDetails");
        enclosedDocumentsSheet = workbook.getSheet("enclosedDocuments");
        applicantParticularsSheet = workbook.getSheet("applicantParticulars");
        fieldInseptionDetailsForWaterConnectionSheet = workbook.getSheet("fieldInseptionDetailsForWaterConnection");
    }

    public ConnectionInfo getConnectionInfo(String connectionDetails){
        Row dataRow = readDataRow(connectionDetailsSheet , connectionDetails);

        Cell waterSourceTypeCell = getCellData(connectionDetailsSheet, dataRow, "waterSourceType");
        waterSourceTypeCell.setCellType(Cell.CELL_TYPE_STRING);
        String waterSourceType = waterSourceTypeCell.getStringCellValue();

        String connectionType = getCellData(connectionDetailsSheet , dataRow ,"connectionType").getStringCellValue();
        String propertyType = getCellData(connectionDetailsSheet , dataRow ,"propertyType").getStringCellValue();
        String category = getCellData(connectionDetailsSheet , dataRow ,"category").getStringCellValue();
        String usageType = getCellData(connectionDetailsSheet , dataRow ,"usageType").getStringCellValue();

        Cell hscPipeSizeCell = getCellData(connectionDetailsSheet, dataRow, "hscPipeSize");
        hscPipeSizeCell.setCellType(Cell.CELL_TYPE_STRING);
        String hscPipeSize = hscPipeSizeCell.getStringCellValue();

        Cell sumpCapacityCell = getCellData(connectionDetailsSheet, dataRow, "sumpCapacity");
        sumpCapacityCell.setCellType(Cell.CELL_TYPE_STRING);
        String sumpCapacity = sumpCapacityCell.getStringCellValue();

        Cell noOfPersonsCell = getCellData(connectionDetailsSheet, dataRow, "noOfPersons");
        noOfPersonsCell.setCellType(Cell.CELL_TYPE_STRING);
        String noOfPersons = noOfPersonsCell.getStringCellValue();

        Cell reasonForConnection = getCellData(connectionDetailsSheet, dataRow, "reasonForAdditionalConn");
        reasonForConnection.setCellType(Cell.CELL_TYPE_STRING);
        String connectionReason = reasonForConnection.getStringCellValue();

        return new ConnectionInfoBuilder()
                .withWaterSourceType(waterSourceType)
                .withConnectionType(connectionType)
                .withPropertyType(propertyType)
                .withCategory(category)
                .withUsageType(usageType)
                .withHSCPipeSize(hscPipeSize)
                .withSumpCapacity(sumpCapacity)
                .withNoOfPersons(noOfPersons)
                .withReasonForAdditionalConnection(connectionReason)
                .build();
    }

    public EnclosedDocument getDocumentInfo(String enclosedDocumentDetails){

        Row dataRow = readDataRow(enclosedDocumentsSheet , enclosedDocumentDetails);

        Cell documentNo1Cell = getCellData(enclosedDocumentsSheet, dataRow, "documentNo1");
        documentNo1Cell.setCellType(Cell.CELL_TYPE_STRING);
        String documentNo1 = documentNo1Cell.getStringCellValue();

        Cell documentNo2Cell = getCellData(enclosedDocumentsSheet, dataRow, "documentNo2");
        documentNo2Cell.setCellType(Cell.CELL_TYPE_STRING);
        String documentNo2 = documentNo2Cell.getStringCellValue();

        Cell documentNo3Cell = getCellData(enclosedDocumentsSheet, dataRow, "documentNo3");
        documentNo3Cell.setCellType(Cell.CELL_TYPE_STRING);
        String documentNo3 = documentNo3Cell.getStringCellValue();

        Cell documentDate1Cell = getCellData(enclosedDocumentsSheet, dataRow, "documentDate1");
        documentDate1Cell.setCellType(Cell.CELL_TYPE_STRING);
        String documentDate1 = documentDate1Cell.getStringCellValue();

        Cell documentDate2Cell = getCellData(enclosedDocumentsSheet, dataRow, "documentDate2");
        documentDate2Cell.setCellType(Cell.CELL_TYPE_STRING);
        String documentDate2 = documentDate2Cell.getStringCellValue();

        Cell documentDate3Cell = getCellData(enclosedDocumentsSheet, dataRow, "documentDate3");
        documentDate3Cell.setCellType(Cell.CELL_TYPE_STRING);
        String documentDate3 = documentDate3Cell.getStringCellValue();

        return new EnclosedDocumentBuilder()
                .withDocumentNo1(documentNo1)
                .withDocumentNo2(documentNo2)
                .withDocumentNo3(documentNo3)
                .withDocumentDate1(documentDate1)
                .withDocumentDate2(documentDate2)
                .withDocumentDate3(documentDate3)
                .build();
    }

    public synchronized ApplicantInfo getApplicantInfo(String applicantDetailsDataId){

        Row dataRow = readDataRow(applicantParticularsSheet, applicantDetailsDataId);

        Cell assessmentNumberCell = getCellData(applicantParticularsSheet, dataRow, "assessmentNumber");
        assessmentNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String assessmentNumber = assessmentNumberCell.getStringCellValue();

        Cell hscNumberCell = getCellData(applicantParticularsSheet, dataRow, "hscNumber");
        hscNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String hscNumber = hscNumberCell.getStringCellValue();

        Cell connectionDateCell = getCellData(applicantParticularsSheet, dataRow, "connectionDate");
        connectionDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String connectionDate = connectionDateCell.getStringCellValue();

        return new ApplicantInfoBuilder()
                .withPTAssessmentNumber(assessmentNumber)
                .withHSCNumber(hscNumber)
                .withConnectionDate(connectionDate).build();
    }

    public FieldInspectionDetails getFieldInspectionInfo(String inspectionInfo){

        Row dataRow = readDataRow(fieldInseptionDetailsForWaterConnectionSheet , inspectionInfo);

        String material =  getCellData(fieldInseptionDetailsForWaterConnectionSheet, dataRow, "material").getStringCellValue();

        Cell quantityCell = getCellData(fieldInseptionDetailsForWaterConnectionSheet, dataRow, "quantity");
        quantityCell.setCellType(Cell.CELL_TYPE_STRING);
        String quantity = quantityCell.getStringCellValue();

        Cell measurementCell = getCellData(fieldInseptionDetailsForWaterConnectionSheet, dataRow, "unitOfMeasurement");
        measurementCell.setCellType(Cell.CELL_TYPE_STRING);
        String measurement = measurementCell.getStringCellValue();

        Cell rateCell = getCellData(fieldInseptionDetailsForWaterConnectionSheet, dataRow, "rate");
        rateCell.setCellType(Cell.CELL_TYPE_STRING);
        String rate = rateCell.getStringCellValue();

        Cell existingPipelineCell = getCellData(fieldInseptionDetailsForWaterConnectionSheet, dataRow, "existingDistributionPipeline");
        existingPipelineCell.setCellType(Cell.CELL_TYPE_STRING);
        String existingPipeline = existingPipelineCell.getStringCellValue();

        Cell pipelineDistanceCell = getCellData(fieldInseptionDetailsForWaterConnectionSheet, dataRow, "pipelineToHomeDistance");
        pipelineDistanceCell.setCellType(Cell.CELL_TYPE_STRING);
        String pipelineDistance = pipelineDistanceCell.getStringCellValue();

        Cell estimationChargesCell = getCellData(fieldInseptionDetailsForWaterConnectionSheet, dataRow, "estimationCharges");
        estimationChargesCell.setCellType(Cell.CELL_TYPE_STRING);
        String estimationCharges = estimationChargesCell.getStringCellValue();

        return new FieldInspectionDetailsBuilder()
                .withMaterial(material)
                .withQuantity(quantity)
                .withUnitOfMeasurement(measurement)
                .withRate(rate)
                .withExistingDistributionPipeLine(existingPipeline)
                .withPipelineToHomeDistance(pipelineDistance)
                .withEstimationCharges(estimationCharges)
                .build();
    }
}
