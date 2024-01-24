
import React, {createContext, useContext} from 'react';

const createUserContext = createContext()

export const useCreateContext = () => {
    return useContext(createUserContext);
};

const RoleDataProvider = ({children}) => {


    return (
        <createUserContext.Provider value={{
            name: "harry"
        }}>
            {children}
        </createUserContext.Provider>
    );
};

export default RoleDataProvider;
