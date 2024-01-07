import {useEffect} from "react";
import {useDispatch} from "react-redux";
import {setAccessToken, setProfile} from "../../slices/UserSlice";
import {Navigate} from "react-router-dom";
import {fetchCurrentUser} from "../../api/client-api";
import {useQuery} from "@tanstack/react-query";

export default function Oauth2Redirect() {
    const dispatch = useDispatch();
    const queryParameters = new URLSearchParams(window.location.search);
    const token = queryParameters.get("token");

    const {data: currentUser, isLoading} = useQuery({
        queryKey: ['currentUser', token],
        queryFn: () => fetchCurrentUser(token),
        enabled: !!token,
    });

    useEffect(() => {
        if (currentUser) {
            console.log(currentUser)
            dispatch(setProfile(currentUser))
        }
    }, [currentUser]);

    useEffect(() => {
        localStorage.setItem("ACCESS_TOKEN", token);
        dispatch(setAccessToken(token));
    }, [token, dispatch]);

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (currentUser && !currentUser.finishedRegistration) {
        if (currentUser.isTherapist) {
            return <Navigate to={"/therapistApplication"}/>
        }
        return <Navigate to={"/questionnaire"}/>
    }

    if (!currentUser.isTherapist) {
        return <Navigate to={"/clientProfile"}/>;
    } else {
        return <Navigate to={"/therapistProfile"}/>;
    }


}
