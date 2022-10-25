import axios from 'axios'
import React,{useState,useEffect} from 'react'
import {Link} from 'react-router-dom'
import EmployeeService from '../Services/EmployeeService'
// import EmployeeService from '../Services/EmployeeService'
const ListEmployees = () => {

  const [employees, setEmployees] = useState([])
  
  const [searchTerm,setSearchTerm]=useState("")
  const [searchResults,setSearchResults]=useState([])
  
  useEffect(() => {
        console.log(searchTerm)
        getAllEmployees();
    }, [])
  const getAllEmployees = () => {
    EmployeeService.getAllEmployees()      
    .then((response) => {
          setEmployees(response.data)
          console.log(response.data);
          setTimeout(()=>console.log(1),2000)
          console.log(2);
          setTimeout(()=>console.log(3),0)
          console.log(4);
          
      }).catch(error =>{
          console.log(error);
      })
  }
    
  const deleteEmployee=(employeeId)=>{
    EmployeeService.deleteEmployee(employeeId)
    .then((response)=>{
      console.log(response.data)
      getAllEmployees();
    })
    .catch((error)=>{
      console.log(error)
    })

  }
  
  return (
    <div className = "container">
    <h2 className = "text-center"> List Employees </h2>
    <div class="findButton1">
      <Link to="/add-employee" className='btn btn-primary mb-2'>Add Employee</Link>
    </div>

    {/* Search By Id */}
    <div className="input-group">
      <input type="search" className="form-control rounded" placeholder="Enter ID to search"
      aria-label="Search" aria-describedby="search-addon"
      id='searchId'
      value={searchTerm}
      onChange={(e)=>setSearchTerm(e.target.value)}
      /> 
      {/* <button type="button" class="btn btn-primary mb-2" onClick={searchByIdHandler}>Search</button> */}
    </div>

    <table className="table table-bordered table-striped">
        <thead>
            <th> Employee Id </th>
            <th> Name</th>
            <th> Date of Joining</th>
            <th> Basic Pay</th>
            <th> DA </th>
            <th> HRA</th>
            <th> Gross Salary</th>
            <th> Tax</th>
            <th> Net Salary</th>
            <th>Action</th>
        </thead>
        <tbody>
            {
                
                    employees.filter((val)=>{
                      if(searchTerm==="")
                      {
                        return val;
                      }
                      else if
                      (val.id.toString().includes(searchTerm.toLowerCase())||val.name.toLowerCase().includes(searchTerm.toLowerCase))
                      {
                        // console.log(val.id+" "+searchTerm);
                        return val;
                      }
                      
                    }).map(
                    employee =>
                    <tr key = {employee.id}> 
                        <td> {employee.id} </td>
                        <td> {employee.name} </td>
                        <td>{employee.dateOfJoining.replace('-', '/').split('T')[0].replace('-', '/')}</td>
                        <td>{employee.basicPay}</td>
                        <td>{employee.da}</td>
                        <td>{employee.hra}</td>
                        <td>{employee.grossSalary}</td>
                        <td>{employee.tax}</td>
                        <td>{employee.net}</td>
                        <td><Link to={`/update-employee/${employee.id}`} className='btn btn-primary mb-2'>Update</Link>
                        <button className='btn btn-danger' style={{marginLeft:"10px"}} onClick={()=>deleteEmployee(employee.id)}>Delete</button>
                        </td>
                    </tr>
                )
            }
        </tbody>
    </table>
</div>
  )
}

export default ListEmployees