import React from 'react';
import AuthWrapper from "./AuthWrapper";
import {Grid, Stack, Typography} from "@mui/material";
import {Link} from "react-router-dom";
import FirebaseRegister from "./auth-forms/AuthRegister";
import ChangePassword from "./auth-forms/ChangePassword";

const AuthChangePassword = () => {
    return (
        <AuthWrapper>
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <Stack direction="row" justifyContent="space-between" alignItems="baseline" sx={{ mb: { xs: -0.5, sm: 0.5 } }}>
                        <Typography variant="h3">Change Password</Typography>
                    </Stack>
                </Grid>
                <Grid item xs={12}>
                    <ChangePassword />
                </Grid>
            </Grid>
        </AuthWrapper>
    );
};

export default AuthChangePassword;
