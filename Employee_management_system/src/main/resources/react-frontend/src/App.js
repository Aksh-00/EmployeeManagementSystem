import './App.css';
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom'
import ListEmployees from './Components/ListEmployees';
import HeaderComponent from './Components/HeaderComponent';
import AddOrUpdateEmployee from './Components/AddOrUpdateEmployee';

function App() {
  return (
    <div className="App">
      <Router>
      <HeaderComponent/>
      <div className="container">
      <Switch>
        <Route exact path="/"><ListEmployees/></Route>
        <Route path="/employees"><ListEmployees/></Route>
        <Route path="/add-employee"><AddOrUpdateEmployee/></Route>
        <Route path="/update-employee/:id"><AddOrUpdateEmployee/></Route>
      </Switch>
      </div>

      </Router>
      </div>
  );
}

export default App;
