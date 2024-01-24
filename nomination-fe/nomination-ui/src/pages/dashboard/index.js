import {useState} from 'react';

// material-ui
import {
    Grid,
    Typography
} from '@mui/material';


// ==============================|| DASHBOARD - DEFAULT ||============================== //

const DashboardDefault = () => {
    return (
        <Grid container rowSpacing={4.5} columnSpacing={2.75}>
            <Grid item xs={12} sx={{mb: -2.25}} textAlign={"center"}>
                <Typography color={
                    "#558424"
                } variant="h2">Welcome Harry</Typography>
            </Grid>
        </Grid>
    );
};

export default DashboardDefault;
