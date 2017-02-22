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
    Sheet propertyHeaderDetailsSheet;
    Sheet ownerDetailsSheet;
    Sheet addressDetailsSheet;
    Sheet assessmentDetailsSheet;
    Sheet amenitiesSheet;
    Sheet constructionTypeDetailsSheet;
    Sheet floorDetailsSheet;
    Sheet approvalDetailsSheet;
    Sheet searchDetailsSheet;
    Sheet editAssessmentDetailsSheet;
    Sheet editFloorDetailsSheet;
    Sheet documentDetailsSheet;
    Sheet vltReportSheet;
    Sheet ptReportSheet;
    Sheet revisionPetitionDetailsSheet;
    Sheet hearingDetailsSheet;
    Sheet registrationDetailsSheet;

    //  Council Maanagement Sheets
    Sheet enclosedDocumentSheet;
    Sheet chequeDetailsSheet;
    Sheet challanHeaderDetailsSheet;
    Sheet registeredUserDetailsSheet;
    Sheet estimateHeaderDetailsSheet;
    Sheet financialDetailsSheet;
    Sheet workDetailsSheet;
    Sheet adminSanctionDetailsSheet;


    Sheet technicalSanctionDetailsSheet;
    //     Trade License Sheets
    Sheet tradeOwnerDetailsSheet;
    Sheet tradeLocationDetailsSheet;
    Sheet tradeDetailsSheet;
    Sheet legencyDetailsSheet;
    Sheet licenseClosureSheet;

    Sheet searchTradeDetailsSheet;



    Sheet paymentMethodSheet;
    Sheet dataFromWebSheet;

//    Grievances Sheets
    Sheet grievancesContactDetailsSheet;
    Sheet grievanceDetailsSheet;
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
        registeredUserSheet = workbook.getSheet("registeredUserDetails");
        propertyHeaderDetailsSheet = workbook.getSheet("propertyHeaderDetails");
        ownerDetailsSheet = workbook.getSheet("ownerDetails");
        addressDetailsSheet = workbook.getSheet("addressDetails");
        assessmentDetailsSheet = workbook.getSheet("assessmentDetails");
        amenitiesSheet = workbook.getSheet("amenities");
        constructionTypeDetailsSheet = workbook.getSheet("constructionTypeDetails");
        floorDetailsSheet = workbook.getSheet("floorDetails");
        approvalDetailsSheet = workbook.getSheet("approvalDetails");
        searchDetailsSheet = workbook.getSheet("searchDetails");
        editAssessmentDetailsSheet = workbook.getSheet("editAssessmentDetails") ;
        editFloorDetailsSheet = workbook.getSheet("editFloorDetails") ;
        vltReportSheet = workbook.getSheet("vltReport");
        ptReportSheet = workbook.getSheet("ptReport");
        registrationDetailsSheet = workbook.getSheet("registrationDetails");
        revisionPetitionDetailsSheet = workbook.getSheet("revisionPetitionDetails");
        hearingDetailsSheet = workbook.getSheet("hearingDetails");

        chequeDetailsSheet = workbook.getSheet("chequeDetails");

        enclosedDocumentSheet = workbook.getSheet("enclosedDocumentsDetails");
        challanHeaderDetailsSheet = workbook.getSheet("challanHeaderDetails");


        registeredUserDetailsSheet = workbook.getSheet("registeredUserDetails");
        estimateHeaderDetailsSheet = workbook.getSheet("estimateHeaderDetails");
        financialDetailsSheet = workbook.getSheet("financialDetails");
        workDetailsSheet = workbook.getSheet("workDetails");
        adminSanctionDetailsSheet = workbook.getSheet("adminSanctionDetails");
        approverDetailsSheet = workbook.getSheet("approvalDetails");
        technicalSanctionDetailsSheet = workbook.getSheet("technicalSanctionDetails");

        //        Trade License Sheet Names
        tradeOwnerDetailsSheet = workbook.getSheet("tradeOwnerDetails");
        tradeLocationDetailsSheet =workbook.getSheet("tradeLocationDetails");
        tradeDetailsSheet = workbook.getSheet("tradeDetails");
        licenseClosureSheet = workbook.getSheet("licenseClosure");
        searchTradeDetailsSheet = workbook.getSheet("searchTradeDeatils");
        legencyDetailsSheet = workbook.getSheet("legencyDetails");

        //        Council management Sheets

        dataFromWebSheet = workbook.getSheet("dataFromWeb");
        paymentMethodSheet = workbook.getSheet("paymentMethod");

//        Grievances sheets
        grievancesContactDetailsSheet = workbook.getSheet("contactInfo");
        grievanceDetailsSheet = workbook.getSheet("grievanceDetails");

//        Marriage Registration
        applicantsInformationSheet = workbook.getSheet("applicantsInformation");
        bridegroomInformationSheet = workbook.getSheet("bridegroomInformation");

        documentDetailsSheet = workbook.getSheet("documentDetails");

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


    public SearchDetails getSearchDetails(String searchId){
        Row dataRow = readDataRow(searchDetailsSheet, searchId);
        SearchDetails searchDetails = new SearchDetails();

        switch (searchId){
            case "searchWithAssessmentNumber":
                Cell assessmentNumberCell = getCellData(searchDetailsSheet, dataRow, "searchValue");
                assessmentNumberCell.setCellType(Cell.CELL_TYPE_STRING);
                String assessmentNumber = assessmentNumberCell.getStringCellValue();

                searchDetails =  new SearchDetailsBuilder()
                        .withAssessmentNumber(assessmentNumber)
                        .build();

                break;
            case "searchWithMobileNumber":
                Cell mobileNumberCell = getCellData(searchDetailsSheet, dataRow, "searchValue");
                mobileNumberCell.setCellType(Cell.CELL_TYPE_STRING);
                String mobileNumber = mobileNumberCell.getStringCellValue();

                searchDetails =  new SearchDetailsBuilder()
                        .withMobileNumber(mobileNumber)
                        .build();

                break;

            case "searchWithDoorNumber":
                String doorNumber = getCellData(searchDetailsSheet, dataRow, "searchValue").getStringCellValue();

                searchDetails = new SearchDetailsBuilder()
                        .withDoorNumber(doorNumber)
                        .build();

                break;

            case "searchWithZoneAndWardNumber":
                String value = getCellData(searchDetailsSheet, dataRow, "searchValue").getStringCellValue();
                String[] values = value.split(";");
                String zone = values[0];
                String ward = values[1];

                String value1 = getCellData(searchDetailsSheet, dataRow, "searchValue2").getStringCellValue();
                String[] values1 = value1.split(";");
                String houseNo = values1[0];
                String ownerName = values1[1];

                searchDetails = new SearchDetailsBuilder()
                        .withZoneNumber(zone)
                        .withWardNumber(ward)
                        .withHouseNo(houseNo)
                        .withOwnerName(ownerName)
                        .build();

                break;

            case "searchWithOwnerName":

                String value2 = getCellData(searchDetailsSheet, dataRow, "searchValue").getStringCellValue();
                String[] values2 = value2.split(";");
                String location = values2[0];
                String ownerName1 = values2[1];

                searchDetails = new SearchDetailsBuilder()
                        .withLocation(location)
                        .withOwnerName(ownerName1)
                        .build();
                break;

            case "searchByDemand":

                String value3 = getCellData(searchDetailsSheet, dataRow, "searchValue").getStringCellValue();
                String[] values3 = value3.split(";");
                String From = values3[0];
                String To = values3[1];

                searchDetails = new SearchDetailsBuilder()
                        .withFrom(From)
                        .withTo(To)
                        .build();

                break;

        }
        return searchDetails;
    }


    public EstimateHeaderDetails getEstimateHeaderDetails(String EstimateDetailsDataId) {
        Row dataRow = readDataRow(estimateHeaderDetailsSheet, EstimateDetailsDataId);
        Cell dateCell = getCellData(estimateHeaderDetailsSheet, dataRow, "date");
        dateCell.setCellType(Cell.CELL_TYPE_STRING);
        String Date  = dateCell.getStringCellValue();
        String RequirementNumber = getCellData(estimateHeaderDetailsSheet, dataRow, "requirementNumber").getStringCellValue();
        Cell electionWardCell = getCellData(estimateHeaderDetailsSheet, dataRow, "electionWard");
        electionWardCell.setCellType(Cell.CELL_TYPE_STRING);
        String ElectionWard  = electionWardCell.getStringCellValue();
        Cell locationCell = getCellData(estimateHeaderDetailsSheet, dataRow, "location");
        locationCell.setCellType(Cell.CELL_TYPE_STRING);
        String location = locationCell.getStringCellValue();
        String WorkCategory = getCellData(estimateHeaderDetailsSheet, dataRow, "workCategory").getStringCellValue();
        Cell beneficiaryCell = getCellData(estimateHeaderDetailsSheet, dataRow, "beneficiary");
        beneficiaryCell.setCellType(Cell.CELL_TYPE_STRING);
        String Beneficiary = beneficiaryCell.getStringCellValue();
        String NatureOfWork = getCellData(estimateHeaderDetailsSheet, dataRow, "natureOfWork").getStringCellValue();
        String TypeOfWork = getCellData(estimateHeaderDetailsSheet, dataRow, "typeOfWork").getStringCellValue();
        String SubTypeOfWork = getCellData(estimateHeaderDetailsSheet, dataRow, "subTypeOfWork").getStringCellValue();
        String ModeOfEntrustment = getCellData(estimateHeaderDetailsSheet, dataRow, "recommendedModeOfEntrustment").getStringCellValue();

        return new EstimateHeaderDetailsBuilder()
                 .withDate(Date)
                 .withRequirementNumber(RequirementNumber)
                 .withElectionWard(ElectionWard)
                 .withLocation(location)
                 .withWorkCategory(WorkCategory)
                 .withBeneficiary(Beneficiary)
                 .withNatureOfWork(NatureOfWork)
                 .withTypeOfWork(TypeOfWork)
                 .withSubTypeOfWork(SubTypeOfWork)
                 .withModeOfEntrustment(ModeOfEntrustment)
                 .build();
    }
    //end of works management module line estimate

    public PropertyHeaderDetails getPropertyHeaderDetails(String propertyDetailsDataId) {
        Row dataRow = readDataRow(propertyHeaderDetailsSheet, propertyDetailsDataId);
        String propertyType = getCellData(propertyHeaderDetailsSheet, dataRow, "propertyType").getStringCellValue();
        String categoryOfOwnership = getCellData(propertyHeaderDetailsSheet, dataRow, "categoryOfOwnership").getStringCellValue();

        return new PropertyHeaderDetailsBuilder()
                .withPropertyType(propertyType)
                .withCategoryOfOwnership(categoryOfOwnership)
                .build();
    }

    public OwnerDetails getOwnerDetails(String ownerDetailsDataId) {
        Row dataRow = readDataRow(ownerDetailsSheet, ownerDetailsDataId);
        Cell mobileNumberCell = getCellData(ownerDetailsSheet, dataRow, "mobileNumber");
        mobileNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String mobileNumber = mobileNumberCell.getStringCellValue();
        String ownerName = getCellData(ownerDetailsSheet, dataRow, "ownerName").getStringCellValue();
        String gender = getCellData(ownerDetailsSheet, dataRow, "gender").getStringCellValue();
        String emailAddress = getCellData(ownerDetailsSheet, dataRow, "emailAddress").getStringCellValue();
        String guardianRelation = getCellData(ownerDetailsSheet, dataRow, "guardianRelation").getStringCellValue();
        String guardian = getCellData(ownerDetailsSheet, dataRow, "guardian").getStringCellValue();
        return new OwnerDetailsBuilder()
                .withMobileNumber(mobileNumber)
                .withOwnerName(ownerName)
                .withGender(gender)
                .withEmailAddress(emailAddress)
                .withGuardianName(guardian)
                .withGuardianRelation(guardianRelation).build();
    }

    public PropertyAddressDetails getPropertyAddressDetails(String addressDetailsDataId) {

        Row dataRow = readDataRow(addressDetailsSheet, addressDetailsDataId);

        String locality = getCellData(addressDetailsSheet, dataRow, "locality").getStringCellValue();
        String zoneNumber = getCellData(addressDetailsSheet, dataRow, "zoneNumber").getStringCellValue();
        String electionWard = getCellData(addressDetailsSheet, dataRow, "electionWard").getStringCellValue();
        Cell doorNumberCell = getCellData(addressDetailsSheet, dataRow, "doorNumber");
        doorNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String doorNumber = doorNumberCell.getStringCellValue();
        Cell pincodeCell = getCellData(addressDetailsSheet, dataRow, "pincode");
        pincodeCell.setCellType(Cell.CELL_TYPE_STRING);
        String pinCode = pincodeCell.getStringCellValue();

        return new AddressDetailsBuilder()
                .withLocality(locality)
                .withZoneNumber(zoneNumber)
                .withElectionWard(electionWard)
                .withDoorNumber(doorNumber)
                .withPincode(pinCode).build();
    }

    public AssessmentDetails getAssessmentDetails(String assessmentDetailsDataId) {
        Row dataRow = readDataRow(assessmentDetailsSheet, assessmentDetailsDataId);
        String reasonForCreation = getCellData(assessmentDetailsSheet, dataRow, "reasonForCreation").getStringCellValue();
        Cell extentOfSiteCell = getCellData(assessmentDetailsSheet, dataRow, "extentOfSite");
        extentOfSiteCell.setCellType(Cell.CELL_TYPE_STRING);
        Cell occupancyCertificateNumberCell = getCellData(assessmentDetailsSheet, dataRow, "occupancyCertificateNumber");
        occupancyCertificateNumberCell.setCellType(Cell.CELL_TYPE_STRING);
//        Cell registrationDocNumberCell = getCellData(assessmentDetailsSheet, dataRow, "registrationDocNumber");
//        registrationDocNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String extentOfSite = extentOfSiteCell.getStringCellValue();
        String occupancyCertificateNumber = occupancyCertificateNumberCell.getStringCellValue();

        return new AssessmentDetailsBuilder().withReasonForCreation(reasonForCreation)
                .withExtentOfSite(extentOfSite)
                .withOccupancyCertificateNumber(occupancyCertificateNumber)
                .build();
    }

    public Amenities getAmenties(String amenitiesDataId) {
        Row dataRow = readDataRow(amenitiesSheet, amenitiesDataId);

        boolean lift = getCellData(amenitiesSheet, dataRow, "lift").getBooleanCellValue();
        boolean toilets = getCellData(amenitiesSheet, dataRow, "toilets").getBooleanCellValue();
        boolean waterTap = getCellData(amenitiesSheet, dataRow, "waterTap").getBooleanCellValue();
        boolean electricity = getCellData(amenitiesSheet, dataRow, "electricity").getBooleanCellValue();
        boolean attachedBathroom = getCellData(amenitiesSheet, dataRow, "attachedBathroom").getBooleanCellValue();
        boolean waterHarvesting = getCellData(amenitiesSheet, dataRow, "waterHarvesting").getBooleanCellValue();
        boolean cableConnection = getCellData(amenitiesSheet, dataRow, "cableConnection").getBooleanCellValue();

        return new AmenitiesBuilder()
                .hasLift(lift)
                .hasToilets(toilets)
                .hasAttachedBathroom(attachedBathroom)
                .hasElectricity(electricity)
                .hasWaterTap(waterTap)
                .hasWaterHarvesting(waterHarvesting)
                .hasCableConnection(cableConnection).build();
    }

    public ConstructionTypeDetails getConstructionTypeDetails(String constructionTypeDetailsDataId) {
        Row dataRow = readDataRow(constructionTypeDetailsSheet, constructionTypeDetailsDataId);

        String floorType = getCellData(constructionTypeDetailsSheet, dataRow, "floorType").getStringCellValue();
        String roofType = getCellData(constructionTypeDetailsSheet, dataRow, "roofType").getStringCellValue();
        String woodType = getCellData(constructionTypeDetailsSheet, dataRow, "woodType").getStringCellValue();
        String wallType = getCellData(constructionTypeDetailsSheet, dataRow, "wallType").getStringCellValue();

        return new ConstructionTypeDetailsBuilder().withFloorType(floorType)
                .withRoofType(roofType)
                .withWallType(wallType)
                .withWoodType(woodType).build();
    }

    public FloorDetails getFloorDetails(String floorDetailsDataId) {
        Row dataRow = readDataRow(floorDetailsSheet, floorDetailsDataId);

        String floorNumber = getCellData(floorDetailsSheet, dataRow, "floorNumber").getStringCellValue();
        String classificationOfBuilding = getCellData(floorDetailsSheet, dataRow, "classificationOfBuilding").getStringCellValue();
        String natureOfUsage = getCellData(floorDetailsSheet, dataRow, "natureOfUsage").getStringCellValue();
        String firmName = getCellData(floorDetailsSheet, dataRow, "firmName").getStringCellValue();
        String occupancy = getCellData(floorDetailsSheet, dataRow, "occupancy").getStringCellValue();
        String occupantName = getCellData(floorDetailsSheet, dataRow, "occupantName").getStringCellValue();
        Date constructionDate = getCellData(floorDetailsSheet, dataRow, "constructionDate").getDateCellValue();
        Date effectiveFromDate = getCellData(floorDetailsSheet, dataRow, "effectiveFromDate").getDateCellValue();
        String unstructuredLand = getCellData(floorDetailsSheet, dataRow, "unstructuredLand").getStringCellValue();

        Cell lengthCell = getCellData(floorDetailsSheet, dataRow, "length");
        lengthCell.setCellType(Cell.CELL_TYPE_STRING);
        Cell breadthCell = getCellData(floorDetailsSheet, dataRow, "breadth");
        breadthCell.setCellType(Cell.CELL_TYPE_STRING);

        String length = lengthCell.getStringCellValue();
        String breadth = breadthCell.getStringCellValue();

//        getCellData(constructionTypeDetailsSheet, dataRow, "plinthArea");
        Cell buildingPermissionNumberCell = getCellData(floorDetailsSheet, dataRow, "buildingPermissionNumber");
        buildingPermissionNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        Cell plinthAreaInBuildingPlanCell = getCellData(floorDetailsSheet, dataRow, "plinthAreaInBuildingPlan");
        plinthAreaInBuildingPlanCell.setCellType(Cell.CELL_TYPE_STRING);

        String buildingPermissionNumber = buildingPermissionNumberCell.getStringCellValue();
        Date buildingPermissionDate = getCellData(floorDetailsSheet, dataRow, "buildingPermissionDate").getDateCellValue();
        String plinthAreaInBuildingPlan = plinthAreaInBuildingPlanCell.getStringCellValue();


        return new FloorDetailsBuilder().withFloorNumber(floorNumber)
                .withClassificationOfBuilding(classificationOfBuilding)
                .withNatureOfUsage(natureOfUsage)
                .withFirmName(firmName)
                .withOccupancy(occupancy)
                .withOccupantName(occupantName)
                .withConstructionDate(new SimpleDateFormat("dd/MM/yy").format(constructionDate))
                .withEffectiveFromDate(new SimpleDateFormat("dd/MM/yy").format(effectiveFromDate))
                .withUnstructuredLand(unstructuredLand)
                .withLength(length)
                .withBreadth(breadth)
                .withBuildingPermissionNumber(buildingPermissionNumber)
                .withBuildingPermissionDate(new SimpleDateFormat("dd/MM/yy").format(buildingPermissionDate))
                .withPlinthAreaInBuildingPlan(plinthAreaInBuildingPlan)
                .build();
    }

    public DocumentTypeValue getDocumentValue(String documentSelect) {
       Row dataRow = readDataRow(documentDetailsSheet, documentSelect);

        String documentType = getCellData(documentDetailsSheet, dataRow, "documentType").getStringCellValue();
        String deedNo = getCellData(documentDetailsSheet, dataRow, "deedNo").getStringCellValue();
        String deedDate = getCellData(documentDetailsSheet, dataRow, "deedDate").getStringCellValue();

        return new DocumentDetailsBuilder()
                    .withdocumentType(documentType)
                    .withDeedNo(deedNo)
                    .withDeedDate(deedDate)
                    .build();
    }

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


    public EditAssessmentDetails getEditAssessmentDetails(String assessmentDetailsDataName) {
    Row dataRow = readDataRow(editAssessmentDetailsSheet, assessmentDetailsDataName ) ;
        Cell extentOfSiteCell = getCellData(editAssessmentDetailsSheet, dataRow, "extentOfSite");
        extentOfSiteCell.setCellType(Cell.CELL_TYPE_STRING);
        String extentOfSite = extentOfSiteCell.getStringCellValue();
        Cell occupancyCertificateNumberCell = getCellData(editAssessmentDetailsSheet, dataRow, "occupancyCertificateNumber");
        occupancyCertificateNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String occupancyCertificateNumber = occupancyCertificateNumberCell.getStringCellValue();

        return new  EditAssessmentDetailsBuilder()
                   .withExtentOfSite(extentOfSite)
                   .withOccupancyCertificateNumber(occupancyCertificateNumber)
                   .build();
    }

    public EditFloorDetails getEditFloorDetails(String floordetailsDataName) {
        Row dataRow = readDataRow(editFloorDetailsSheet, floordetailsDataName);
        Cell editfloorNumberCell = getCellData(editFloorDetailsSheet, dataRow, "editfloorNumber");
        editfloorNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String editfloorNumber = editfloorNumberCell.getStringCellValue();
        Cell editclassificationOfBuildingCell = getCellData(editFloorDetailsSheet, dataRow, "editclassificationOfBuilding");
        editclassificationOfBuildingCell.setCellType(Cell.CELL_TYPE_STRING);
        String editclassificationOfBuilding = editclassificationOfBuildingCell.getStringCellValue();
        Cell editnatureOfUsageCell = getCellData(editFloorDetailsSheet, dataRow, "editnatureOfUsage");
        editnatureOfUsageCell.setCellType(Cell.CELL_TYPE_STRING);
        String editnatureOfUsage = editnatureOfUsageCell.getStringCellValue();

        Cell editoccupancyCell = getCellData(editFloorDetailsSheet, dataRow, "editoccupancy");
        editoccupancyCell.setCellType(Cell.CELL_TYPE_STRING);
        String editoccupancy = editoccupancyCell.getStringCellValue();

        Cell editoccupantNameCell =getCellData(editFloorDetailsSheet, dataRow, "editoccupantName");
        editoccupantNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String editoccupantName =editoccupantNameCell.getStringCellValue();

        Cell editconstructionDateCell =getCellData(editFloorDetailsSheet, dataRow, "editconstructionDate");
        editconstructionDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String editconstructionDate = editconstructionDateCell.getStringCellValue();

        Cell editeffectiveFromDateCell =getCellData(editFloorDetailsSheet, dataRow, "editeffectiveFromDate");
        editeffectiveFromDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String editeffectiveFromDate = editeffectiveFromDateCell.getStringCellValue();

        Cell editunstructuredLandCell = getCellData(editFloorDetailsSheet, dataRow, "editunstructuredLand");
        editunstructuredLandCell.setCellType(Cell.CELL_TYPE_STRING);
        String editunstructuredLand = editunstructuredLandCell.getStringCellValue();

        Cell editlengthCell = getCellData(editFloorDetailsSheet, dataRow, "editlength");
        editlengthCell.setCellType(Cell.CELL_TYPE_STRING);
        String editlength = editlengthCell.getStringCellValue();

        Cell editbreadthCell = getCellData(editFloorDetailsSheet, dataRow, "editbreadth");
        editbreadthCell.setCellType(Cell.CELL_TYPE_STRING);
        String editbreadth = editbreadthCell.getStringCellValue();

        Cell editbuildingPermissionNumberCell = getCellData(editFloorDetailsSheet, dataRow, "editbuildingPermissionNumber");
        editbuildingPermissionNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String editbuildingPermissionNumber = editbuildingPermissionNumberCell.getStringCellValue();

        Cell editbuildingPermissionDateCell = getCellData(editFloorDetailsSheet, dataRow, "editbuildingPermissionDate");
        editbuildingPermissionDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String editbuildingPermissionDate = editbuildingPermissionDateCell.getStringCellValue();

        Cell editplinthAreaInBuildingPlanCell = getCellData(editFloorDetailsSheet, dataRow, "editplinthAreaInBuildingPlan");
        editplinthAreaInBuildingPlanCell.setCellType(Cell.CELL_TYPE_STRING);
        String editplinthAreaInBuildingPlan = editplinthAreaInBuildingPlanCell.getStringCellValue();

        return new EditFloorDetailsBuilder()
                    .withEditFloorNumber(editfloorNumber)
                    .withEditclassificationOfBuilding(editclassificationOfBuilding)
                    .withEditnatureOfUsage(editnatureOfUsage)
                    .withEditoccupancy(editoccupancy)
                    .withEditoccupantName(editoccupantName)
                    .withEditconstructionDate(editconstructionDate)
                    .withEditeffectiveFromDate(editeffectiveFromDate)
                    .withEditunstructuredLand(editunstructuredLand)
                    .withEditlength(editlength)
                    .withEditbreadth(editbreadth)
                    .withEditbuildingPermissionNumber(editbuildingPermissionNumber)
                    .withEditbuildingPermissionDate(editbuildingPermissionDate)
                    .withEditplinthAreaInBuildingPlan(editplinthAreaInBuildingPlan)
                    .build();
    }

    public ChallanHeaderDetails getChallanHeader(String challanheaderid) {

        Row dataRow = readDataRow(challanHeaderDetailsSheet, challanheaderid);

        Cell dateCell = getCellData(challanHeaderDetailsSheet, dataRow, "date");
        dateCell.setCellType(Cell.CELL_TYPE_STRING);
        String date = dateCell.getStringCellValue();
        String payeeName = getCellData(challanHeaderDetailsSheet, dataRow, "payeeName").getStringCellValue();
        String payeeAddress = getCellData(challanHeaderDetailsSheet,dataRow,"payeeAddress").getStringCellValue();
        String narration = getCellData(challanHeaderDetailsSheet,dataRow,"narration").getStringCellValue();
        String serviceCategory = getCellData(challanHeaderDetailsSheet,dataRow,"serviceCategory").getStringCellValue();
        String sericeType = getCellData(challanHeaderDetailsSheet,dataRow,"serviceType").getStringCellValue();

        Cell amountCell = getCellData(challanHeaderDetailsSheet, dataRow, "amount");
        amountCell.setCellType(Cell.CELL_TYPE_STRING);
        String amount = amountCell.getStringCellValue();

        return new ChallanHeaderDetailsBuilder()
                .withDate(date)
                .withPayeeName(payeeName)
                .withPayeeAddress(payeeAddress)
                .withNarration(narration)
                .withServiceCategory(serviceCategory)
                .withServiceType(sericeType)
                .withAmount(amount)
                .build();
    }

    public VLTReport getVLTReportInfo(String vltReport){

        Row dataRow = readDataRow(vltReportSheet , vltReport);

        Cell fromDateCell = getCellData(vltReportSheet, dataRow, "fromDate");
        fromDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String fromDate = fromDateCell.getStringCellValue();

        Cell toDateCell = getCellData(vltReportSheet, dataRow, "toDate");
        toDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String toDate = toDateCell.getStringCellValue();

        return new VLTReportBuilder()
                .withFromDate(fromDate)
                .withToDate(toDate)
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

    public PTReport getPTReportInfo(String ptReport){

        Row dataRow = readDataRow(ptReportSheet , ptReport);

        Cell fromDateCell = getCellData(ptReportSheet, dataRow, "fromDate");
        fromDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String fromDate = fromDateCell.getStringCellValue();

        Cell toDateCell = getCellData(ptReportSheet, dataRow, "toDate");
        toDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String toDate = toDateCell.getStringCellValue();

        return new PTReportBuilder()
                .withFromDate(fromDate)
                .withToDate(toDate)
                .build();
    }


    public FinancialDetails getFinancialDetails(String financialDetailsDataId) {

        Row dataRow = readDataRow(financialDetailsSheet, financialDetailsDataId);

        String fund = getCellData(financialDetailsSheet, dataRow, "fund").getStringCellValue();
        String function = getCellData(financialDetailsSheet, dataRow, "function").getStringCellValue();
        String budgetHead = getCellData(financialDetailsSheet, dataRow, "budgetHead").getStringCellValue();

        return new FinancialDetailsBuilder()
                .withFund(fund)
                .withFunction(function)
                .withBudgetHead(budgetHead)
                .build();
    }

    public WorkDetails getWorkDetails(String workDetailsDataId) {

        Row dataRow = readDataRow(workDetailsSheet, workDetailsDataId);

        boolean worksCreated = getCellData(workDetailsSheet, dataRow, "worksOrderCreated").getBooleanCellValue();
        boolean billCreated = getCellData(workDetailsSheet, dataRow, "isBillCreated").getBooleanCellValue();
        String nameOfWork = getCellData(workDetailsSheet, dataRow, "nameOfWork").getStringCellValue();
        String abstractEstimateNumber = getCellData(workDetailsSheet, dataRow, "abstractEstimateNumber").getStringCellValue();
        Cell estimateAmountCell = getCellData(workDetailsSheet, dataRow, "estimatedAmount");
        estimateAmountCell.setCellType(Cell.CELL_TYPE_STRING);
        String estimateAmount = estimateAmountCell.getStringCellValue();
        String WIN = getCellData(workDetailsSheet, dataRow, "workIdentificationNumber").getStringCellValue();
        Cell actualEstimateAmountCell = getCellData(workDetailsSheet, dataRow, "actualEstimateAmount");
        actualEstimateAmountCell.setCellType(Cell.CELL_TYPE_STRING);
        String actualEstimateAmount = actualEstimateAmountCell.getStringCellValue();
        Cell grossAmountBilledCell = getCellData(workDetailsSheet, dataRow, "grossAmountBilled");
        grossAmountBilledCell.setCellType(Cell.CELL_TYPE_STRING);
        String grossAmountBilled = grossAmountBilledCell.getStringCellValue();

        return new WorkDetailsBuilder()
                .withWorksOrderCreated(worksCreated)
                .withBillsCreated(billCreated)
                .withNameOfWork(nameOfWork)
                .withAbstarctEstimateNumber(abstractEstimateNumber)
                .withEstimatedAmount(estimateAmount)
                .withWorkIdentificationNumber(WIN)
                .withActualEstimateAmount(actualEstimateAmount)
                .withGrossAmountBilled(grossAmountBilled)
                .build();

    }

    public AdminSanctionDetails getAdminSanctionDetails(String adminSanctionDetailsDataId) {

        Row dataRow = readDataRow(adminSanctionDetailsSheet, adminSanctionDetailsDataId);

        String AdminstrativeSanctionNumber = getCellData(adminSanctionDetailsSheet, dataRow, "administrativeSanctionNumber").getStringCellValue();

        Cell dateCell = getCellData(adminSanctionDetailsSheet, dataRow, "adminSanctionDate");
        dateCell.setCellType(Cell.CELL_TYPE_STRING);
        String Date  = dateCell.getStringCellValue();

        String AdminstrativeSanctionAuthority = getCellData(adminSanctionDetailsSheet,dataRow,"administrativeSanctionAuthority").getStringCellValue();


        return new AdminSanctionDetailsBuilder()
                .withAdministrationSanctionNumber(AdminstrativeSanctionNumber)
                .withAdminSanctionDate(Date)
                .withAdminSanctionAuthority(AdminstrativeSanctionAuthority)
                .build();

    }

    public TechnicalSanctionDetails getTechnicalSanctionDetails(String technicalSanctionDetailsDataId) {

        Row dataRow = readDataRow(technicalSanctionDetailsSheet, technicalSanctionDetailsDataId);

        String TechnicalSanctionNumber = getCellData(technicalSanctionDetailsSheet, dataRow, "technicalSanctionNumber").getStringCellValue();

        Cell dateCell = getCellData(technicalSanctionDetailsSheet, dataRow, "technicalSanctionDate");
        dateCell.setCellType(Cell.CELL_TYPE_STRING);
        String Date  = dateCell.getStringCellValue();

        String TechnicalSanctionAuthority = getCellData(technicalSanctionDetailsSheet, dataRow, "technicalSanctionAuthority").getStringCellValue();

        return new TechnicalSanctionDetailsBuilder()
                .withTechnicalSanctionNumber(TechnicalSanctionNumber)
                .withTechnicalSanctionDate(Date)
                .withTechnicalSanctionAuthority(TechnicalSanctionAuthority)
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

    public PaymentMethod getPaymentMethodDetails(String paymentMethod) {
        Row dataRow = readDataRow(paymentMethodSheet, paymentMethod);

               Cell chequeNumberCell = getCellData(paymentMethodSheet, dataRow, "dd/chequeNum");
               chequeNumberCell.setCellType(Cell.CELL_TYPE_STRING);
               String chequeNumber = chequeNumberCell.getStringCellValue();

               Cell bankNameCell = getCellData(paymentMethodSheet, dataRow, "bankName");
               bankNameCell.setCellType(Cell.CELL_TYPE_STRING);
               String bankName = bankNameCell.getStringCellValue();

               Cell accountNumCell = getCellData(paymentMethodSheet, dataRow, "accountNum");
               accountNumCell.setCellType(Cell.CELL_TYPE_STRING);
               String accountNum = accountNumCell.getStringCellValue();

        return new PaymentMethodBuilder()
                .withChequeNumber(chequeNumber)
                .withBankName(bankName)
                .withAccountNumber(accountNum)
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


    public CreateComplaintDetails getCitizenContactDetails(String contactInfo) {
        Row dataRow = readDataRow(grievancesContactDetailsSheet,contactInfo);

        Cell citizenNameCell= getCellData(grievancesContactDetailsSheet, dataRow, "citizenName");
        citizenNameCell.setCellType(Cell.CELL_TYPE_STRING);
        String citizenName=citizenNameCell.getStringCellValue();

        Cell citizenMobNoCell= getCellData(grievancesContactDetailsSheet, dataRow, "mobNo");
        citizenMobNoCell.setCellType(Cell.CELL_TYPE_STRING);
        String citizenMobNo= citizenMobNoCell.getStringCellValue();

        Cell emailIdCell= getCellData(grievancesContactDetailsSheet, dataRow, "emailId");
        emailIdCell.setCellType(Cell.CELL_TYPE_STRING);
        String emailId= emailIdCell.getStringCellValue();


        return new CreateComplaintDetailsBuilder()
                .withCitizenName(citizenName)
                .withCitizenMobNo(citizenMobNo)
                .withEmailId(emailId)
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

    public CreateComplaintDetails getGrievanceDetails(String grievanceDetails) {
    Row dataRow= readDataRow(grievanceDetailsSheet,grievanceDetails);

    String grievanceCategory= getCellData(grievanceDetailsSheet, dataRow,"grievanceCategory").getStringCellValue();

    String grievanceType=getCellData(grievanceDetailsSheet, dataRow,"grievanceType").getStringCellValue();

    String grievanceDetailsText= getCellData(grievanceDetailsSheet, dataRow, "grievanceDetails").getStringCellValue();

    String grievanceLocation= getCellData(grievanceDetailsSheet, dataRow, "grievanceLocation").getStringCellValue();

    String locationLandmark= getCellData(grievanceDetailsSheet, dataRow, "locationLandmark").getStringCellValue();

    return new CreateComplaintDetailsBuilder()
            .withGrievanceCategory(grievanceCategory)
            .withGrievanceType(grievanceType)
            .withGrievanceDetails(grievanceDetailsText)
            .withGrievanceLocation(grievanceLocation)
            .withLocationLandmark(locationLandmark)
            .build();

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

    public RevisionPetitionDetails getRevisionPetitionDetails(String revisionPetitionDataId) {
        Row dataRow = readDataRow(revisionPetitionDetailsSheet, revisionPetitionDataId);
        Cell revisionPetitionDetailsCell = getCellData(revisionPetitionDetailsSheet, dataRow, "revisionPetitionDetails");
        revisionPetitionDetailsCell.setCellType(Cell.CELL_TYPE_STRING);
        String revisionPetitionDetails = revisionPetitionDetailsCell.getStringCellValue();

        return new RevisionPetitionDetailsBuilder()
                .withRevisionPetitionDetail(revisionPetitionDetails)
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

    public HearingDetails getHearingDetails(String hearingDataId) {
        Row dataRow = readDataRow(hearingDetailsSheet, hearingDataId);
        Cell hearingDateCell = getCellData(hearingDetailsSheet, dataRow, "hearingDate");
        hearingDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String hearingDate = hearingDateCell.getStringCellValue();

        Cell hearingTimeCell = getCellData(hearingDetailsSheet, dataRow, "hearingTime");
        hearingTimeCell.setCellType(Cell.CELL_TYPE_STRING);
        String hearingTime = hearingTimeCell.getStringCellValue();

        String venue = getCellData(hearingDetailsSheet, dataRow, "venue").getStringCellValue();

        return new HearingDetailsBuilder()
                .withHearingDate(hearingDate)
                .withHearingTime(hearingTime)
                .withvenue(venue)
                .build();
    }
}
