package mariana.model.report;

import java.util.List;

/**
 * Created by mariana on 05.09.2016.
 */
public class ReportSheet {
	private final String name;
	private final String title;
	private final ReportRow headers;
	private final List<ReportRow> rowList;

	public ReportSheet(String name, String title, ReportRow headers, List<ReportRow> rowList) {
		this.name = name;
		this.title = title;
		this.headers = headers;
		this.rowList = rowList;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public ReportRow getHeaders() {
		return headers;
	}

	public List<ReportRow> getRowList() {
		return rowList;
	}

	@Override
	public String toString() {
		return "ReportSheet{" +
				"name='" + name + '\'' +
				", title='" + title + '\'' +
				", headers=" + headers +
				", rowList=" + rowList +
				'}';
	}
}
