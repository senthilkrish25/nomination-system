import React, {useEffect, useState} from 'react';
import CommonModal from "../../layout/CommonModal/CommonModal";
import {Button, Grid} from "@mui/material";
import TextField from "../../layout/Form/InputField";
import {EditFilled} from "@ant-design/icons";

const FormStats = {
    name: {
        type: "text",
        name: "name",
        value: "",
        required: true,
        label: "Name",
    },
    email: {
        type: "email",
        name: "email",
        value: "",
        required: true,
        label: "Official Email",
    },
    phone: {
        type: "text",
        name: "phone",
        value: "",
        required: true,
        label: "Point of Contact",
    },
}
const EditCustomers = ({params}) => {
    const [open, setOpen] = useState(false)
    const [customer, setCustomer] = useState(FormStats)
    customer.name.value = params.name
    customer.email.value = params.email
    customer.phone.value = params.phone
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
    const EditCustomerHandler = () =>{
        console.log(customer)
    }
    console.log(params)
    return (
        <>
            <Button onClick={() => setOpen(true)}><EditFilled style={{color:"black",marginRight:"auto",fontSize:"20px"}} /></Button>
            <CommonModal
                open={open}
                addCustomerHandle={EditCustomerHandler}
                onHandleClose={onHandleClose}
                title={"Edit Customer"}
                btnTxt={"Save"}
            >
                <form action="">

                    <Grid container spacing={2} alignItems="center">
                        <Grid item xs={6} mb={2}>
                            <TextField {...customer.name} onChange={handleChange}/>
                        </Grid>
                        <Grid item xs={6} mb={2}>
                            <TextField {...customer.email} onChange={handleChange}/>
                        </Grid>
                    </Grid>
                    <Grid container rowSpacing={1} columnSpacing={{xs: 1, sm: 2, md: 3}}>
                        <Grid item xs={6}>
                            <TextField {...customer.phone} onChange={handleChange}/>

                        </Grid>
                    </Grid>
                </form>

            </CommonModal>
        </>
    );
}

export default EditCustomers;
