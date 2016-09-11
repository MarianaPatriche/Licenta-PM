package mariana.mapper;

import mariana.entity.WorkDay;
import mariana.model.report.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mariana on 05.09.2016.
 */
public class ReportMapper {
	private static final String BASE_REQUEST_SHEET_NAME = "Raport ";
	private static final String BASE_REQUEST_SHEET_TITLE = "Raport  - ";
	private static final String BASE_REQUEST_REPORT_NAME = "Raport  - ";
	private static final String MAINTENANCE_REQUEST_SHEET_NAME = "Raport cereri mentenanta";
	private static final String MAINTENANCE_REQUEST_SHEET_TITLE = "Raport cereri mentenanta - ";
	private static final String MAINTENANCE_REQUEST_REPORT_NAME = "Raport cereri mentenanta - ";
	private static final String EXCEEDED_SLA = "DA";
	private static final String WITHIN_SLA = "NU";

	private static final ReportRow BASE_REQUEST_HEADERS_ROW = new ReportRow(Arrays.asList(
			getHeaderCell(ReportCell.CellWidth.XSMALL, "Nr"),
			getHeaderCell(ReportCell.CellWidth.MEDIUM, "Prenume"),
			getHeaderCell(ReportCell.CellWidth.LARGE, "Nume"),
			getHeaderCell(ReportCell.CellWidth.LARGE, "Data"),
			getHeaderCell(ReportCell.CellWidth.LARGE, "Proiect"),
			getHeaderCell(ReportCell.CellWidth.MEDIUM, "Client"),
			getHeaderCell(ReportCell.CellWidth.SMALL, "Ore"),
			getHeaderCell(ReportCell.CellWidth.MEDIUM, "Detalii")
	));


	private static ReportCell getHeaderCell(final ReportCell.CellWidth cellWidth, final String text) {
		return new ReportCell(cellWidth, new ReportCellText(text, ReportCellText.TextColor.BLACK, ReportCellText.TextWeight.BOLD));
	}

	private static ReportRow mapReportRowForBaseRequest(final int number, final WorkDay workDay) {
		return ReportRow.of(ReportCell.of(number), ReportCell.of(workDay.getEmployee().getFirstName()), ReportCell.of(workDay.getEmployee().getLastName()), ReportCell.of(workDay.getDay().toString()),
				ReportCell.of(workDay.getProject().getName()), ReportCell.of(workDay.getProject().getClient()), ReportCell.of(workDay.getHours()),
				ReportCell.of(workDay.getDetails()));
	}

	private static ReportSheet mapReportSheetForBaseRequest(List<WorkDay> workDays) {
		List<ReportRow> reportRows = new ArrayList<>();
		int entryNo = 1;
		for (WorkDay request : workDays) {
			reportRows.add(mapReportRowForBaseRequest(entryNo, request));
			entryNo++;
		}
		return new ReportSheet(BASE_REQUEST_SHEET_NAME, BASE_REQUEST_SHEET_TITLE /*+ LocalDateTime.now().toString("dd/MM/yyyy hh:mm")*/, BASE_REQUEST_HEADERS_ROW, reportRows);
	}


	public static ReportModel mapReportForBaseRequest(List<WorkDay> workDays) {
		return new ReportModel(BASE_REQUEST_REPORT_NAME +  LocalDate.now().toString(), mapReportSheetForBaseRequest(workDays));
	}

}
