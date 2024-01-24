import React, {useEffect, useState} from 'react';
import CommonModal from "../../layout/CommonModal/CommonModal";
import {Grid} from "@mui/material";
import TextField from "../../layout/Form/InputField";
import {PostApi, usePostApi} from "../../components/PostApi/PostApi";
import axios from "axios";
import {CustomerUtils} from "../../utils/customers/customerUtils";

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
        type: "number",
        name: "phone",
        value: "",
        required: true,
        label: "Point of Contact",
    },
}
const AddCustomer = ({open, onHandleClose,setOpen,fetchcustomerData}) => {
    const [customer, setCustomer] = useState(FormStats)
    const [loading, setLoading] = useState(false)

    const addCustomerHandle = () => {

        try {
            axios.post("https://65acf49dadbd5aa31bdfd287.mockapi.io/user-managent", CustomerUtils(customer).finalPayloadCustomerDetails)
                .then((res) => {
                    console.log(res)
                    setOpen(false)
                    fetchcustomerData()
                })
        } catch (e) {
            console.log(e)
        } finally {
            setLoading(true)
        }

    }
    const handleChange = (name, value) => {
        console.log(value, name)
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
    return (
        <CommonModal
            open={open}
            addCustomerHandle={addCustomerHandle}
            onHandleClose={onHandleClose}
            title={"Add Customer"}
            btnTxt={"Add"}
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
    )
        ;
};


export default AddCustomer;
