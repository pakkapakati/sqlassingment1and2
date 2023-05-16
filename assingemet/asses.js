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