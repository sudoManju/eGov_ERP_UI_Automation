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

//    Property Tax Sheets
    Sheet registrationDetailsSheet;


    //  Council Maanagement Sheets
    Sheet enclosedDocumentSheet;
    Sheet chequeDetailsSheet;
    Sheet applicantParticularsSheet;
    Sheet connectionDetailsSheet;
    Sheet enclosedDocumentsSheet;
    Sheet registeredUserDetailsSheet;


    //     Trade License Sheets
    Sheet tradeOwnerDetailsSheet;
    Sheet tradeLocationDetailsSheet;
    Sheet tradeDetailsSheet;
    Sheet legencyDetailsSheet;
    Sheet licenseClosureSheet;

    Sheet searchTradeDetailsSheet;



    Sheet dataFromWebSheet;
    Sheet approvalDetailsSheet;


    Sheet approverDetailsSheet;

//    Marriage Registration
    Sheet applicantsInformationSheet;
    Sheet bridegroomInformationSheet;




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

//      Property Tax Sheet Names

        chequeDetailsSheet = workbook.getSheet("chequeDetails");

        enclosedDocumentSheet = workbook.getSheet("enclosedDocumentsDetails");
        registeredUserSheet = workbook.getSheet("registeredUserDetails");



        registeredUserDetailsSheet = workbook.getSheet("registeredUserDetails");
        approverDetailsSheet = workbook.getSheet("approvalDetails");


        //        Trade License Sheet Names
        tradeOwnerDetailsSheet = workbook.getSheet("tradeOwnerDetails");
        tradeLocationDetailsSheet =workbook.getSheet("tradeLocationDetails");
        tradeDetailsSheet = workbook.getSheet("tradeDetails");
        licenseClosureSheet = workbook.getSheet("licenseClosure");
        searchTradeDetailsSheet = workbook.getSheet("searchTradeDeatils");
        legencyDetailsSheet = workbook.getSheet("legencyDetails");
        approvalDetailsSheet = workbook.getSheet("approvalDetails");


        //        Council management Sheets


        dataFromWebSheet = workbook.getSheet("dataFromWeb");

//        Grievances sheets

//        Marriage Registration
        applicantsInformationSheet = workbook.getSheet("applicantsInformation");
        bridegroomInformationSheet = workbook.getSheet("bridegroomInformation");


    }

    protected Row readDataRow(Sheet fromSheet, String dataId) {
        Iterator<Row> rowIterator = fromSheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(0).getStringCellValue().equalsIgnoreCase(dataId))
//            if (row.getCell(0).getStringCellValue().equals(dataId))
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

    public ChequeDetails getChequeDetails(String chequeDetailsDataId) {
        Row dataRow = readDataRow(chequeDetailsSheet, chequeDetailsDataId);
        Cell chequeNumberCell = getCellData(chequeDetailsSheet, dataRow, "chequeNumber");
        chequeNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String chequeNumber = chequeNumberCell.getStringCellValue();
        Cell bankNameCell = getCellData(chequeDetailsSheet, dataRow, "bankName");
        bankNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String bankName = bankNameCell.getStringCellValue();
        String paidBy = getCellData(chequeDetailsSheet, dataRow, "paidBy").getStringCellValue();

        return new ChequeDetailsBuilder()
                .withChequeNumber(chequeNumber)
                .withBankName(bankName)
                .withPaidBy(paidBy)
                .withChequeDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date())).build();
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

   public RegistrationDetails getRegistrationDetails(String registrationDetailsDataId){
       Row dataRow = readDataRow(registrationDetailsSheet, registrationDetailsDataId);
       Cell sellerExecutantNameCell = getCellData(registrationDetailsSheet, dataRow, "sellerExecutantName");
       sellerExecutantNameCell.setCellType(Cell.CELL_TYPE_STRING);
       String sellerExecutantName = sellerExecutantNameCell.getStringCellValue();

       Cell buyerClaimantNameCell = getCellData(registrationDetailsSheet, dataRow, "buyerClaimantName");
       buyerClaimantNameCell.setCellType(Cell.CELL_TYPE_STRING);
       String  buyerClaimantName = buyerClaimantNameCell.getStringCellValue();

       Cell doorNoCell = getCellData(registrationDetailsSheet, dataRow, "doorNo");
       doorNoCell.setCellType(Cell.CELL_TYPE_STRING);
       String  doorNo = doorNoCell.getStringCellValue();

       Cell propertyAddressCell = getCellData(registrationDetailsSheet, dataRow, "propertyAddress");
       propertyAddressCell.setCellType(Cell.CELL_TYPE_STRING);
       String  propertyAddress = propertyAddressCell.getStringCellValue();

       Cell registeredPlotAreaCell = getCellData(registrationDetailsSheet, dataRow, "registeredPlotArea");
       registeredPlotAreaCell.setCellType(Cell.CELL_TYPE_STRING);
       String  registeredPlotArea = registeredPlotAreaCell.getStringCellValue();

       Cell registeredPlinthAreaCell = getCellData(registrationDetailsSheet, dataRow, "registerPlinthArea");
       registeredPlinthAreaCell.setCellType(Cell.CELL_TYPE_STRING);
       String  registeredPlinthArea = registeredPlinthAreaCell.getStringCellValue();

       Cell eastBoundaryCell = getCellData(registrationDetailsSheet, dataRow, "eastBoundary");
       eastBoundaryCell.setCellType(Cell.CELL_TYPE_STRING);
       String  eastBoundary = eastBoundaryCell.getStringCellValue();

       Cell westBoundaryCell = getCellData(registrationDetailsSheet, dataRow, "westBoundary");
       westBoundaryCell.setCellType(Cell.CELL_TYPE_STRING);
       String  westBoundary = westBoundaryCell.getStringCellValue();

       Cell northBoundaryCell = getCellData(registrationDetailsSheet, dataRow, "northBoundary");
       northBoundaryCell.setCellType(Cell.CELL_TYPE_STRING);
       String  northBoundary = northBoundaryCell.getStringCellValue();

       Cell southBoundaryCell = getCellData(registrationDetailsSheet, dataRow, "southBoundary");
       southBoundaryCell.setCellType(Cell.CELL_TYPE_STRING);
       String  southBoundary = southBoundaryCell.getStringCellValue();

       Cell sroNameCell = getCellData(registrationDetailsSheet, dataRow, "sroName");
       sroNameCell.setCellType(Cell.CELL_TYPE_STRING);
       String  sroName = sroNameCell.getStringCellValue();

       Cell reasonForChangeCell = getCellData(registrationDetailsSheet, dataRow, "reasonForChange");
       reasonForChangeCell.setCellType(Cell.CELL_TYPE_STRING);
       String  reasonForChange = reasonForChangeCell.getStringCellValue();

       Cell registrationDocumentNumberCell = getCellData(registrationDetailsSheet, dataRow, "registrationDocumentNumber");
       registrationDocumentNumberCell.setCellType(Cell.CELL_TYPE_STRING);
       String  registrationDocumentNumber = registrationDocumentNumberCell.getStringCellValue();

       Cell registrationDocumentDateCell = getCellData(registrationDetailsSheet, dataRow, "registrationDocumentDate");
       registrationDocumentDateCell.setCellType(Cell.CELL_TYPE_STRING);
       String  registrationDocumentDate = registrationDocumentDateCell.getStringCellValue();

       Cell partiesConsiderationValueCell = getCellData(registrationDetailsSheet, dataRow, "partiesConsiderationValue");
       partiesConsiderationValueCell.setCellType(Cell.CELL_TYPE_STRING);
       String  partiesConsiderationValue = partiesConsiderationValueCell.getStringCellValue();

       Cell departmentGuidelinesValueCell = getCellData(registrationDetailsSheet, dataRow, "departmentGuide");
       departmentGuidelinesValueCell.setCellType(Cell.CELL_TYPE_STRING);
       String  departmentGuidelinesValue = departmentGuidelinesValueCell.getStringCellValue();



       return new RegistrationDetailsBuilder()
               .withSellerExecutantName(sellerExecutantName)
               .withBuyerClaimantName(buyerClaimantName)
               .withDoorNo(doorNo)
               .withPropertyAddress(propertyAddress)
               .withRegisteredPlotArea(registeredPlotArea)
               .withRegisteredPlinthArea(registeredPlinthArea)
               .withEastBoundary(eastBoundary)
               .withWestBoundary(westBoundary)
               .withNorthBoundary(northBoundary)
               .withSouthBoundary(southBoundary)
               .withSroName(sroName)
               .withReasonForChange(reasonForChange)
               .withRegistrationDocumentNumber(registrationDocumentNumber)
               .withRegistrationDocumentDate(registrationDocumentDate)
               .withPartiesConsiderationValue(partiesConsiderationValue)
               .withdePartmentGuidelinesValue(departmentGuidelinesValue)
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

    public MarriageRegistrationInformation getApplicantsInformation(String applicantsInformation)
    {
        Row dataRow = readDataRow(applicantsInformationSheet, applicantsInformation);

        Cell registrationUnitCell = getCellData(applicantsInformationSheet, dataRow, "RegistrationUnit");
        registrationUnitCell.setCellType(Cell.CELL_TYPE_STRING);
        String RegistrationUnit = registrationUnitCell.getStringCellValue();

        Cell streetCell = getCellData(applicantsInformationSheet, dataRow, "Street");
        streetCell.setCellType(Cell.CELL_TYPE_STRING);
        String Street = streetCell.getStringCellValue();

        Cell localityCell = getCellData(applicantsInformationSheet, dataRow, "Locality");
        localityCell.setCellType(Cell.CELL_TYPE_STRING);
        String Locality = localityCell.getStringCellValue();

        Cell cityCell = getCellData(applicantsInformationSheet, dataRow, "City");
        cityCell.setCellType(Cell.CELL_TYPE_STRING);
        String City = cityCell.getStringCellValue();

        Cell venueOfMarriageCell = getCellData(applicantsInformationSheet, dataRow, "VenueOfMarriage");
        venueOfMarriageCell.setCellType(Cell.CELL_TYPE_STRING);
        String VenueOfMarriage = venueOfMarriageCell.getStringCellValue();

        Cell placeOfMarriageCell = getCellData(applicantsInformationSheet, dataRow, "PlaceOfMarriage");
        placeOfMarriageCell.setCellType(Cell.CELL_TYPE_STRING);
        String PlaceOfMarriage = placeOfMarriageCell.getStringCellValue();

        return new MarriageRegistrationBuilder()
                .withRegistrationUnit(RegistrationUnit)
                .withStreet(Street).withLocality(Locality)
                .withCity(City).withVenueOfMarriage(VenueOfMarriage).withPlaceOfMarriage(PlaceOfMarriage)
                .build();
    }

    public MarriageRegistrationInformation getBrideGroomInformation(String brideGroomInformation) {
        Row dataRow = readDataRow(bridegroomInformationSheet, brideGroomInformation);

        Cell FullNameCell = getCellData(bridegroomInformationSheet, dataRow, "FullName");
        FullNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String FullName = FullNameCell.getStringCellValue();

        Cell FathersMothersNameCell = getCellData(bridegroomInformationSheet, dataRow, "FathersMothersName");
        FathersMothersNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String FathersMothersName = FathersMothersNameCell.getStringCellValue();

        Cell ReligionCell = getCellData(bridegroomInformationSheet, dataRow, "Religion");
        ReligionCell.setCellType(Cell.CELL_TYPE_STRING);
        String Religion = ReligionCell.getStringCellValue();

        Cell StatusAtTheTimeMarriageCell = getCellData(bridegroomInformationSheet, dataRow, "StatusAtTheTimeMarriage");
        StatusAtTheTimeMarriageCell.setCellType(Cell.CELL_TYPE_STRING);
        String StatusAtTheTimeMarriage = StatusAtTheTimeMarriageCell.getStringCellValue();

        Cell streetCell = getCellData(bridegroomInformationSheet, dataRow, "Street");
        streetCell.setCellType(Cell.CELL_TYPE_STRING);
        String Street = streetCell.getStringCellValue();

        Cell localityCell = getCellData(bridegroomInformationSheet, dataRow, "Locality");
        localityCell.setCellType(Cell.CELL_TYPE_STRING);
        String Locality = localityCell.getStringCellValue();

        Cell cityCell = getCellData(bridegroomInformationSheet, dataRow, "City");
        cityCell.setCellType(Cell.CELL_TYPE_STRING);
        String City = cityCell.getStringCellValue();

        Cell ResidenceAddressCell = getCellData(bridegroomInformationSheet, dataRow, "ResidenceAddress");
        ResidenceAddressCell.setCellType(Cell.CELL_TYPE_STRING);
        String ResidenceAddress = ResidenceAddressCell.getStringCellValue();

        Cell OfficeAddressCell = getCellData(bridegroomInformationSheet, dataRow, "OfficeAddress");
        OfficeAddressCell.setCellType(Cell.CELL_TYPE_STRING);
        String OfficeAddress = OfficeAddressCell.getStringCellValue();

        Cell phoneNoCell = getCellData(bridegroomInformationSheet, dataRow, "PhoneNo");
        phoneNoCell.setCellType(Cell.CELL_TYPE_STRING);
        String PhoneNo = phoneNoCell.getStringCellValue();

        Cell OccupationCell = getCellData(bridegroomInformationSheet, dataRow, "Occupation");
        OccupationCell.setCellType(Cell.CELL_TYPE_STRING);
        String Occupation = OccupationCell.getStringCellValue();

        Cell educationQualificationCell = getCellData(bridegroomInformationSheet, dataRow, "EducationQualification");
        educationQualificationCell.setCellType(Cell.CELL_TYPE_STRING);
        String EducationQualification = educationQualificationCell.getStringCellValue();

        Cell NationalityCell = getCellData(bridegroomInformationSheet, dataRow, "Nationality");
        NationalityCell.setCellType(Cell.CELL_TYPE_STRING);
        String Nationality = NationalityCell.getStringCellValue();

        Row dataRow2 = readDataRow(bridegroomInformationSheet, brideGroomInformation);
        Cell fullNameCell = getCellData(bridegroomInformationSheet, dataRow2,"FullName");
        fullNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String fullName= fullNameCell.getStringCellValue();

        Cell fathersMothersNameCell = getCellData(bridegroomInformationSheet, dataRow2,"FathersMothersName");
        fathersMothersNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String fathersMothersName= fathersMothersNameCell.getStringCellValue();

        Cell religionCell = getCellData(bridegroomInformationSheet, dataRow2,"Religion");
        religionCell.setCellType(Cell.CELL_TYPE_STRING);
        String religion= religionCell.getStringCellValue();

        //        Cell StatusAtTheTimeMarriageCell = getCellData(bridegroomInformationSheet, dataRow2,"StatusAtTheTimeMarriage");
//        StatusAtTheTimeMarriageCell.setCellType(Cell.CELL_TYPE_STRING);
//        String StatusAtTheTimeMarriage= StatusAtTheTimeMarriageCell.getStringCellValue();
//
//        Cell ResidenceAddressCell = getCellData(bridegroomInformationSheet, dataRow2,"ResidenceAddress");
//        ResidenceAddressCell.setCellType(Cell.CELL_TYPE_STRING);
//        String ResidenceAddress= ResidenceAddressCell.getStringCellValue();
//
//        Cell OfficeAddressCell = getCellData(bridegroomInformationSheet, dataRow2,"OfficeAddress");
//        OfficeAddressCell.setCellType(Cell.CELL_TYPE_STRING);
//        String OfficeAddress= OfficeAddressCell.getStringCellValue();
//
//        Cell phoneNoCell = getCellData(bridegroomInformationSheet, dataRow2,"PhoneNo");
//        phoneNoCell.setCellType(Cell.CELL_TYPE_STRING);
//        String PhoneNo= phoneNoCell.getStringCellValue();
//
//        Cell OccupationCell = getCellData(bridegroomInformationSheet, dataRow2,"Occupation");
//        OccupationCell.setCellType(Cell.CELL_TYPE_STRING);
//        String Occupation= OccupationCell.getStringCellValue();
//
//        Cell educationQualificationCell = getCellData(bridegroomInformationSheet, dataRow2,"EducationQualification");
//        educationQualificationCell.setCellType(Cell.CELL_TYPE_STRING);
//        String EducationQualification= educationQualificationCell.getStringCellValue();
//
//        Cell NationalityCell = getCellData(bridegroomInformationSheet, dataRow2,"Nationality");
//        NationalityCell.setCellType(Cell.CELL_TYPE_STRING);
//        String Nationality= NationalityCell.getStringCellValue();

        return new MarriageRegistrationBuilder()
                .withFullName(FullName).withFathersMothersName(FathersMothersName).withReligion(Religion)
                .withStatusAtTheTimeMarriage(StatusAtTheTimeMarriage).withResidenceAddress(ResidenceAddress)
                .withStreet(Street).withLocality(Locality).withCity(City)
                .withOfficeAddress(OfficeAddress).withPhoneNo(PhoneNo).withOccupation(Occupation)
                .withEducationQualification(EducationQualification).withNationality(Nationality)
                .withFullName(fullName).withFathersMothersName(fathersMothersName).withReligion(religion)
                .build();
    }


}
