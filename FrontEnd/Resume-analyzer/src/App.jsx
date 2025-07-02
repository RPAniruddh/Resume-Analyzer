import { Routes, Route } from "react-router-dom";

import SignIn from "./Components/SignIn"
import SignUp from "./Components/SignUp"
import ResumeUpload from "./Components/ResumeUpload";


function App() {

  return (
    <>
      <Routes>
        <Route path="/signup" Component={SignUp} />
        <Route path="/signin" Component={SignIn} />
        <Route path="/upload" Component={ResumeUpload} />
      </Routes>
    </>
  )
}

export default App
