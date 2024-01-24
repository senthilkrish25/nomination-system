import React from 'react';

export const CustomerUtils = (customer) => {
    const {email, phone, name} = customer

    const finalPayloadCustomerDetails = {
        email: email.value,
        phone: phone.value,
        name: name.value
    }
    return {
        finalPayloadCustomerDetails
    }
};
