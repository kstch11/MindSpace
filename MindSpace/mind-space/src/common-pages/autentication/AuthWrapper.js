import {useDispatch} from "react-redux";
import {useEffect, useState} from "react";
import {setAccessToken} from "../../slices/UserSlice";
import {redirect} from "react-router-dom";

export default function AuthWrapper(props) {
    const [status, setStatus] = useState('idle')
    const dispatch = useDispatch();

    useEffect(() => {
        console.log("huj")
    }, [])

    useEffect(() => {
        const accessToken = localStorage.getItem('ACCESS_TOKEN')
        if (accessToken != null) {
            dispatch(setAccessToken(accessToken))
            setStatus('loggedIn')
        } else {
            setStatus('notLoggedIn')
            redirect("/login")
        }
    }, [])

    if (status === 'idle') {
        return (
            <div>Loading...</div>
        )
    }

    if (status === 'notLoggedIn') {
        redirect("/login")
    }

    return props.children
}
