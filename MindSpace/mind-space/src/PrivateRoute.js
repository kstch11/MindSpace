import {Navigate, Outlet} from "react-router-dom";
import {useSelector} from "react-redux";

export default function PrivateRoute() {
    const isLoggedIn = useSelector(state => state.currentUser.accessToken != null)

    if (isLoggedIn) {
        return <Outlet/>
    }

    return <Navigate to={"/login"}/>
}
