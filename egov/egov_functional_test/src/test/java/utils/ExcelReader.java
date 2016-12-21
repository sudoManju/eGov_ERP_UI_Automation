package utils;

import builders.LoginDetailsBuilder;
import builders.collections.ChallanHeaderDetailsBuilder;
import builders.collections.ChequeDetailsBuilder;
import builders.dcReports.PTReportBuilder;
import builders.dcReports.VLTReportBuilder;
import builders.ptis.*;
import builders.wcms.EnclosedDocumentBuilder;
import builders.wcms.FieldInspectionDetailsBuilder;
import builders.works.*;
import cucumber.api.java8.Da;
import entities.*;
import entities.collections.ChallanHeaderDetails;
import entities.collections.ChequeDetails;
import entities.dcReports.PTReport;
import entities.dcReports.VLTReport;
import entities.ptis.*;
import entities.wcms.EnclosedDocument;
import entities.wcms.FieldInspectionDetails;
import entities.works.*;
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
    Sheet propertyHeaderDetailsSheet;
    Sheet ownerDetailsSheet;
    Sheet addressDetailsSheet;
    Sheet assessmentDetailsSheet;
    Sheet amenitiesSheet;
    Sheet constructionTypeDetailsSheet;
    Sheet floorDetailsSheet;
    Sheet approvalDetailsSheet;
    Sheet chequeDetailsSheet;
    Sheet applicantParticularsSheet;
    Sheet connectionDetailsSheet;
    Sheet searchDetailsSheet;
    Sheet editAssessmentDetailsSheet;
    Sheet editFloorDetailsSheet;
    Sheet enclosedDocumentSheet;
    Sheet challanHeaderDetailsSheet;
    Sheet enclosedDocumentsSheet;
    Sheet vltReportSheet;
    Sheet registeredUserDetailsSheet;
    Sheet estimateHeaderDetailsSheet;
    Sheet financialDetailsSheet;

    Sheet workDetailsSheet;
    Sheet adminSanctionDetailsSheet;
    Sheet fieldInseptionDetailsForWaterConnectionSheet;
    Sheet ptReportSheet;
    Sheet technicalSanctionDetailsSheet;




    public ExcelReader(String testData) {
        String excelFilePath = testData + ".xlsx";
        System.out.println(excelFilePath);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(excelFilePath);
        try {
            workbook = WorkbookFactory.create(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        registeredUserSheet = workbook.getSheet("registeredUserDetails");
        propertyHeaderDetailsSheet = workbook.getSheet("propertyHeaderDetails");
        ownerDetailsSheet = workbook.getSheet("ownerDetails");
        addressDetailsSheet = workbook.getSheet("addressDetails");
        assessmentDetailsSheet = workbook.getSheet("assessmentDetails");
        amenitiesSheet = workbook.getSheet("amenities");
        constructionTypeDetailsSheet = workbook.getSheet("constructionTypeDetails");
        floorDetailsSheet = workbook.getSheet("floorDetails");
        approvalDetailsSheet = workbook.getSheet("approvalDetails");
        applicantParticularsSheet = workbook.getSheet("applicantParticulars");
        connectionDetailsSheet = workbook.getSheet("connectionDetails");
        searchDetailsSheet = workbook.getSheet("searchDetails");
        chequeDetailsSheet = workbook.getSheet("chequeDetails");

        applicantParticularsSheet = workbook.getSheet("applicantParticulars");
        connectionDetailsSheet = workbook.getSheet("connectionDetails");

        editAssessmentDetailsSheet = workbook.getSheet("editAssessmentDetails") ;
        editFloorDetailsSheet = workbook.getSheet("editFloorDetails") ;

        enclosedDocumentSheet = workbook.getSheet("enclosedDocumentsDetails");
        challanHeaderDetailsSheet = workbook.getSheet("challanHeaderDetails");

        enclosedDocumentsSheet = workbook.getSheet("enclosedDocuments");
        vltReportSheet = workbook.getSheet("vltReport");
        ptReportSheet = workbook.getSheet("ptReport");

        registeredUserDetailsSheet = workbook.getSheet("registeredUserDetails");
        estimateHeaderDetailsSheet = workbook.getSheet("estimateHeaderDetails");
        financialDetailsSheet = workbook.getSheet("financialDetails");
        workDetailsSheet = workbook.getSheet("workDetails");
        adminSanctionDetailsSheet = workbook.getSheet("adminSanctionDetails");
        fieldInseptionDetailsForWaterConnectionSheet = workbook.getSheet("fieldInseptionDetailsForWaterConnection");
        technicalSanctionDetailsSheet = workbook.getSheet("technicalSanctionDetails");

    }

    private Row readDataRow(Sheet fromSheet, String dataId) {
        Iterator<Row> rowIterator = fromSheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(0).getStringCellValue().equals(dataId))
                return row;
        }
        throw new RuntimeException("No data found with this identifier: " + dataId);
    }

    private Cell getCellData(Sheet fromSheet, Row dataRow, String header) {
        return dataRow.getCell(getCellNumberWithHeader(fromSheet, header), Row.CREATE_NULL_AS_BLANK);
    }

    private int getCellNumberWithHeader(Sheet sheet, String header) {

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

                searchDetails = new SearchDetailsBuilder()
                        .withZoneNumber(zone)
                        .withWardNumber(ward)
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
        String Subject = getCellData(estimateHeaderDetailsSheet, dataRow, "subject").getStringCellValue();
        String RequirementNumber = getCellData(estimateHeaderDetailsSheet, dataRow, "requirementNumber").getStringCellValue();
        String Description = getCellData(estimateHeaderDetailsSheet, dataRow, "description").getStringCellValue();
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
        String ModeOfEntrustment = getCellData(estimateHeaderDetailsSheet, dataRow, "modeOfEntrustment").getStringCellValue();

        return new EstimateHeaderDetailsBuilder()
                 .withDate(Date)
                 .withSubject(Subject)
                 .withRequirementNumber(RequirementNumber)
                 .withDescription(Description)
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
        return new OwnerDetailsBuilder().withMobileNumber(mobileNumber)
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
        Cell registrationDocNumberCell = getCellData(assessmentDetailsSheet, dataRow, "registrationDocNumber");
        registrationDocNumberCell.setCellType(Cell.CELL_TYPE_STRING);
        String extentOfSite = extentOfSiteCell.getStringCellValue();
        String occupancyCertificateNumber = occupancyCertificateNumberCell.getStringCellValue();
        String registrationDocNumber = registrationDocNumberCell.getStringCellValue();
        Date registrationDocDate = getCellData(assessmentDetailsSheet, dataRow, "registrationDocDate").getDateCellValue();

        return new AssessmentDetailsBuilder().withReasonForCreation(reasonForCreation)
                .withExtentOfSite(extentOfSite)
                .withOccupancyCertificateNumber(occupancyCertificateNumber)
                .withRegistrationDocNumber(registrationDocNumber)
                .withRegistrationDocDate(new SimpleDateFormat("dd/MM/yy").format(registrationDocDate)).build();
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

    public ApplicantInfo getApplicantInfo(String applicantDetailsDataId){
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
        Cell quantityCell = getCellData(workDetailsSheet, dataRow, "quantity");
        quantityCell.setCellType(Cell.CELL_TYPE_STRING);
        String quantity = quantityCell.getStringCellValue();
        String uom = getCellData(workDetailsSheet, dataRow, "uom").getStringCellValue();
        String expectedOutcome = getCellData(workDetailsSheet, dataRow, "expectedOutcome").getStringCellValue();

        return new WorkDetailsBuilder()
                .withWorksOrderCreated(worksCreated)
                .withBillsCreated(billCreated)
                .withNameOfWork(nameOfWork)
                .withAbstarctEstimateNumber(abstractEstimateNumber)
                .withEstimatedAmount(estimateAmount)
                .withWorkIdentificationNumber(WIN)
                .withActualEstimateAmount(actualEstimateAmount)
                .withGrossAmountBilled(grossAmountBilled)
                .withQuantity(quantity)
                .withUOM(uom)
                .withExpectedOutcome(expectedOutcome)
                .build();

    }

    public AdminSanctionDetails getAdminSanctionDetails(String adminSanctionDetailsDataId) {

        Row dataRow = readDataRow(adminSanctionDetailsSheet, adminSanctionDetailsDataId);

        String AdminstrativeSanctionNumber = getCellData(adminSanctionDetailsSheet, dataRow, "administrativeSanctionNumber").getStringCellValue();

        Cell dateCell = getCellData(adminSanctionDetailsSheet, dataRow, "adminSanctionDate");
        dateCell.setCellType(Cell.CELL_TYPE_STRING);
        String Date  = dateCell.getStringCellValue();

        return new AdminSanctionDetailsBuilder()
                .withAdministrationSanctionNumber(AdminstrativeSanctionNumber)
                .withAdminSanctionDate(Date)
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
}
