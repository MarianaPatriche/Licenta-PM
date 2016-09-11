package mariana.service;

import mariana.repository.WorkDayRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mariana.mapper.ReportMapper;
import mariana.model.report.ReportModel;

import static java.io.File.separator;

/**
 * Created by mariana on 05.09.2016.
 */
@Service
public class ReportService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

	//@Value("${report.path}")
	private String path = "/home/mariana/Desktop";

	@Autowired
	private CreateReportService createReportService;

	@Autowired
	private WorkDayRepository workDayRepository;


	private ReportModel generateReportForBaseRequest(String username, LocalDate startDate, LocalDate endDate) {
		LOGGER.info("Generate report!");
		return ReportMapper.mapReportForBaseRequest(workDayRepository.findByEmployeeUserUsernameAndDayBetween(username, startDate, endDate));
	}

	private String saveReport(final ReportModel report) throws IOException {
		LOGGER.info("Save report!");
		final File baseDir = new File(path + separator + "reports" + separator);
		if (!baseDir.exists()) {
			boolean created = baseDir.mkdirs();
			if (!created) {
				LOGGER.error("Could not create directory!");
			}
		}
		final String savePath = baseDir.getPath() + separator + report.getReportName() + ".xlsx";
		try (final Workbook workbook = createReportService.generateReport(report);
			 final FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath))) {
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		}
		return savePath;
	}

	public String generateAndSaveReportForBaseRequest(String username, LocalDate startDate, LocalDate endDate) {
		final ReportModel report = generateReportForBaseRequest(username, startDate, endDate);
		String savePath = "";
		try {
			savePath = saveReport(report);
		} catch (IOException io) {
			LOGGER.warn("Could not generate report! " + io);
		}
		return savePath;
	}

	public void downloadReport(String savePath, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ServletContext context = request.getServletContext();

		File downloadFile = new File(savePath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		String mimeType = context.getMimeType(savePath);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		OutputStream outStream = response.getOutputStream();

		byte[] buffer = Files.readAllBytes(downloadFile.toPath());
		int bytesRead;

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.close();
	}

}
