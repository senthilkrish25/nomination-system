
import React, {createContext, useContext, useState} from 'react';

const createUserContext = createContext()

export const useCreateContext = () => {
    return useContext(createUserContext);
};

const MasterDataProvider = ({children}) => {


    return (
        <createUserContext.Provider value={{
            name: "harry"
        }}>
            {children}
        </createUserContext.Provider>
    );
};

export default MasterDataProvider;
