import React,{useState,useEffect} from 'react'
import {Link} from 'react-router-dom'
import EmployeeService from '../Services/EmployeeService'
// import EmployeeService from '../Services/EmployeeService'


const ListEmployees = () => {

  const [employees, setEmployees] = useState([])
  
  //variable to store search Term
  const [searchTerm,setSearchTerm]=useState("")
  
  useEffect(() => {
        console.log(searchTerm)
        getAllEmployees();
    }, [])

//function to list all the employees on home page
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
  
  //function to delete an employee by ID
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
      <br></br>
    <h2 className = "text-center"> List of Employees </h2>


    {/* Input field to Add new employee */}
    <div class="findButton1">
      <Link to="/add-employee" className='btn btn-primary mb-2'>Add Employee</Link>
    </div>

    {/* Input field to search employee by Id, Name or Date of Joining  */}
    <div className="input-group">
      <input type="search" className="form-control rounded" placeholder="Search Employee by ID, Name or Date Of Joining"
      aria-label="Search" aria-describedby="search-addon"
      id='search'
      value={searchTerm}
      onChange={(e)=>setSearchTerm(e.target.value)}
      /> 
      
    </div>

    {/* Table to display all the employee details */}
    <table className="table table-striped table-hover" >
        <thead class="p-3 mb-5 bg-dark text-white text-center">
            <th > Employee Id </th>
            <th > Name</th>
            <th > Date of Joining</th>
            <th > Basic Pay</th>
            <th > DA </th>
            <th > HRA</th>
            <th > Gross Salary</th>
            <th > Tax</th>
            <th > Net Salary</th>
            <th >Action</th>
        </thead>
        <tbody className='text-center'>
            {
                    //filter to search employee by Id,name,dateofjoining based on searchTerm
                    employees.filter((employee)=>{
                      if(searchTerm==="")
                      {
                        return employee;
                      }
                      else if(employee.id.toString().includes(searchTerm.toLowerCase())||
                      employee.name.toLowerCase().includes(searchTerm.toLowerCase())||
                      employee.dateOfJoining.replace('-', '/').split('T')[0].replace('-', '/').toLowerCase()
                      .includes(searchTerm.toLowerCase()))
                      {
                        
                        return employee;
                      }
                      return null;
                    }).map( 

                    //displays employee detail in tabular format
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
                        {/*<td><Link to={`/update-employee/${employee.id}`} className='btn btn-primary mb-2'>Update</Link>
                        <button className='btn btn-danger' style={{marginLeft:"10px"}} onClick={()=>deleteEmployee(employee.id)}>Delete</button>
                    */}

                    {/* buttons to update or delete an employee */}
                    <td><Link to={`/update-employee/${employee.id}`} className='btn btn-primary mb-2'><span class="bi bi-pencil-square"></span></Link>
                        <button type="button" class='btn btn-link' style={{marginLeft:"10px"}} onClick={()=>deleteEmployee(employee.id)}><span class="bi bi-trash-fill" style={{color:"red"}}></span></button>
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