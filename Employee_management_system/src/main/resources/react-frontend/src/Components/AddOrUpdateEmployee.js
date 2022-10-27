import axios from 'axios'
import React,{useState,useEffect} from 'react'
import EmployeeService from '../Services/EmployeeService'
import {useHistory,Link,useParams} from 'react-router-dom'

import DatePicker from 'react-datepicker';  
   
import "react-datepicker/dist/react-datepicker.css";  

const AddOrUpdateEmployee = () => 
{
    const [name,setName]=useState('')
    const [dateOfJoining,setDateOfJoining]=useState(new Date())
    const [basicPay,setBasicPay]=useState('')
    const history=useHistory();
    const {id}=useParams();
    
    useEffect(() => {
        //Fetches employee details to be updated using ID
        EmployeeService.getEmployeeById(id).then((response) =>{
            setName(response.data.name)
            setDateOfJoining(new Date(response.data.dateOfJoining))
            setBasicPay(response.data.basicPay)
            console.log(name+" "+dateOfJoining)
        }).catch(error => {
            console.log(error)
        })
    }, [])

    //function to add or update employee
    const AddOrUpdate=(e)=>{
        e.preventDefault();
        const employee={name,dateOfJoining,basicPay}
        console.log(employee);

        //updates the employee details if employee ID exits
        if(id)
        {
            EmployeeService.updateEmployee(id,employee)
            .then((response)=>{
                console.log("updated successfully")
                history.push("/employees")
            })
            .catch((error)=>{
                console.log(error)
            })
        }
        else
        {
        //if employee doesnot exists, employee will be added as new one
        EmployeeService.createEmployee(employee)
        .then(response=>{
            console.log(response.data)
            history.push("/employees")        
        })
        .catch(error=>{
            console.log(error)
        })
        }
    }

//function to change title dynamically    
const title=()=>{
    return id?<h2 className="text-center">Update Employee</h2>:<h2 className="text-center">Add Employee</h2>;
}


  return (
    <div>
           <br /><br />
           <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3 offset-md-3">
                       {
                        title()
                       }
                       
                        <div className = "card-body">
                            <form>
                                {/* Input field to get employee name */}
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Name :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter name"
                                        name = "name"
                                        className = "form-control"
                                        value={name}
                                        onChange = {(e) => setName(e.target.value)}
                                    >
                                    </input>
                                </div>

                                {/* Input field to get date of joining */}
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Date of Joining :</label>
                                    
                                    <DatePicker  
                                        selected={ dateOfJoining }  
                                        onChange={(date)=>setDateOfJoining(date)}  
                                        name="dateOfJoining"  
                                        dateFormat="dd/MM/yyyy" 
                                        maxDate={new Date()} 
                                        filterDate={date=>date.getDay()!==6&&date.getDay()!==0}
                                        isClearable
                                        showYearDropdown
                                        scrollableMonthYearDropdown
                                     />  
                                </div>
                                {/* Input field to get basic pay */}
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Basic Pay :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter basic pay per year"
                                        name = "basicPay"
                                        className = "form-control"
                                        value={basicPay}
                                        onChange = {(e) => setBasicPay(e.target.value)}
                                    >
                                    </input>
                                </div>
                                {/* Button to add,update or cancel */}
                                <div className='text-center'>
                                <button className = "btn btn-success" data-testid="buttonForAddUpdate" onClick = {AddOrUpdate} >{id?"Update Employee": "Add Employee"}</button>
                                <Link to="/employees" data-testid="cancelLink" className="btn btn-danger"> Cancel </Link>
                                </div>
                                
                            </form>
                        </div>
                    </div>
                </div>
           </div>
        </div>
  )
}

export default AddOrUpdateEmployee