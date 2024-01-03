import {useDispatch} from "react-redux";
import {useEffect, useState} from "react";
import {setAccessToken, setProfile} from "../../slices/UserSlice";
import {redirect} from "react-router-dom";
import {fetchCurrentUser} from "../../api/client-api";

export default function AuthWrapper(props) {
    const [status, setStatus] = useState('idle');
    const dispatch = useDispatch();

    const fetchProfile = async (accessToken) => {
        try {
            const profile = await fetchCurrentUser(accessToken);
            dispatch(setProfile(profile));
            setStatus('loggedIn');
        } catch (error) {
            console.error("Error fetching profile:", error);
            setStatus('error');
        }
    };

    useEffect(() => {
        const accessToken = localStorage.getItem('ACCESS_TOKEN');
        if (accessToken) {
            dispatch(setAccessToken(accessToken));
            fetchProfile(accessToken);
        } else {
            setStatus('notLoggedIn');
            redirect("/login");
        }
    }, [dispatch]);

    if (status === 'idle') {
        return <div>Loading...</div>;
    }

    if (status === 'notLoggedIn' || status === 'error') {
        redirect("/login");
    }

    return props.children;
}
