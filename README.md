# CSVparser

This is a csv Parser which can parse any of the format given in the task with a label attribute. 
The code can seperate between numeric and non numeric attributes and returns median for numeric attributes grouped by Label. 
It supports both a REST service and web UI interface. 

## Assumptions
1. The format of the files remain the same. (can handle variable attributes of different data types)
2. label column should be always present.

## Running the code

The code can be run easily by executing the `./run.sh`. This file contains the mvn packaging and jar execution command. 
Once the server is running the client UI can be accessed at 

`http://localhost:8080/index`


The REST service can alone be executed with 
´/parser/filename/{fileName}/query/{query}´

where {fileName} is the location of the file along with .csv extension and {query} is the name of the label whose data is to eb verified. 

The UI shows the log of all queries along with filename and results on the same page. 
The same log is maintained in a Results.json file in the project folder.


## Technology Stack

1. Streams (Java 8) to group and calculate median.
2. Maven to package the code. 
3. SpringBoot for the web service. 
4. Gson, univocity for json and csv parsing. 


In case of bigger files the Spark Sql with DataFrames structures can be used to achieve the goal. But for these size of files, 
Streams are a faster choice. 
