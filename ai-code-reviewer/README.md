# AI Code Reviewer

A full-stack AI-powered code reviewer application that analyzes your code and provides intelligent feedback including error detection, improvement suggestions, complexity analysis, and optimized code.

## Tech Stack

### Backend
- **Spring Boot 3.2.0** - Java framework for REST API
- **Maven** - Dependency management
- **Lombok** - Reduce boilerplate code
- **Spring DevTools** - Hot reload for development
- **OpenAI API** - AI-powered code analysis

### Frontend
- **React 18** - UI framework
- **Vite** - Build tool and dev server
- **Axios** - HTTP client for API calls
- **CSS** - Modern styling with dark mode support

## Features

- 🤖 AI-powered code analysis using OpenAI GPT-4
- 📝 Support for multiple programming languages (Java, Python, C++, JavaScript)
- 🔍 Error detection and bug finding
- 💡 Code improvement suggestions
- ⏱️ Time complexity analysis
- 💾 Space complexity analysis
- ⚡ Optimized code suggestions
- 🌙 Dark/Light mode toggle
- 📋 Copy response to clipboard
- 📱 Responsive design for mobile devices
- ⏳ Loading spinner during API calls
- 🎨 Modern AI SaaS-like UI

## Project Structure

```
ai-code-reviewer/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/aicodereviewer/
│   │   │   │   ├── AiCodeReviewerApplication.java
│   │   │   │   ├── config/
│   │   │   │   │   └── CorsConfig.java
│   │   │   │   ├── controller/
│   │   │   │   │   └── CodeReviewController.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── CodeReviewRequest.java
│   │   │   │   │   ├── CodeReviewResponse.java
│   │   │   │   │   ├── OpenAIRequest.java
│   │   │   │   │   └── OpenAIResponse.java
│   │   │   │   └── service/
│   │   │   │       └── CodeReviewService.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   └── pom.xml
└── frontend/
    ├── index.html
    ├── package.json
    ├── vite.config.js
    └── src/
        ├── App.jsx
        ├── App.css
        └── main.jsx
```

## Prerequisites

Before running this application, ensure you have:

1. **Java 17 or higher** - [Download here](https://www.oracle.com/java/technologies/downloads/)
2. **Maven** - [Download here](https://maven.apache.org/download.cgi)
3. **Node.js 18 or higher** - [Download here](https://nodejs.org/)
4. **OpenAI API Key** - Get one from [OpenAI Platform](https://platform.openai.com/api-keys)

## Setup Instructions

### 1. Clone or Navigate to Project

```bash
cd ai-code-reviewer
```

### 2. Backend Setup

#### Configure OpenAI API Key

Navigate to the backend resources folder:

```bash
cd backend/src/main/resources
```

Open `application.properties` and replace `YOUR_OPENAI_API_KEY` with your actual OpenAI API key:

```properties
openai.api.key=sk-your-actual-api-key-here
```

#### Build and Run Backend

Navigate to the backend directory:

```bash
cd ../../..
```

Build the project with Maven:

```bash
cd backend
mvn clean install
```

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

You should see:
```
AI Code Reviewer Backend started successfully!
Server running on http://localhost:8080
```

### 3. Frontend Setup

#### Install Dependencies

Navigate to the frontend directory:

```bash
cd ../frontend
```

Install npm dependencies:

```bash
npm install
```

#### Run Frontend

Start the Vite development server:

```bash
npm run dev
```

The frontend will start on `http://localhost:5173`

You should see:
```
  VITE v5.0.8  ready in xxx ms

  ➜  Local:   http://localhost:5173/
```

## Running the Application

### Option 1: Run Both Separately (Recommended for Development)

**Terminal 1 - Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Terminal 2 - Frontend:**
```bash
cd frontend
npm run dev
```

### Option 2: Run Backend Only (If Frontend is Already Built)

```bash
cd backend
mvn spring-boot:run
```

Then open `http://localhost:5173` in your browser (if frontend is already running)

## Using the Application

1. Open your browser and navigate to `http://localhost:5173`
2. Select your programming language from the dropdown (Java, Python, C++, JavaScript)
3. Paste your code into the textarea
4. Click the "🔍 Review Code" button
5. Wait for the AI analysis (loading spinner will appear)
6. View the results including:
   - Summary
   - Errors detected
   - Improvement suggestions
   - Time complexity
   - Space complexity
   - Optimized code
7. Click "📋 Copy" to copy the entire response to clipboard
8. Toggle dark/light mode using the button in the header
9. Click "🗑️ Clear" to reset and review new code

## API Endpoints

### POST /api/review

Reviews the provided code using AI.

**Request Body:**
```json
{
  "code": "your code here",
  "language": "java"
}
```

**Response:**
```json
{
  "errors": "List of errors",
  "improvements": "Improvement suggestions",
  "timeComplexity": "Time complexity analysis",
  "spaceComplexity": "Space complexity analysis",
  "optimizedCode": "Optimized version",
  "summary": "Overall summary"
}
```

### GET /api/health

Health check endpoint.

**Response:**
```
AI Code Reviewer API is running
```

## Configuration

### Backend Configuration (application.properties)

```properties
# Server Port
server.port=8080

# OpenAI API Configuration
openai.api.key=YOUR_OPENAI_API_KEY
openai.api.url=https://api.openai.com/v1/chat/completions
openai.api.model=gpt-4

# CORS Configuration
cors.allowed.origins=http://localhost:5173
```

### Frontend Configuration (vite.config.js)

```javascript
server: {
  port: 5173,
  proxy: {
    '/api': {
      target: 'http://localhost:8080',
      changeOrigin: true,
    }
  }
}
```

## Troubleshooting

### Backend Issues

**Issue:** "Connection refused" when calling API
- **Solution:** Make sure the backend is running on port 8080
- Check that Maven build was successful

**Issue:** OpenAI API errors
- **Solution:** Verify your API key is correct in `application.properties`
- Ensure you have credits in your OpenAI account
- Check that the API URL is correct

### Frontend Issues

**Issue:** "Failed to review code" error
- **Solution:** Ensure backend is running on http://localhost:8080
- Check browser console for detailed error messages

**Issue:** Dependencies not installing
- **Solution:** Try deleting `node_modules` and `package-lock.json`, then run `npm install` again

**Issue:** Port already in use
- **Solution:** Change the port in `vite.config.js` or kill the process using the port

## Development

### Backend Development

The backend uses Spring Boot DevTools for hot reload. Changes to Java files will automatically recompile.

### Frontend Development

Vite provides hot module replacement (HMR). Changes to React components will automatically update in the browser.

## Building for Production

### Backend

```bash
cd backend
mvn clean package
java -jar target/ai-code-reviewer-1.0.0.jar
```

### Frontend

```bash
cd frontend
npm run build
```

The built files will be in the `dist/` directory.

## Security Notes

- Never commit your OpenAI API key to version control
- Use environment variables for sensitive configuration in production
- The API key is stored in `application.properties` - add this to `.gitignore`
- Consider implementing rate limiting for the API endpoint in production

## License

This project is for educational purposes.

## Support

If you encounter any issues or have questions, please check the troubleshooting section above.

## Future Enhancements

- User authentication
- Code review history
- Support for more programming languages
- Code comparison feature
- Export reviews as PDF
- Integration with GitHub/GitLab
- Real-time collaborative code review
