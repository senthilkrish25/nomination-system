import React, {useState} from 'react';
import {Formik} from "formik";
import * as Yup from "yup";
import {
    Button,
    FormHelperText,
    Grid,
    IconButton,
    InputAdornment,
    InputLabel,
    OutlinedInput,
    Stack
} from "@mui/material";
import AnimateButton from "../../../components/@extended/AnimateButton";
import {EyeInvisibleOutlined, EyeOutlined} from "@ant-design/icons";

const ChangePassword = () => {
    const [loading,setLoading] = useState(false)
    const [showPassword, setShowPassword] = React.useState(false);
    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    };
    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };
    return (
        <Formik initialValues={
            {
                password: "",
                confirmPassword:""
            }
        }
                validationSchema={Yup.object().shape({
                    password: Yup.string().required('Password is required'),
                    confirmPassword: Yup.string()
                        .oneOf([Yup.ref('password'), null], 'Passwords must match')
                })}
                onSubmit={async (value, { setErrors, setStatus, setSubmitting }) =>{
                    try {
                        // setStatus({ success: false });
                        // setSubmitting(false);
                        console.log(value)
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

            {({errors, handleBlur, values, handleChange, touched,isSubmitting,isValid,dirty}) => (
                <form action="">
                    <Grid container spacing={3}>
                        <Grid item xs={12}>
                            <Stack spacing={1}>
                                <InputLabel htmlFor="password">Password *</InputLabel>
                                <OutlinedInput
                                    required
                                    fullWidth
                                    error={Boolean(touched.password && errors.password && isSubmitting)}
                                    id="-password-login"
                                    type={showPassword ? 'text' : 'password'}
                                    value={values.password}
                                    name="password"
                                    onBlur={handleBlur}
                                    onChange={handleChange}
                                    endAdornment={
                                        <InputAdornment position="end">
                                            <IconButton
                                                aria-label="toggle password visibility"
                                                onClick={handleClickShowPassword}
                                                onMouseDown={handleMouseDownPassword}
                                                edge="end"
                                                size="large"
                                            >
                                                {showPassword ? <EyeOutlined /> : <EyeInvisibleOutlined />}
                                            </IconButton>
                                        </InputAdornment>
                                    }
                                    placeholder="Enter password"
                                />
                                {touched.password && errors.password && (
                                    <FormHelperText error id="standard-weight-helper-text-password-login">
                                        {errors.password}
                                    </FormHelperText>
                                )}

                                <InputLabel htmlFor="confirmPassword">Confirm Password *
                                </InputLabel>
                                <OutlinedInput
                                    fullWidth
                                    required
                                    error={Boolean(touched.confirmPassword && errors.confirmPassword && isSubmitting)}
                                    id="Confirm-password-login"
                                    type={showPassword ? 'text' : 'password'}
                                    value={values.confirmPassword}
                                    name="confirmPassword"
                                    onBlur={handleBlur}
                                    onChange={handleChange}
                                    endAdornment={
                                        <InputAdornment position="end">
                                            <IconButton
                                                aria-label="toggle password visibility"
                                                onClick={handleClickShowPassword}
                                                onMouseDown={handleMouseDownPassword}
                                                edge="end"
                                                size="large"
                                            >
                                                {showPassword ? <EyeOutlined /> : <EyeInvisibleOutlined />}
                                            </IconButton>
                                        </InputAdornment>
                                    }
                                    placeholder="Enter password"
                                />
                                {touched.confirmPassword && errors.confirmPassword && (
                                    <FormHelperText error id="standard-weight-helper-text-password-login">
                                        {errors.confirmPassword}
                                    </FormHelperText>
                                )}
                                <Grid
                                      container
                                      alignItems="center"
                                      justifyContent="center"
                                >
                                    <AnimateButton>
                                        <Button
                                            disableElevation
                                            disabled={isSubmitting}
                                            fullWidth
                                            size="large"
                                            type="submit"
                                            variant="contained"
                                            color="primary"
                                            disabled={!isValid}
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

export default ChangePassword;
