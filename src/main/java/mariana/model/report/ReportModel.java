package mariana.model.report;

/**
 * Created by mariana on 05.09.2016.
 */
public class ReportModel {
	private final String reportName;

	private final ReportSheet sheet;

	public ReportModel(String reportName, ReportSheet sheet) {
		this.reportName = reportName;
		this.sheet = sheet;
	}

	public String getReportName() {
		return reportName;
	}

	public ReportSheet getSheet() {
		return sheet;
	}

	@Override
	public String toString() {
		return "ReportModel{" +
				"reportName='" + reportName + '\'' +
				", sheet=" + sheet +
				'}';
	}
}
