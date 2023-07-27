import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import NewsletterComponent from './component/NewsletterComponent'
import SuccessComponent from './component/SuccessComponent'

function App() {
  return (
    <>
    <BrowserRouter>
    <Routes>
      <Route path='/' element={<NewsletterComponent />} />
      <Route path='/success' element={<SuccessComponent />} />
    </Routes>
    </BrowserRouter>
    </>
  )
}

export default App
