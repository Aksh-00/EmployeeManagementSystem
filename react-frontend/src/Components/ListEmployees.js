import axios from 'axios'
import React,{useState,useEffect} from 'react'
import {Link} from 'react-router-dom'
import EmployeeService from '../Services/EmployeeService'
// import EmployeeService from '../Services/EmployeeService'
const ListEmployees = () => {

  const [employees, setEmployees] = useState([])

  useEffect(() => {
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

    <div class="input-group">
      <input type="search" class="form-control rounded" placeholder="Enter Name or ID" aria-label="Search" aria-describedby="search-addon" />
      <button type="button" class="btn btn-primary mb-2">search</button>
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
                employees.map(
                    employee =>
                    <tr key = {employee.id}> 
                        <td> {employee.id} </td>
                        <td> {employee.name} </td>
                        <td>{employee.dateOfJoining}</td>
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