Sever port: 8888
example request: http://localhost:8888/data   -gets one data
End points:
/data - get a single data from the API and save it
/data/{n} - get n data from the API
/getData/{id} - retriev data from this API
/getAllData - retriev all data from this API
NOTE:
-All Exceptions are handled using One controller advice class
-I moved the URL to properties
-I added log4j logger
-I am auto generating the id for the tables, 
so when you request second time to the External API you may generating
the same id and get SQLException. 


