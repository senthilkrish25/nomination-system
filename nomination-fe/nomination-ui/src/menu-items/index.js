// project import
import pages from './pages';
import {admin, dashboard, Master_data, nomination} from './dashboard';
import utilities from './utilities';
import support from './support';

// ==============================|| MENU ITEMS ||============================== //

const menuItems = {

    items: [dashboard,nomination,admin,Master_data]
    // items: [dashboard, pages, utilities, support]
};

export const specificPage = {

    items: [pages]
};

export default menuItems;
