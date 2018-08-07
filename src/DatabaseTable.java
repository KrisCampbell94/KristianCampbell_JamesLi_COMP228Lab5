import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DatabaseTable extends AbstractTableModel {
    private int rows;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;

    public DatabaseTable(String query) throws Exception{
        setQuery(query);
    }
    public void setQuery(String query) throws Exception{
        resultSet = DatabaseConnection.getRs();
        metaData = DatabaseConnection.getMd();
        rows = DatabaseConnection.setQueryToTable(query);
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        try {
            return metaData.getColumnCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            resultSet.absolute(rowIndex+1);
            return resultSet.getObject(columnIndex+1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Class getColumnClass(int column) {
        String className = null;
        try {
            className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Object.class;
    }
    public String getColumnName(int column) {
        try {
            return metaData.getColumnName(column+1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
