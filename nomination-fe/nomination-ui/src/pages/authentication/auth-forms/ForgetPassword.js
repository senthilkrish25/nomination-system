import React, {useState} from 'react';
import {Form, Formik} from "formik";
import * as Yup from "yup";
import {
    Box, Button, Divider,
    FormControl,
    FormHelperText,
    Grid,
    IconButton,
    InputAdornment,
    InputLabel, Link,
    OutlinedInput,
    Stack, Typography
} from "@mui/material";
import {EyeInvisibleOutlined, EyeOutlined} from "@ant-design/icons";
import {Link as RouterLink} from "react-router-dom";
import AnimateButton from "../../../components/@extended/AnimateButton";
import FirebaseSocial from "./FirebaseSocial";
import AuthWrapper from "../AuthWrapper";

const ForgetPassword = () => {

    const [loading,setLoading] = useState(false)

    return (
        <Formik initialValues={
            {
                email: ""
            }
        }
                validationSchema={Yup.object().shape({
                    email: Yup.string().email('Must be a valid email').max(255).required('Email is required'),
                })}
                onSubmit={async (value, { setErrors, setStatus, setSubmitting }) =>{
                try {
                    setStatus({ success: false });
                    setSubmitting(false);
                }
                catch (err){
                    setStatus({ success: false });
                    setErrors({ submit: err.message });
                    setSubmitting(false);
                }finally {
                    setLoading(false)
                }
                }
                }>

            {({errors, handleBlur, values, handleChange, touched,isSubmitting}) => (
                <form action="">
                    <Grid container spacing={3}>
                        <Grid item xs={12}>
                            <Stack spacing={1}>
                                <InputLabel htmlFor="email-login">Email Address</InputLabel>
                                <OutlinedInput
                                    id="email-login"
                                    type="email"
                                    value={values.email}
                                    name="email"
                                    onBlur={handleBlur}
                                    onChange={handleChange}
                                    placeholder="Enter email address"
                                    fullWidth
                                    error={Boolean(touched.email && errors.email)}
                                />
                                {touched.email && errors.email && (
                                    <FormHelperText error id="standard-weight-helper-text-email-login">
                                        {errors.email}
                                    </FormHelperText>
                                )}
                                <Grid item xs={3} sx={{mt:2}}>
                                    <AnimateButton>
                                        <Button
                                            disableElevation
                                            disabled={isSubmitting}
                                            fullWidth
                                            size="large"
                                            type="submit"
                                            variant="contained"
                                            color="primary"
                                        >
                                            Submit
                                        </Button>
                                    </AnimateButton>
                                </Grid>
                            </Stack>
                        </Grid>
                    </Grid>

                </form>
            )}

        </Formik>
    );
};

export default ForgetPassword;
