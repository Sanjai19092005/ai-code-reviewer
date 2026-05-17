import { useState } from 'react'
import axios from 'axios'
import './App.css'

function App() {
  const [code, setCode] = useState('')
  const [language, setLanguage] = useState('java')
  const [response, setResponse] = useState(null)
  const [loading, setLoading] = useState(false)
  const [darkMode, setDarkMode] = useState(true)
  const [error, setError] = useState('')

  // Handle code review
  const handleReview = async () => {
    if (!code.trim()) {
      setError('Please enter some code to review')
      return
    }

    setLoading(true)
    setError('')
    setResponse(null)

    try {
      const res = await axios.post('http://localhost:8080/api/review', {
        code: code,
        language: language
      })
      setResponse(res.data)
    } catch (err) {
      setError('Failed to review code. Please make sure the backend is running.')
      console.error('Error:', err)
    } finally {
      setLoading(false)
    }
  }

  // Copy response to clipboard
  const copyToClipboard = () => {
    const text = `
SUMMARY:
${response.summary}

ERRORS:
${response.errors}

IMPROVEMENTS:
${response.improvements}

TIME COMPLEXITY:
${response.timeComplexity}

SPACE COMPLEXITY:
${response.spaceComplexity}

OPTIMIZED CODE:
${response.optimizedCode}
    `
    navigator.clipboard.writeText(text)
    alert('Response copied to clipboard!')
  }

  // Clear all
  const handleClear = () => {
    setCode('')
    setResponse(null)
    setError('')
  }

  return (
    <div className={`app ${darkMode ? 'dark' : 'light'}`}>
      <div className="container">
        {/* Header */}
        <header className="header">
          <div className="header-content">
            <h1 className="title">🤖 AI Code Reviewer</h1>
            <p className="subtitle">Get intelligent code reviews powered by AI</p>
          </div>
          <button 
            className="theme-toggle"
            onClick={() => setDarkMode(!darkMode)}
            aria-label="Toggle dark mode"
          >
            {darkMode ? '☀️' : '🌙'}
          </button>
        </header>

        {/* Main Content */}
        <main className="main-content">
          {/* Input Section */}
          <div className="card input-section">
            <div className="card-header">
              <h2>📝 Your Code</h2>
              <select 
                className="language-select"
                value={language}
                onChange={(e) => setLanguage(e.target.value)}
              >
                <option value="java">Java</option>
                <option value="python">Python</option>
                <option value="cpp">C++</option>
                <option value="javascript">JavaScript</option>
              </select>
            </div>
            
            <textarea
              className="code-textarea"
              placeholder="Paste your code here..."
              value={code}
              onChange={(e) => setCode(e.target.value)}
              spellCheck="false"
            />
            
            <div className="button-group">
              <button 
                className="btn btn-primary"
                onClick={handleReview}
                disabled={loading}
              >
                {loading ? '⏳ Reviewing...' : '🔍 Review Code'}
              </button>
              <button 
                className="btn btn-secondary"
                onClick={handleClear}
                disabled={loading}
              >
                🗑️ Clear
              </button>
            </div>

            {error && <div className="error-message">{error}</div>}
          </div>

          {/* Response Section */}
          {response && (
            <div className="card response-section">
              <div className="card-header">
                <h2>📊 Review Results</h2>
                <button 
                  className="btn-copy"
                  onClick={copyToClipboard}
                  aria-label="Copy response"
                >
                  📋 Copy
                </button>
              </div>

              <div className="response-content">
                {response.summary && (
                  <div className="response-item">
                    <h3>📌 Summary</h3>
                    <p>{response.summary}</p>
                  </div>
                )}

                {response.errors && (
                  <div className="response-item">
                    <h3>❌ Errors</h3>
                    <p>{response.errors}</p>
                  </div>
                )}

                {response.improvements && (
                  <div className="response-item">
                    <h3>💡 Improvements</h3>
                    <p>{response.improvements}</p>
                  </div>
                )}

                {response.timeComplexity && (
                  <div className="response-item">
                    <h3>⏱️ Time Complexity</h3>
                    <p>{response.timeComplexity}</p>
                  </div>
                )}

                {response.spaceComplexity && (
                  <div className="response-item">
                    <h3>💾 Space Complexity</h3>
                    <p>{response.spaceComplexity}</p>
                  </div>
                )}

                {response.optimizedCode && (
                  <div className="response-item">
                    <h3>⚡ Optimized Code</h3>
                    <pre className="code-block">{response.optimizedCode}</pre>
                  </div>
                )}
              </div>
            </div>
          )}
        </main>

        {/* Footer */}
        <footer className="footer">
          <p>Built with ❤️ using React, Spring Boot, and OpenAI</p>
        </footer>
      </div>
    </div>
  )
}

export default App
