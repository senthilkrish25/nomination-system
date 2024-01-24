
import React, {useEffect, useState} from 'react';
import CommonModal from "../../layout/CommonModal/CommonModal";
import {Button, Grid} from "@mui/material";
import TextField from "../../layout/Form/InputField";

const FormStats = {
    type: {
        type: "text",
        name: "type",
        value: "",
        required: true,
        label: "Type",
    },
    action: {
        type: "text",
        name: "action",
        value: "Active",
        required: true,
        label: "Action",
    },

}
const EditUserRole = ({params}) => {
    const [open, setOpen] = useState(false)
    const [customer, setCustomer] = useState(FormStats)
    const handleChange = (name, value) => {
        setCustomer((_state) => {
            return {
                ..._state,
                [name]: {
                    ..._state[name],
                    value,
                },
            };
        });
    };
    const onHandleClose = () => {
        setOpen(!open)
    }
    const addCustomerHandle = () =>{
        console.log(customer)
    }
    console.log(params)
    return (
        <>
            <Button onClick={() => setOpen(true)}>Edit</Button>
            <CommonModal
                open={open}
                addCustomerHandle={addCustomerHandle}
                onHandleClose={onHandleClose}
                title={"Edit Role"}
                btnTxt={"Save"}
            >
                <form action="">

                    <Grid container spacing={2} alignItems="center">
                        <Grid item xs={6} mb={2}>
                            <TextField {...customer.type} onChange={handleChange}/>
                        </Grid>
                        <Grid item xs={6} mb={2}>
                            <TextField {...customer.action} onChange={handleChange}/>
                        </Grid>
                    </Grid>

                </form>

            </CommonModal>
        </>
    );
}

export default EditUserRole;
