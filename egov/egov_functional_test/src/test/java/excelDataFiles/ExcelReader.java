package excelDataFiles;

import builders.ApprovalDetailsEntityBuilder;
import builders.LoginDetailsBuilder;
import builders.collections.ChallanHeaderDetailsBuilder;
import builders.collections.ChequeDetailsBuilder;
import builders.collections.PaymentMethodBuilder;
import builders.councilManagement.PreambleDetailsBuilder;
import builders.dcReports.PTReportBuilder;
import builders.dcReports.VLTReportBuilder;
import builders.financial.*;
import builders.grievances.CreateComplaintDetailsBuilder;
import builders.marriageRegistration.MarriageRegistrationBuilder;
import builders.ptis.*;
import builders.tradeLicense.*;
import builders.wcms.EnclosedDocumentBuilder;
import builders.wcms.FieldInspectionDetailsBuilder;
import builders.works.*;
import entities.*;
import entities.collections.ChallanHeaderDetails;
import entities.collections.ChequeDetails;
import entities.collections.PaymentMethod;
import entities.councilManagement.CreatePreambleDetails;
import entities.dcReports.PTReport;
import entities.dcReports.VLTReport;
import entities.financial.*;
import entities.grievances.CreateComplaintDetails;
import entities.marriageRegistration.MarriageRegistrationInformation;
import entities.ptis.*;
import entities.tradeLicense.*;
import entities.tradeLicense.SearchTradeDetails;
import entities.wcms.EnclosedDocument;
import entities.wcms.FieldInspectionDetails;
import entities.works.*;
import entities.ApprovalDetailsEntity;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


public class ExcelReader {

    Workbook workbook;

    Sheet registeredUserSheet;

    //     Trade License Sheets
    Sheet tradeOwnerDetailsSheet;
    Sheet tradeLocationDetailsSheet;
    Sheet tradeDetailsSheet;
    Sheet legencyDetailsSheet;
    Sheet licenseClosureSheet;

    Sheet searchTradeDetailsSheet;
    Sheet approvalDetailsSheet;
    Sheet approverDetailsSheet;

    public ExcelReader(String testData) {
        String excelFilePath = testData + ".xlsx";
//        System.out.println(excelFilePath);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(excelFilePath);
        try {
            workbook = WorkbookFactory.create(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        registeredUserSheet = workbook.getSheet("registeredUserDetails");
        approverDetailsSheet = workbook.getSheet("approvalDetails");

        //        Trade License Sheet Names
        tradeOwnerDetailsSheet = workbook.getSheet("tradeOwnerDetails");
        tradeLocationDetailsSheet =workbook.getSheet("tradeLocationDetails");
        tradeDetailsSheet = workbook.getSheet("tradeDetails");
        licenseClosureSheet = workbook.getSheet("licenseClosure");
        searchTradeDetailsSheet = workbook.getSheet("searchTradeDeatils");
        legencyDetailsSheet = workbook.getSheet("legencyDetails");
        approvalDetailsSheet = workbook.getSheet("approvalDetails");

    }

    protected Row readDataRow(Sheet fromSheet, String dataId) {
        Iterator<Row> rowIterator = fromSheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(0).getStringCellValue().equalsIgnoreCase(dataId))
                return row;
        }
        throw new RuntimeException("No data found with this identifier: " + dataId);
    }

    protected Cell getCellData(Sheet fromSheet, Row dataRow, String header) {
        return dataRow.getCell(getCellNumberWithHeader(fromSheet, header), Row.CREATE_NULL_AS_BLANK);
    }

    protected int getCellNumberWithHeader(Sheet sheet, String header) {

        int physicalNumberOfCells = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int cellNumber = 0; cellNumber < physicalNumberOfCells; cellNumber++) {
            if (sheet.getRow(0).getCell(cellNumber).toString().equalsIgnoreCase(header))
                return cellNumber;
        }

        throw new RuntimeException("No cell found for header: " + header);
    }


    private String getValueFromExcel(Sheet firstSheet, int rowIndex, int columnIndex) {
        Cell cell = firstSheet.getRow(rowIndex).getCell(columnIndex);
        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return "";
        }
        if (isDateCell(firstSheet, columnIndex) && rowIndex > 0) {
            return cell.getDateCellValue().toString();
        }
        return cell.toString();
    }

    private boolean isDateCell(Sheet firstSheet, int columnIndex) {
        return firstSheet.getRow(0).getCell(columnIndex).toString().contains("Timestamp") ||
                firstSheet.getRow(0).getCell(columnIndex).toString().contains("current time") ||
                firstSheet.getRow(0).getCell(columnIndex).toString().contains("current date");
    }


    public LoginDetails getLoginDetails(String loggedInUserDataId) {
        Row dataRow = readDataRow(registeredUserSheet, loggedInUserDataId);
        Cell idCell = getCellData(registeredUserSheet, dataRow, "id");
        idCell.setCellType(Cell.CELL_TYPE_STRING);
        String id = idCell.getStringCellValue();
        String password = getCellData(registeredUserSheet, dataRow, "password").getStringCellValue();
        boolean hasZone = getCellData(registeredUserSheet, dataRow, "hasZone").getBooleanCellValue();


        return new LoginDetailsBuilder().withLoginId(id).withPassword(password)
                .withHasZone(hasZone).build();

    }



    //end of works management module line estimate

    public ApprovalDetails getApprovalDetails(String approvalDetailsDataId) {
        Row dataRow = readDataRow(approvalDetailsSheet, approvalDetailsDataId);
        String approverDepartment = getCellData(approvalDetailsSheet, dataRow, "approverDepartment").getStringCellValue();
        String approverDesignation = getCellData(approvalDetailsSheet, dataRow, "approverDesignation").getStringCellValue();
        String approver = getCellData(approvalDetailsSheet, dataRow, "approver").getStringCellValue();
        String approverRemarks = getCellData(approvalDetailsSheet, dataRow, "approverRemarks").getStringCellValue();


        return new ApprovalDetailsBuilder()
                .withApproverDepartment(approverDepartment)
                .withApproverDesignation(approverDesignation)
                .withApprover(approver)
                .withApproverRemarks(approverRemarks)
                .build();
    }

    public ApprovalDetailsEntity getApprovalDetailsForGrievance(String approvalDetailsDataId) {
        Row dataRow = readDataRow(approvalDetailsSheet, approvalDetailsDataId);
        String approverDepartment = getCellData(approvalDetailsSheet, dataRow, "approverDepartment").getStringCellValue();
        String approverDesignation = getCellData(approvalDetailsSheet, dataRow, "approverDesignation").getStringCellValue();
        String approver = getCellData(approvalDetailsSheet, dataRow, "approver").getStringCellValue();
        String approverRemarks = getCellData(approvalDetailsSheet, dataRow, "approverRemarks").getStringCellValue();
        return new ApprovalDetailsEntityBuilder()
                .withApproverDepartment(approverDepartment)
                .withApproverDesignation(approverDesignation)
                .withApprover(approver)
                .withApproverRemarks(approverRemarks)
                .build();
    }

    public ApprovalDetails getApprovalDetailsOfChallan(String approverId) {
        Row dataRow = readDataRow(approvalDetailsSheet, approverId);
        String approverDepartment = getCellData(approvalDetailsSheet, dataRow, "approverDepartment").getStringCellValue();
        String approverDesignation = getCellData(approvalDetailsSheet, dataRow, "approverDesignation").getStringCellValue();
        String approver = getCellData(approvalDetailsSheet, dataRow, "approver").getStringCellValue();

        return new ApprovalDetailsBuilder()
                .withApproverDepartment(approverDepartment)
                .withApproverDesignation(approverDesignation)
                .withApprover(approver)
                .build();
    }


    public TradeOwnerDetails getTradeOwnerDetails(String tradeOwnerDetailsDataId){
        Row dataRow = readDataRow(tradeOwnerDetailsSheet, tradeOwnerDetailsDataId);
        Cell aadhaarNumberCell = getCellData(tradeOwnerDetailsSheet, dataRow, "aadhaarNumber");
        aadhaarNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String  aadhaarNumber = aadhaarNumberCell.getStringCellValue();

        Cell mobileNumberCell = getCellData(tradeOwnerDetailsSheet, dataRow, "mobileNumber");
        mobileNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String  mobileNumber = mobileNumberCell.getStringCellValue();

        Cell tradeOwnerNameCell = getCellData(tradeOwnerDetailsSheet, dataRow, "tradeOwnerName");
        tradeOwnerNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String tradeOwnerName = tradeOwnerNameCell.getStringCellValue();

        Cell fatherSpouseNameCell = getCellData(tradeOwnerDetailsSheet, dataRow, "fatherSpouseName");
        fatherSpouseNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String fatherSpouseName = fatherSpouseNameCell.getStringCellValue();

        Cell emailIdCell = getCellData(tradeOwnerDetailsSheet, dataRow, "emailId");
        emailIdCell.setCellType(Cell.CELL_TYPE_STRING);
        String emailId = emailIdCell.getStringCellValue();

        Cell tradeOwnerAddressCell = getCellData(tradeOwnerDetailsSheet, dataRow, "tradeOwnerAddress");
        tradeOwnerAddressCell.setCellType(Cell.CELL_TYPE_STRING);
        String tradeOwnerAddress = tradeOwnerAddressCell.getStringCellValue();


        return new TradeOwnerDetailsBuilder()
                    .withAadhaarNumber(aadhaarNumber)
                    .withMobileNumber(mobileNumber)
                    .withTradeOwnerName(tradeOwnerName)
                    .withFatherSpouseName(fatherSpouseName)
                    .withEmailId(emailId)
                    .withTradeOwnerAddress(tradeOwnerAddress)
                    .build();
    }

    public TradeLocationDetails getTradeLocationDetails(String tradeLocationDetailsDataId) {
        Row dataRow = readDataRow(tradeLocationDetailsSheet, tradeLocationDetailsDataId);

        String propertyAssessmentDetails = getCellData(tradeLocationDetailsSheet, dataRow, "propertyAssessmentDetails").getStringCellValue();
        String ownershipType = getCellData(tradeLocationDetailsSheet, dataRow, "ownershipType").getStringCellValue();
        String locality = getCellData(tradeLocationDetailsSheet, dataRow,"locality").getStringCellValue();
        String ward = getCellData(tradeLocationDetailsSheet,dataRow,"ward").getStringCellValue();

        return new TradeLocationDetailsBuilder()
                .withpropertyAssessmentNumber(propertyAssessmentDetails)
                .withownershipType(ownershipType)
                .withLocality(locality)
                .withWard(ward)
                .build();
    }

    public TradeDetails getTradeDetails(String tradeDetailsData) {
        Row dataRow = readDataRow(tradeDetailsSheet, tradeDetailsData);

        String tradeTitle = getCellData(tradeDetailsSheet, dataRow, "tradeTitle").getStringCellValue();

        String tradeType = getCellData(tradeDetailsSheet, dataRow, "tradeType").getStringCellValue();

        String tradeCategory = getCellData(tradeDetailsSheet, dataRow, "tradeCategory").getStringCellValue();

        String tradeSubCategory = getCellData(tradeDetailsSheet, dataRow, "tradeSubCategory").getStringCellValue();

        Cell tradeAreaWeightOfPremisesCell = getCellData(tradeDetailsSheet, dataRow, "tradeAreaWeightOfPremises");
        tradeAreaWeightOfPremisesCell.setCellType(Cell.CELL_TYPE_STRING);
        String tradeAreaWeightOfPremises = tradeAreaWeightOfPremisesCell.getStringCellValue();

        String remarks = getCellData(tradeDetailsSheet, dataRow, "remarks").getStringCellValue();

        Cell tradeCommencementDateCell = getCellData(tradeDetailsSheet, dataRow, "TradeCommencementDate");
        tradeCommencementDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String tradeCommencementDate = tradeCommencementDateCell.getStringCellValue();

        return new TradeDetailsBuilder()
                .withtradeTitle(tradeTitle)
                .withtradeType(tradeType)
                .withtradeCategory(tradeCategory)
                .withtradeSubCategory(tradeSubCategory)
                .withtradeAreaWeightOfPremises(tradeAreaWeightOfPremises)
                .withremarks(remarks)
                .withtradeCommencementDate(tradeCommencementDate)
                .build();
    }

    public ApproverDetails getApprovalDetailsForEstimate(String approverDetailsDataId) {
        Row dataRow = readDataRow(approverDetailsSheet, approverDetailsDataId);

        String approverDept = getCellData(approverDetailsSheet, dataRow, "approverDepartment").getStringCellValue();
        String approverDesig = getCellData(approverDetailsSheet, dataRow, "approverDesignation").getStringCellValue();
        String approver = getCellData(approverDetailsSheet, dataRow, "approver").getStringCellValue();
        String comment = getCellData(approverDetailsSheet, dataRow, "approverRemarks").getStringCellValue();

        return new ApproverDetailsBuilder()
                .withApproverDepartment(approverDept)
                .withApproverDesignation(approverDesig)
                .withApprover(approver)
                .withApproverComment(comment)
                .build();
    }



    public LicenseClosureDetails getDetailsForClosure(String closureData) {
       Row dataRow = readDataRow(licenseClosureSheet,closureData);

       Cell statusCell = getCellData(licenseClosureSheet, dataRow, "status");
       statusCell.setCellType(Cell.CELL_TYPE_STRING);
       String status=statusCell.getStringCellValue();

       Cell tradeCategoryCell = getCellData(licenseClosureSheet, dataRow, "tradeCategory");
       tradeCategoryCell.setCellType(Cell.CELL_TYPE_STRING);
       String tradeCategory=tradeCategoryCell.getStringCellValue();

       return new LicenseClosureDetailsBuilder()
               .withStatusDetails(status)
               .withTradeCategory(tradeCategory)
               .build();
    }

    public SearchTradeDetails getTradeSearchDetails(String searchId) {
        Row dataRow = readDataRow(searchTradeDetailsSheet, searchId);
        SearchTradeDetails searchTradeDetails = new SearchTradeDetails();

        switch (searchId) {
            case "searchWithApplicationNumber":
                String applicationNumber = getCellData(searchTradeDetailsSheet, dataRow, "searchValue").getStringCellValue();

                searchTradeDetails = new SearchTradeDetailsBuilder()
                        .withApplicationNumber(applicationNumber)
                        .build();
                break;

            case "searchWithLicenseNumber":

                String licenseNumber= getCellData(searchTradeDetailsSheet, dataRow, "searchValue").getStringCellValue();

                searchTradeDetails = new SearchTradeDetailsBuilder()
                        .withLicenseNumber(licenseNumber)
                        .build();
                break;
        }
        return searchTradeDetails;
    }
}
