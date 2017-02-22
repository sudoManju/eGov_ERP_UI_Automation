package excelDataFiles;

import builders.marriageRegistration.MarriageRegistrationBuilder;
import entities.marriageRegistration.MarriageRegistrationInformation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class MarriageRegistrationDataReader extends ExcelReader {

    Sheet applicantsInformationSheet;
    Sheet bridegroomInformationSheet;

    public MarriageRegistrationDataReader(String testData) {
        super(testData);
        applicantsInformationSheet = workbook.getSheet("applicantsInformation");
        bridegroomInformationSheet = workbook.getSheet("bridegroomInformation");
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
