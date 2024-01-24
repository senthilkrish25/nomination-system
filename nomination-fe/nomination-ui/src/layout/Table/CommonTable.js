import React from 'react';
import {DataGrid} from '@mui/x-data-grid';



const CommonTable = ({

    columns=[],
    rows=[],
    loading=false
                     }) => {
    return (
        <div>
            <div style={{height: 400, width: '100%'}}>
                <DataGrid
                    rows={rows}
                    loading={loading}
                    columns={columns}
                    initialState={{
                        pagination: {
                            paginationModel: {page: 0, pageSize: 5},
                        },
                    }}
                    getRowId={rows?.id}
                    pageSizeOptions={[5, 10,15]}
                    // checkboxSelection
                />
            </div>
        </div>
    );
};

export default CommonTable;
