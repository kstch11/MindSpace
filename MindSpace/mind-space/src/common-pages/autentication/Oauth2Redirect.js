import {useEffect} from "react";
import {useDispatch} from "react-redux";
import {setAccessToken} from "../../slices/UserSlice";
import {Navigate} from "react-router-dom";
import {fetchCurrentUser} from "../../api/client-api";
import {useQuery} from "@tanstack/react-query";

export default function Oauth2Redirect() {
    const dispatch = useDispatch();
    const queryParameters = new URLSearchParams(window.location.search);
    const token = queryParameters.get("token");

    useEffect(() => {
        localStorage.setItem("ACCESS_TOKEN", token);
        dispatch(setAccessToken(token));
    }, [token, dispatch]);

    const {data: currentUser, isLoading} = useQuery({
        queryKey: ['currentUser', token],
        queryFn: () => fetchCurrentUser(token),
        enabled: !!token,
    });

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (currentUser && !currentUser.finishedRegistration) {
        if (currentUser.isTherapist) {
            return <Navigate to={"/therapistApplication"}/>
        }
        return <Navigate to={"/questionnaire"}/>
    }

    return <Navigate to={"/"}/>;
}
