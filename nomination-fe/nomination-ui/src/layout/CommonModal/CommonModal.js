import React from "react";
import {Box, Modal, Typography, Button} from "@mui/material";

const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 800,
    bgcolor: "background.paper",
    borderRadius: 2,
    boxShadow: 24,
    p: 2,
};


const CommonModal = ({open,onHandleClose, addCustomerHandle,btnTxt,title, children}) => {
    return (
        <Modal open={open} onClose={addCustomerHandle}>
            <Box sx={style}>
                <Typography variant="h4" component="h2" mb={2}>
                    {title}
                </Typography>
                {children}
                <Box
                    sx={{
                        display: "flex",
                        justifyContent: "end",
                        gap: "1rem",
                        margin: "0.5rem",
                    }}
                >

                    <Button color="primary" onClick={onHandleClose}>
                        Go Back
                    </Button>
                    <Button color="primary" type="submit" variant="contained"  onClick={addCustomerHandle}>
                        {btnTxt}
                    </Button>
                </Box>
            </Box>
        </Modal>
    );
};

export default CommonModal;
