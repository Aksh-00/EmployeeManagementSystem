import axios from 'axios'
import React,{useState,useEffect} from 'react'
import EmployeeService from '../Services/EmployeeService'
import {useHistory,Link,useParams} from 'react-router-dom'
const AddOrUpdateEmployee = () => 
{

    const [name,setName]=useState('')
    const [dateOfJoining,setDateOfJoining]=useState('')
    const [basicPay,setBasicPay]=useState('')
    const history=useHistory();
    const {id}=useParams();

    useEffect(() => {

        EmployeeService.getEmployeeById(id).then((response) =>{
            setName(response.data.name)
            setDateOfJoining(response.data.dateOfJoining)
            setBasicPay(response.data.basicPay)
            console.log(name+" "+dateOfJoining)
        }).catch(error => {
            console.log(error)
        })
    }, [])
    const AddOrUpdate=(e)=>{
        e.preventDefault();
        const employee={name,dateOfJoining,basicPay}
        console.log(employee);
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

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Date of Joining :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter date of joining"
                                        name = "datOfJoining"
                                        className = "form-control"
                                        value={dateOfJoining}
                                        onChange = {(e) => setDateOfJoining(e.target.value)}
                                    >
                                    </input>
                                </div>

                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Basic Pay :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter email Id"
                                        name = "basicPay"
                                        className = "form-control"
                                        value={basicPay}
                                        onChange = {(e) => setBasicPay(e.target.value)}
                                    >
                                    </input>
                                </div>
                                <div className='text-center'>
                                <button className = "btn btn-success" onClick = {AddOrUpdate} >{id?"Update Employee": "Add Employee"}</button>
                                <Link to="/employees" className="btn btn-danger"> Cancel </Link>
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