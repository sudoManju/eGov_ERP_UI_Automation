package excelDataFiles;

import builders.dcReports.PTReportBuilder;
import builders.dcReports.VLTReportBuilder;
import builders.ptis.*;
import entities.dcReports.PTReport;
import entities.dcReports.VLTReport;
import entities.ptis.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PTISDataReader extends ExcelReader{

    Sheet propertyHeaderDetailsSheet;
    Sheet ownerDetailsSheet;
    Sheet addressDetailsSheet;
    Sheet assessmentDetailsSheet;
    Sheet amenitiesSheet;
    Sheet constructionTypeDetailsSheet;
    Sheet floorDetailsSheet;
    Sheet searchDetailsSheet;
    Sheet editAssessmentDetailsSheet;
    Sheet editFloorDetailsSheet;
    Sheet documentDetailsSheet;
    Sheet vltReportSheet;
    Sheet ptReportSheet;
    Sheet revisionPetitionDetailsSheet;
    Sheet hearingDetailsSheet;
    Sheet registrationDetailsSheet;


    public PTISDataReader(String testData) {
        super(testData);
        propertyHeaderDetailsSheet = workbook.getSheet("propertyHeaderDetails");
        ownerDetailsSheet = workbook.getSheet("ownerDetails");
        addressDetailsSheet = workbook.getSheet("addressDetails");
        assessmentDetailsSheet = workbook.getSheet("assessmentDetails");
        amenitiesSheet = workbook.getSheet("amenities");
        constructionTypeDetailsSheet = workbook.getSheet("constructionTypeDetails");
        floorDetailsSheet = workbook.getSheet("floorDetails");
        searchDetailsSheet = workbook.getSheet("searchDetails");
        editAssessmentDetailsSheet = workbook.getSheet("editAssessmentDetails") ;
        editFloorDetailsSheet = workbook.getSheet("editFloorDetails") ;
        vltReportSheet = workbook.getSheet("vltReport");
        ptReportSheet = workbook.getSheet("ptReport");
        registrationDetailsSheet = workbook.getSheet("registrationDetails");
        revisionPetitionDetailsSheet = workbook.getSheet("revisionPetitionDetails");
        hearingDetailsSheet = workbook.getSheet("hearingDetails");
        documentDetailsSheet = workbook.getSheet("documentDetails");
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
    public RevisionPetitionDetails getRevisionPetitionDetails(String revisionPetitionDataId) {
        Row dataRow = readDataRow(revisionPetitionDetailsSheet, revisionPetitionDataId);
        Cell revisionPetitionDetailsCell = getCellData(revisionPetitionDetailsSheet, dataRow, "revisionPetitionDetails");
        revisionPetitionDetailsCell.setCellType(Cell.CELL_TYPE_STRING);
        String revisionPetitionDetails = revisionPetitionDetailsCell.getStringCellValue();

        return new RevisionPetitionDetailsBuilder()
                .withRevisionPetitionDetail(revisionPetitionDetails)
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
