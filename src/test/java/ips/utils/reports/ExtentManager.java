package ips.utils.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("Informe de pruebas");
        extentReports.attachReporter(reporter);
        //extentReports.setSystemInfo("Blog Name", "SW Test Academy");
        //extentReports.setSystemInfo("Author", "Onur Baskirt");
        return extentReports;
    }
}
