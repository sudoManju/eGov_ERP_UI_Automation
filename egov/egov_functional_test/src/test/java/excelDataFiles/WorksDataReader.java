package excelDataFiles;

import builders.works.*;
import entities.works.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class WorksDataReader extends ExcelReader {

    Sheet estimateHeaderDetailsSheet;
    Sheet financialDetailsSheet;
    Sheet workDetailsSheet;
    Sheet adminSanctionDetailsSheet;
    Sheet technicalSanctionDetailsSheet;

    public WorksDataReader(String testData) {
        super(testData);
        estimateHeaderDetailsSheet = workbook.getSheet("estimateHeaderDetails");
        financialDetailsSheet = workbook.getSheet("financialDetails");
        workDetailsSheet = workbook.getSheet("workDetails");
        adminSanctionDetailsSheet = workbook.getSheet("adminSanctionDetails");
        technicalSanctionDetailsSheet = workbook.getSheet("technicalSanctionDetails");
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
}
