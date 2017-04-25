package excelDataFiles;

import builders.employeeManagement.AssignmentDetailsBuilder;
import entities.employeeManagement.AssignmentDetails;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class EmployeeManagementDetailsDataReader extends ExcelReader {

    Sheet assignmentDetailsSheet;

    public EmployeeManagementDetailsDataReader(String testData) {
        super(testData);
        assignmentDetailsSheet = workbook.getSheet("assignmentDetails");
    }

    public AssignmentDetails getAssignmentDetails(String dataName) {
        Row dataRow = readDataRow(assignmentDetailsSheet, dataName);

        String isPrimary = getCellData(assignmentDetailsSheet, dataRow, "IsPrimary").getStringCellValue();
        String fromDate = convertNumericToString(assignmentDetailsSheet, dataRow, "FromDate");
        String toDate = convertNumericToString(assignmentDetailsSheet, dataRow, "ToDate");
        String mainDepartment = getCellData(assignmentDetailsSheet, dataRow, "MainDepartment").getStringCellValue();
        String designation = getCellData(assignmentDetailsSheet, dataRow, "Designation").getStringCellValue();
        String position = getCellData(assignmentDetailsSheet, dataRow, "Position").getStringCellValue();

        return new AssignmentDetailsBuilder()
                .withIsPrimary(isPrimary)
                .withFromDate(fromDate)
                .withToDate(toDate)
                .withDepartment(mainDepartment)
                .withDesignation(designation)
                .withPosition(position)
                .build();
    }
}
