package excelDataFiles;

import builders.councilManagement.PreambleDetailsBuilder;
import entities.councilManagement.CreatePreambleDetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class CouncilManagementDataReader extends ExcelReader {

    Sheet createPreambleDetailsSheet;
    Sheet createAgendaSheet;
    Sheet createMeetingSheet;
    Sheet createCouncilMOMSheet;

    public CouncilManagementDataReader(String testData) {
        super(testData);
        createPreambleDetailsSheet = workbook.getSheet("createPreamble");
        createAgendaSheet = workbook.getSheet("createAgenda");
        createMeetingSheet = workbook.getSheet("createMeeting");
        createCouncilMOMSheet = workbook.getSheet("createCouncilMOM");
    }

    public CreatePreambleDetails getCreatePreambleDetails(String createPreambleData) {
        Row dataRow = readDataRow(createPreambleDetailsSheet, createPreambleData);

        Cell departmentCell = getCellData(createPreambleDetailsSheet, dataRow, "department");
        departmentCell.setCellType(Cell.CELL_TYPE_STRING);
        String preambleDepartment = departmentCell.getStringCellValue();

        Cell amountCell = getCellData(createPreambleDetailsSheet, dataRow,"amount");
        amountCell.setCellType(Cell.CELL_TYPE_STRING);
        String amount = amountCell.getStringCellValue();

        Cell gistOfPreambleCell = getCellData(createPreambleDetailsSheet,dataRow,"gistOfPreamble");
        gistOfPreambleCell.setCellType(Cell.CELL_TYPE_STRING);
        String gistOfPreamble = gistOfPreambleCell.getStringCellValue();

        return  new PreambleDetailsBuilder()
                .withPreambleDepartment(preambleDepartment)
                .withSanctionAmount(amount)
                .withGistOfPreamble(gistOfPreamble)
                .build();
    }

    public CreatePreambleDetails getCreateAgendaDetails(String createAgendaData) {
        Row dataRow = readDataRow(createAgendaSheet, createAgendaData);

        Cell committeeTypeCell =getCellData(createAgendaSheet, dataRow,"committeeType");
        committeeTypeCell.setCellType(Cell.CELL_TYPE_STRING);
        String committeeType=committeeTypeCell.getStringCellValue();

        return new PreambleDetailsBuilder()
                .withCommitteeType(committeeType)
                .build();
    }

    public CreatePreambleDetails getCreateMeetingDetails(String createMeetingDetails) {
        Row dataRow = readDataRow(createMeetingSheet, createMeetingDetails);

        Cell meetingDateCell = getCellData(createMeetingSheet, dataRow, "meetingDate");
        meetingDateCell.setCellType(Cell.CELL_TYPE_STRING);
        String meetingDate = meetingDateCell.getStringCellValue();

        Cell meetingTimeCell = getCellData(createMeetingSheet, dataRow, "meetingTime");
        meetingTimeCell.setCellType(Cell.CELL_TYPE_STRING);
        String meetingTime = meetingTimeCell.getStringCellValue();

        Cell meetingPlaceCell = getCellData(createMeetingSheet, dataRow, "meetingPlace");
        meetingPlaceCell.setCellType(Cell.CELL_TYPE_STRING);
        String meetingPlace = meetingPlaceCell.getStringCellValue();

        return new PreambleDetailsBuilder()
                .withCouncilMeetingDate(meetingDate)
                .withCouncilMeetingTime(meetingTime)
                .withCouncilMeetingPlace(meetingPlace)
                .build();
    }

    public CreatePreambleDetails getCouncilMOMDetails(String councilMOMData) {
        Row dataRow = readDataRow(createCouncilMOMSheet,councilMOMData);

        Cell resolutionCommentCell= getCellData(createCouncilMOMSheet, dataRow,"resolutionComments");
        resolutionCommentCell.setCellType(Cell.CELL_TYPE_STRING);
        String resolutionComment= resolutionCommentCell.getStringCellValue();

        Cell actionTakenCell= getCellData(createCouncilMOMSheet, dataRow, "actionTaken");
        actionTakenCell.setCellType(Cell.CELL_TYPE_STRING);
        String actionTaken= actionTakenCell.getStringCellValue();

        return new PreambleDetailsBuilder()
                .withCouncilMOMResolution(resolutionComment)
                .withCouncilMOMAction(actionTaken)
                .build();
    }
}
