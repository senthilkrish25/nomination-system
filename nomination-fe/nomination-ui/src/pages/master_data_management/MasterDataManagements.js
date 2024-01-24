
import React, {useState} from 'react';
import MainCard from "../../layout/MainCard";
import {Box, Button, Typography} from "@mui/material";
import DataTableLayout from "../../layout/Table/DataTableLayout";
import {DeleteOutlined} from "@ant-design/icons";
import MasterDataProvider from "./MasterDataProvider";

const MasterDataManagements = () => {
    const [open, setOpen] = useState(false)
    const columns = [
        {field: 'code', headerName: 'Agreement Code', flex: 1},
        {field: 'name', headerName: 'Customer Name', flex: 1},
        {field: 'start', headerName: 'Start date', flex: 1},
        {field: 'end', headerName: 'End date ', flex: 1},
        {
            field: '', headerName: 'Actions', flex: 1,
            renderCell: (params) => {
                return (
                    <>
                        <Button variant="contained" style={{background: "#5c8a2d"}}> End Agreement</Button>

                    </>
                )
            }
        },
    ];

    const rows = [
        {id:1,code: 'A001', name: 'Snow', start: '01-01-2023',end: '01-01-2023'},
        {id:2,code: 'A002', name: 'harry', start: '01-01-2023',end: '01-01-2023'},
        {id:3,code: 'A003', name: 'daksh', start: '01-01-2023',end: '01-01-2023'},
        {id:4,code: 'A004', name: 'harinder', start: '01-01-2023',end: '01-01-2023'},
        {id:5,code: 'A005', name: 'kdaksh', start: '01-01-2023',end: '01-01-2023'},
        {id:6,code: 'A006', name: 'kiran', start: '01-01-2023',end: '01-01-2023'},

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
                 Master Data
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
    <MasterDataProvider>
        <MasterDataManagements {...props} />
    </MasterDataProvider>
)

