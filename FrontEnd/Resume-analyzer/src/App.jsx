import { Routes, Route } from "react-router-dom";

import SignIn from "./Components/SignIn"
import SignUp from "./Components/SignUp"


function App() {

  return (
    <>
      <Routes>
        <Route path="/signup" Component={SignUp} />
        <Route path="/signin" Component={SignIn} />
      </Routes>
    </>
  )
}

export default App
