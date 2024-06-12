package Others;

import terminalUtils.ColumnFormat;
import terminalUtils.TerminalUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Util {

    public static void errorLog() throws FileNotFoundException {
        File logFile = new File("errors.log");

        if (logFile.exists()) {
            logFile.delete();
        }
        PrintStream errorStream = new PrintStream(new FileOutputStream(logFile));
        System.setErr(errorStream);
    }

    public static String showResponse(ResultSet response) throws SQLException {

        if (response == null) return "Empty Set";

        ResultSetMetaData resData = response.getMetaData();

        int columnCount  = resData.getColumnCount();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<ColumnFormat> columnsFormat = new ArrayList<>();

        // extract data
        int[] maxLengthColumn = new int[columnCount];
        for (int i = 0; i < columnCount; i++) {
            maxLengthColumn[i] = resData.getColumnName(i+1).length();
        }

        int row = 0;
        while (response.next()) {
            ArrayList<String> rowData = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                String d = response.getObject(i) + "";
                rowData.add(d);
                if (d.length() > maxLengthColumn[i-1]) maxLengthColumn[i-1] = d.length();
            }
            data.add(rowData);
            row++;
        }

        for (int i = 1; i <= columnCount; i++) {
            columnsFormat.add(new ColumnFormat(maxLengthColumn[i-1], resData.getColumnName(i), ""));
        }

        return new TerminalUtils(columnsFormat).printTable(data, false);
    }
}
