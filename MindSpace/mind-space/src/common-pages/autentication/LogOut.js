import {useDispatch} from "react-redux";
import {useEffect} from "react";
import {revokedAccessToken} from "../../slices/UserSlice";
import {Navigate} from "react-router-dom";

export default function LogOut() {
    const dispatch = useDispatch();

    useEffect(() => {
        localStorage.removeItem("ACCESS_TOKEN")
        dispatch(revokedAccessToken())
    }, [])

    return <Navigate to={"/"}/>
}
