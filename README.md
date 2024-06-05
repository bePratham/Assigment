<h2>Spring Boot Backend Application</h2>
<h3>Overview</h3>
This backend application is built using Spring Boot 3.3.0. It provides APIs to manage student data and supports basic operations such as retrieving a greeting message, uploading student data manually or via a CSV file, and fetching student details based on roll numbers. Swagger UI is integrated for easy API testing and documentation.

<h3>API Endpoints</h3>
The base URL for all APIs is http://localhost:8080/api/students.

1. GET /
   Description: Returns a simple "Hello World" message.
   Example Request:
   http

   GET http://localhost:8080/api/students/
   Example Response:
   json

   {
   "message": "Hello World"
   }

2. POST /upload-manual
   Description: Stores a predefined list of students in the backend. No body or parameters are required.
   Example Request:
   http

   POST http://localhost:8080/api/students/upload-manual
   Example Response:
   json

   {
   "message": "Students uploaded manually"
   }

3. GET /{rollNumber}
   Description: Returns the details of a student with the given roll number. If the roll number doesn't exist, it returns "N/A".
   Parameters:
   rollNumber (path parameter): The roll number of the student.
   Example Request:
   http

   GET http://localhost:8080/api/students/12345
   Example Response (if student exists):
   json

{
"rollNumber": "1",
"studentName": "John Doe",
"science": 85,
"maths": 90,
"english": 88,
"computer": 92,
"eligible": "NO"
}
Example Response (if student does not exist):
json

{
"rollNumber": "123",
"studentName": "N/A",
"science": 0,
"maths": 0,
"english": 0,
"computer": 0,
"eligible": "N/A"
}

4. POST /upload
   Description: Accepts a CSV file containing student data and saves it in the backend.
   Request Body: A CSV file with the required student data.
   Example Request:
   http

   POST http://localhost:8080/api/students/upload
   Content-Type: multipart/form-data
   Example Response:
   json

   {
   "message": "CSV file uploaded successfully"
   }
   <h3>Swagger UI </h3> 
   Swagger UI is available for easy testing and documentation of the APIs. Once the server is running, you can access it at:

<h3>Swager url</h3>
http://localhost:8080/swagger-ui/index.html

Running the Application
To run the application, make sure you have Java and Maven installed. Then, follow these steps:

Clone the repository.
Navigate to the project directory.
Run the application using Maven:
sh

mvn spring-boot:run
After starting the server, you can test the APIs using the provided URLs and Swagger UI.

Conclusion
This backend application demonstrates a simple Spring Boot setup with four main APIs for managing student data. Swagger UI integration allows for easy testing and exploration of the API endpoints.
