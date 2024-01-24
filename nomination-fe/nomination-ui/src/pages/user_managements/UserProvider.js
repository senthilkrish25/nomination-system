import React, {createContext, useContext, useState} from 'react';
import EditCustomers from "../customer_management/EditCustomers";

const createUserContext = createContext()

export const useCreateContext = () => {
    return useContext(createUserContext);
};

const UserProvider = ({children}) => {


    return (
        <createUserContext.Provider value={{
            name: "harry"
        }}>
            {children}
        </createUserContext.Provider>
    );
};

export default UserProvider;
