import React, {useState} from 'react';
import UserProvider, {useCreateContext} from "./UserProvider";
import MainCard from "../../layout/MainCard";
import AddCustomer from "../customer_management/AddCustomer";
import {Box, Button, Typography} from "@mui/material";
import DataTableLayout from "../../layout/Table/DataTableLayout";
import EditCustomers from "../customer_management/EditCustomers";
import EditUserRole from "./EditUserRole";
import {DeleteOutlined} from "@ant-design/icons";

const UserManagement = () => {

    const {name} = useCreateContext()
    const [open, setOpen] = useState(false)


    const handleDeleteClick = () => {
        console.log("delete ")
    }
    const handleEditClick = () => {
        console.log("edit")
    }
    const columns = [
        {field: 'id', headerName: 'ID', width: 70},
        {field: 'firstName', headerName: 'Name', width: 130},
        {field: 'lastName', headerName: 'Official Email', width: 130},
        {
            headerName: 'Role',
            width: 100,
            renderCell: (params) => {
                return (
                    <>
                        <EditUserRole params={params.row}/>

                    </>
                )
            }
        },
        {
            field: '', headerName: 'Actions', width: 130,
            renderCell: (params) => {
                return (
                    <>
                        <Button >Edit</Button>
                        <Button color="error" > <DeleteOutlined /></Button>

                    </>
                )
            }
        },


    ];

    const rows = [
        {id: 1, lastName: 'Snow', firstName: 'Jon', phone: 233231231},
        {id: 2, lastName: 'Lannister', firstName: 'Cersei', phone: 123123123},
        {id: 3, lastName: 'Lannister', firstName: 'Jaime', phone: 123123123},
        {id: 4, lastName: 'Stark', firstName: 'Arya', age: 324234234},
        {id: 5, lastName: 'Targaryen', firstName: 'Daenerys', phone: null},
        {id: 6, lastName: 'Melisandre', firstName: null, phone: 567574456},
        {id: 7, lastName: 'Clifford', firstName: 'Ferrara', phone: 678678678},
        {id: 8, lastName: 'Frances', firstName: 'Rossini', phone: 324545756},
        {id: 9, lastName: 'Roxie', firstName: 'Harvey', phone: 79867345243},
    ];
    const onHandleClose = () => {
        setOpen(!open)
    }
    console.log(name)
    return (
        <MainCard>
            <AddCustomer open={open} onHandleClose={onHandleClose}/>
            <Box display="flex" justifyContent={"end"} m={1}>
                <Button variant="contained" style={{background: "#5c8a2d"}} onClick={onHandleClose}>Add +</Button>
            </Box>
            <Box display="flex" justifyContent={"space-between"}>
                <Typography variant="h2" component="h2">
                    User
                </Typography>
                <Box display="flex" justifyContent={"space-between"}>
                </Box>
            </Box>
            <Box display="grid" gap={2} mt={3}>
                <DataTableLayout columns={columns} rows={rows}/>
            </Box>

        </MainCard>
    );
};

export default (props) => (
    <UserProvider>
        <UserManagement {...props} />
    </UserProvider>
)
