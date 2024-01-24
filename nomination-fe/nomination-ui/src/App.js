import ThemeCustomization from 'themes';
import ScrollTop from 'components/ScrollTop';
import {Route, Routes} from "react-router";

import DashboardDefault from "./pages/dashboard";
import MainLayout from "./layout/MainLayout";
import Login from "./pages/authentication/Login";
import ForgetPassword from "./pages/authentication/auth-forms/ForgetPassword";
import ForgetPasswordWrapper from "./pages/authentication/ForgetPasswordWrapper";
import ChangePassword from "./pages/authentication/auth-forms/ChangePassword";
import AuthChangePassword from "./pages/authentication/AuthChangePassword";
import Customermanagement from "./pages/customer_management/Customermanagement";
import './App.css'
import UserManagement from "./pages/user_managements/UserManagement";
import MasterDataManagements from "./pages/master_data_management/MasterDataManagements";
import RoleDataManagements from "./pages/master_data_management/Roles/RoleDataManagements";
// const router = createBrowserRouter(createRoutesFromElements(
//     <Route path={'/'} element={<MainLayout/>}>
//         <Route index element={<DashboardDefault />} />
//     </Route>
// ))


const App = () => {
    return (
        <ThemeCustomization>
            <ScrollTop>
                <Routes>
                    <Route element={<MainLayout/>}>
                        <Route index path={'/'} element={<DashboardDefault/>}/>
                        <Route  path={'/customers'} element={<Customermanagement/>}/>
                        <Route  path={'/users'} element={<UserManagement/>}/>
                        <Route  path={'/agreement-code'} element={<MasterDataManagements/>}/>
                        <Route  path={'/roles'} element={<RoleDataManagements/>}/>
                        <Route  path={'*'} element={<h1>Page not Found</h1>}/>
                    </Route>
                    <Route  path={'/login'} element={<Login/>}/>
                    <Route  path={'/forget-password'} element={<ForgetPasswordWrapper/>}/>
                    <Route  path={'/change-password'} element={<AuthChangePassword/>}/>


                </Routes>

            </ScrollTop>
        </ThemeCustomization>
    );
}

export default App;
