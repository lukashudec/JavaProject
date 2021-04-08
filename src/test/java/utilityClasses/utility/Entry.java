package utilityClasses.utility;

import io.cucumber.datatable.DataTable;

import java.util.List;

public class Entry {
    public List<List<String>> data;

    public Entry(DataTable table, Boolean header) {
        List<List<String>> l = table.asLists();
        if (header) {
            this.data = l.subList(1, l.size());
        } else {
            this.data = l;
        }
    }

    public List<String> getRow(int index) {
        return data.get(index);
    }
}
