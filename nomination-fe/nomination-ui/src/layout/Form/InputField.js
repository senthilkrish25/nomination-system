import React from "react";
import { TextField as MUITextField } from "@mui/material";

const TextField = ({
                       name,
                       value,
                       label,
                       onChange,
                       type = "text",
                       required = false,
                       fullWidth = true,
                       hidden = false,
                       ...rest
                   }) => {
    if (hidden) return null;

    return (
        <MUITextField
            type={type}
            label={label}
            name={name}
            InputLabelProps={{
                shrink: true,
                required,
            }}
            fullWidth={fullWidth}
            value={value}
            onChange={(e) => {
                onChange(name, e.target.value);
            }}
            {...rest}
        />
    );
};

export default TextField;
