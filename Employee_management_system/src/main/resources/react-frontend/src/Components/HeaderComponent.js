import React from 'react'

//Header component
const HeaderComponent = () => {
    return (
        <div>
            <header>
                <nav className = "navbar navbar-expand-md navbar-dark bg-primary">
                    <div>
                         <h4 className='text-white'><span style={{ fontWeight: 'bold' }}>Employee Management Application</span></h4>    
                    </div>

                </nav>
            </header>
        </div>
    )
}

export default HeaderComponent