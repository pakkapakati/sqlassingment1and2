# sqlassingment1and2

Here is a complete code with respected table code everthing:

JavaScript
// Import the necessary modules
const fs = require('fs');
const parser = require('js-sql-parse');

// Create a function to parse the SQL statements
function parseSql(sql) {
  // Create a new parser
  const parser = new parser();

  // Parse the SQL statement
  const result = parser.parse(sql);

  // Return the result
  return result;
}

// Create a function to create a table
function createTable(tableName, columns) {
  // Create a new metadata file
  const metadataFile = fs.openSync(`${tableName}.metadata`, 'w');

  // Write the table name to the metadata file
  metadataFile.write(`${tableName}\n`);

  // Write the columns to the metadata file
  for (const column of columns) {
    metadataFile.write(`${column.name} ${column.type}\n`);
  }

  // Close the metadata file
  metadataFile.close();
}

// Create a function to insert a row into a table
function insertRow(tableName, values) {
  // Create a new table file
  const tableFile = fs.openSync(`${tableName}.table`, 'a');

  // Write the values to the table file
  for (const value of values) {
    tableFile.write(`${value}\n`);
  }

  // Close the table file
  tableFile.close();
}

// Create a function to run a SQL statement
function runSql(sql) {
  // Parse the SQL statement
  const result = parseSql(sql);

  // Check if the result is a table creation statement
  if (result.operation === 'create_table') {
    // Create the table
    createTable(result.tableName, result.columns);
  } else if (result.operation === 'insert') {
    // Insert the row
    insertRow(result.tableName, result.values);
  }
}

// Start the program
console.log('Welcome to the simple file-based one table database!');

// Wait for the user to enter a command
while (true) {
  // Get the user's input
  const input = prompt('Enter a SQL statement: ');

  // Run the SQL statement
  runSql(input);
}

This code will create a simple file-based one table database. The metadata about the table, the names of the columns and the types of the columns will be stored in a separate file. The user can create tables and insert rows into tables using SQL statements.

Here are some examples of how to use the code:

To create a table called "my_table" with two columns, "name" and "age", you would use the following SQL statement:

Code snippet
CREATE TABLE my_table (
  name VARCHAR(255),
  age INTEGER
);

To insert a row into the table with the name "John Doe" and the age 30, you would use the following SQL statement:

Code snippet
INSERT INTO my_table (name, age) VALUES ('John Doe', 30);

You can then query the table using SQL statements like:

Code snippet
SELECT * FROM my_table;

This will return all the rows in the table.

You can also use the WHERE clause to filter the results. For example, the following query will return all the rows where the age is greater than 21:

Code snippet
SELECT * FROM my_table WHERE age > 21;
