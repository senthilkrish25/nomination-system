// assets
import { DashboardOutlined ,DatabaseOutlined,FolderAddOutlined,FolderOutlined,GoldOutlined} from '@ant-design/icons';

// icons
const icons = {
    DashboardOutlined
};

// ==============================|| MENU ITEMS - DASHBOARD ||============================== //

export const dashboard = {
    id: 'group-dashboard',
    type: 'group',
    children: [
        {
            id: 'dashboard',
            title: 'Dashboard',
            type: 'item',
            url: '/',
            icon: icons.DashboardOutlined,
            breadcrumbs: false,
        }
    ]
};

export const nomination = {
    id:'nomination',
    type: 'group',
    title: 'Nomination',
    children: [
        {
            id:'dailyNomination',
            title: 'Daily Nomination',
            type: 'item',
            url: '/daily-nomination',
            icon: DatabaseOutlined,
            breadcrumbs: false,
        },
        {
            id:"weeklyNomination",
            title: 'Weekly Nomination',
            type: 'item',
            url: '/weekly-nomination',
            icon: FolderAddOutlined,
            breadcrumbs: false,
        },
        {
            id:"monthlyNomination",
            title: 'Monthly Nomination',
            type: 'item',
            url: '/monthly-nomination',
            icon: FolderOutlined,
            breadcrumbs: false,
        },
        {
            id:"yearlyNomination",
            title: 'Yearly Nomination',
            type: 'item',
            url: '/yearly-nomination',
            icon: GoldOutlined,
            breadcrumbs: false,
        }
    ]

}

export const admin = {
    id:'admin',
    type: 'group',
    title: 'Admin',
    children: [
        {
            id:'customer1',
            title: 'Customers',
            type: 'item',
            url: '/customers',
            icon: DatabaseOutlined,
            breadcrumbs: false,
        },
        {
            id:"users1",
            title: 'Users',
            type: 'item',
            url: '/users',
            icon: FolderAddOutlined,
            breadcrumbs: false,
        },
        // {
        //     id:"masterdata1",
        //     title: 'Master Data',
        //     type: 'item',
        //     url: '/master-data',
        //     icon: FolderOutlined,
        //     breadcrumbs: false,
        // },
    ]

}
export const Master_data = {
    id:'admin',
    type: 'group',
    title: 'Master Data',
    children: [
        {
            id:'agreement1',
            title: 'Agreement Code',
            type: 'item',
            url: '/agreement-code',
            icon: DatabaseOutlined,
            breadcrumbs: false,
        },
        {
            id:"roles1",
            title: 'Roles',
            type: 'item',
            url: '/roles',
            icon: FolderAddOutlined,
            breadcrumbs: false,
        },

    ]

}
