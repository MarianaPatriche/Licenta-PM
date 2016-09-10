package mariana.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mariana.model.report.ReportCell;
import mariana.model.report.ReportCellText;
import mariana.model.report.ReportModel;
import mariana.model.report.ReportRow;
import mariana.model.report.ReportSheet;

/**
 * Created by mariana on 05.09.2016.
 */
public class ReportMapper {
	private static final String BASE_REQUEST_SHEET_NAME = "Raport cereri";
	private static final String BASE_REQUEST_SHEET_TITLE = "Raport cereri - ";
	private static final String BASE_REQUEST_REPORT_NAME = "Raport cereri - ";
	private static final String MAINTENANCE_REQUEST_SHEET_NAME = "Raport cereri mentenanta";
	private static final String MAINTENANCE_REQUEST_SHEET_TITLE = "Raport cereri mentenanta - ";
	private static final String MAINTENANCE_REQUEST_REPORT_NAME = "Raport cereri mentenanta - ";
	private static final String EXCEEDED_SLA = "DA";
	private static final String WITHIN_SLA = "NU";

	private static final ReportRow BASE_REQUEST_HEADERS_ROW = new ReportRow(Arrays.asList(
			getHeaderCell(ReportCell.CellWidth.XSMALL, "Nr"),
			getHeaderCell(ReportCell.CellWidth.MEDIUM, "Iniţiator"),
			getHeaderCell(ReportCell.CellWidth.LARGE, "Motiv"),
			getHeaderCell(ReportCell.CellWidth.MEDIUM, "Tipul cererii"),
			getHeaderCell(ReportCell.CellWidth.MEDIUM, "Data"),
			getHeaderCell(ReportCell.CellWidth.SMALL, "Status"),
			getHeaderCell(ReportCell.CellWidth.MEDIUM, "Aprobator curent"),
			getHeaderCell(ReportCell.CellWidth.SMALL, "SLA depăşit"),
			getHeaderCell(ReportCell.CellWidth.SMALL, "Preţ")
	));

	private static final ReportRow MAINTENANCE_REQUEST_HEADERS_ROW = new ReportRow(Arrays.asList(
			getHeaderCell(ReportCell.CellWidth.XSMALL, "Nr"),
			getHeaderCell(ReportCell.CellWidth.MEDIUM, "Iniţiator"),
			getHeaderCell(ReportCell.CellWidth.LARGE, "Motiv"),
			getHeaderCell(ReportCell.CellWidth.LARGE, "Echipament"),
			getHeaderCell(ReportCell.CellWidth.LARGE, "Magazin"),
			getHeaderCell(ReportCell.CellWidth.MEDIUM, "Data"),
			getHeaderCell(ReportCell.CellWidth.SMALL, "Status"),
			getHeaderCell(ReportCell.CellWidth.MEDIUM, "Aprobator curent"),
			getHeaderCell(ReportCell.CellWidth.SMALL, "SLA depăşit")
	));

	private static ReportCell getHeaderCell(final ReportCell.CellWidth cellWidth, final String text) {
		return new ReportCell(cellWidth, new ReportCellText(text, ReportCellText.TextColor.BLACK, ReportCellText.TextWeight.BOLD));
	}

	private static ReportRow mapReportRowForBaseRequest(final int number/*, final BaseRequest baseRequest*/) {
		/*return ReportRow.of(ReportCell.of(number), ReportCell.of(baseRequest.getInitiator()), ReportCell.of(baseRequest.getReason()), ReportCell.of(getBaseRequestType(baseRequest.getRequestType())),
				ReportCell.of(baseRequest.getCreatedDate().toString("dd/MM/yyyy")), ReportCell.of(getBaseRequestStatusType(baseRequest.getStatus())), ReportCell.of(baseRequest.getAssignedUser()),
				ReportCell.of(baseRequest.getOutdatedDays() != null ? (baseRequest.getOutdatedDays() > 0 ? EXCEEDED_SLA : WITHIN_SLA) : WITHIN_SLA),
				ReportCell.of(baseRequest.getPrice() != null ? baseRequest.getPrice().toString() : "-"));*/
		return new ReportRow(new ArrayList<ReportCell>());
	}

	private static ReportSheet mapReportSheetForBaseRequest(/*List<BaseRequest> baseRequests*/) {
		List<ReportRow> reportRows = new ArrayList<>();
		int entryNo = 1;
	/*	for (BaseRequest request : baseRequests) {
			reportRows.add(mapReportRowForBaseRequest(entryNo, request));
			entryNo++;
		}*/
		return new ReportSheet(BASE_REQUEST_SHEET_NAME, BASE_REQUEST_SHEET_TITLE /*+ LocalDateTime.now().toString("dd/MM/yyyy hh:mm")*/, BASE_REQUEST_HEADERS_ROW, reportRows);
	}


	public static ReportModel mapReportForBaseRequest(/*List<BaseRequest> baseRequests*/) {
		return new ReportModel(BASE_REQUEST_REPORT_NAME /*+  LocalDateTime.now().toString("dd-MM-yyyy hh:mm")*/, mapReportSheetForBaseRequest(/*baseRequests*/));
	}

}
