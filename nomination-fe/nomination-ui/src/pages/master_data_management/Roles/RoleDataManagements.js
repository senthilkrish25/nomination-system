import React, {useState} from 'react';
import {Box, Button, Typography} from "@mui/material";
import RoleDataProvider from "./RoleDataProvider";
import MainCard from "../../../layout/MainCard";
import DataTableLayout from "../../../layout/Table/DataTableLayout";

const RoleDataManagements = () => {
    const [open, setOpen] = useState(false)

    const columns = [
        {field: 'role_name', headerName: 'Role Name ', flex: 2},
        {field: 'customer_name', headerName: 'Customer Name', flex: 2},
    ];


    const rows = [
        {id: 1,  role_name: 'Snow', customer_name:'harry'},
        {id: 2,  role_name: 'Snow', customer_name:'harry'},
        {id: 3,  role_name: 'Snow', customer_name:'harry'},
    ];
    const onHandleClose = () => {
        setOpen(!open)
    }
    return (
        <MainCard>
            <Box display="flex" justifyContent={"end"} m={1}>
                <Button variant="contained" style={{background: "#5c8a2d"}} onClick={onHandleClose}>Add +</Button>
            </Box>
            <Box display="flex" justifyContent={"space-between"}>
                <Typography variant="h2" component="h2">
                    Roles
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
    <RoleDataProvider>
        <RoleDataManagements {...props} />
    </RoleDataProvider>
)

