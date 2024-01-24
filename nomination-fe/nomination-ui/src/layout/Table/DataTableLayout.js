import React from 'react';
import CommonTable from "./CommonTable";

const DataTableLayout = ({...data}) => {
    return (
        <div>
            <CommonTable {...data} />
        </div>
    );
};

export default DataTableLayout;
