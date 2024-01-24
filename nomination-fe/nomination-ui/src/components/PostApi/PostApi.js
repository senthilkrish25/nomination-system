import React, {useEffect, useState} from 'react';
import axios from 'axios';

export const PostApi = (url, payload) => {
    const [data,setData] = useState([])
    const [isLoaded, setIsLoaded] = useState(false);
    const [error, setError] = useState(null);
    debugger
    useEffect(()=>{
        setIsLoaded(true)
        axios.post(url, payload.finalPayloadCustomerDetails)
            .then((res) => {
                setData(res)
                setIsLoaded(false)

        }).catch((error)=>{
            setError(error)
        })
    },[url,payload])
    return {data,error,isLoaded}
}
