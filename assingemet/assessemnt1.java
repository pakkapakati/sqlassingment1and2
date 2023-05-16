import java.io.FileWriter;
import java.io.IOException;

public class FileDatabase {
    // Define the metadata file
    private static final String metadata_file = "metadata.csv";

    // Define the table file
    private static final String table_file = "table.csv";

    // Parse the CREATE TABLE statement
    public static void create_table(String sql) throws IOException {
        // Extract the column names and data types from the SQL statement
        String[] columns = sql.split("\\(")[1].split("\\)")[0].split(",");
        // Write the metadata to the metadata file
        FileWriter writer = new FileWriter(metadata_file);
        writer.write("column_name,data_type\n");
        for (String col : columns) {
            String[] tokens = col.trim().split("\\s+");
            writer.write(tokens[0] + "," + tokens[1] + "\n");
        }
        writer.close();
    }

    // Parse the INSERT INTO statement
    public static void insert_into(String sql) throws IOException {
        // Extract the values from the SQL statement
        String[] values = sql.split("\\(")[1].split("\\)")[0].split(",");
        // Write the values to the table file
        FileWriter writer = new FileWriter(table_file, true);
        writer.write(String.join(",", values) + "\n");
        writer.close();
    }

    // Example usage
    public static void main(String[] args) throws IOException {
        create_table("CREATE TABLE mytable (id INTEGER, name STRING)");
        insert_into("INSERT INTO mytable VALUES (1, \"John\")");
        insert_into("INSERT INTO mytable VALUES (2, \"Jane\")");
    }
}

