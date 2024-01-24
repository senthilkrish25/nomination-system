import React, {useEffect, useState} from 'react';
import DataTableLayout from "../../layout/Table/DataTableLayout";
import {Box, Grid, Button, Paper, Typography} from "@mui/material";
import MainCard from "../../layout/MainCard";
import AddCustomer from "./AddCustomer";
import {DeleteFilled, DeleteOutlined, EditOutlined} from "@ant-design/icons";
import EditCustomers from "./EditCustomers";
import axios from "axios";
import {CUSTOMERS_PATH} from "../../components/API/customers/PATH";

const Customermanagement = () => {
    const [open, setOpen] = useState(false)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')
    const [customerData, setCustomerData] = useState([])
    const fetchcustomerData = () => {
        try {
            axios.get(`${CUSTOMERS_PATH}/user-managent`)
                .then((res) => {
                    setCustomerData(res.data)
                })
        } catch (e) {
            setError(e)
        } finally {
            setLoading(false)
        }
    }
    useEffect(() => {
        setLoading(true)
        fetchcustomerData()
    }, [])

    const columns = [
        {field: 'id', headerName: 'ID', flex:1},
        {field: 'name', headerName: 'Name', flex:1},
        {field: 'email', headerName: 'Official Email', flex:1},
        {
            field: 'phone',
            headerName: 'Point of Contact',
            flex:1,
        },
        {
            headerName: 'Edit',
            field: 'edit',
            width: 100,
            renderCell: (params) => {
                return (
                    <>
                        <EditCustomers params={params.row}/>

                    </>
                )
            }
        },
        {
            headerName: 'delete',
            field: 'delete',
            width: 100,
            renderCell: (params) => {
                return (
                    <>
                        <DeleteFilled style={{cursor:"pointer"}} />

                    </>
                )
            }
        },
    ];

    const onHandleClose = () => {
        setOpen(!open)
    }
    return (
        <>

            <MainCard>
                <AddCustomer
                    open={open}
                    onHandleClose={onHandleClose}
                    setOpen={setOpen}
                    fetchcustomerData={fetchcustomerData}
                />
                <Box display="flex" justifyContent={"end"} m={1}>
                    <Button variant="contained" style={{background: "#5c8a2d"}} onClick={onHandleClose}>Add +</Button>
                </Box>
                <Box display="flex" justifyContent={"space-between"}>
                    <Typography variant="h2" component="h2">
                        Customers
                    </Typography>
                    <Box display="flex" justifyContent={"space-between"}>
                    </Box>
                </Box>
                <Box display="grid" gap={2} mt={3}>
                    <DataTableLayout columns={columns} rows={customerData} loading={loading} error={error}/>
                </Box>

            </MainCard>
        </>
    )
        ;
};

export default Customermanagement;
