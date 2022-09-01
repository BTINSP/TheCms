import React from 'react';
import {Route, Routes,BrowserRouter} from "react-router-dom";

import Home from "./pages/Home/Home"
import Login from "./pages/Home/Login"

function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path={'/manage/*'} element={<Home />}></Route>
              <Route path={'/manage/login'} element={<Login />}></Route>
          </Routes>
      </BrowserRouter>
  );
}

export default App;
