import {ClientData} from "./ClientData";
import {ClientSession} from "./ClientSession";
import {ChangeTherapist} from "./ChangeTherapist";
import {Navbar} from "../parts/Navbar";
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";

const contentProps = [
    {
        label: "Personal data",
        order: 1,
        value: 'personalData',
        component: <ClientData />,
    },
    {
        label: "Session",
        order: 1,
        value: 'session',
        component: <ClientSession />,
    },
    {
        label: "Therapist",
        order: 1,
        value: 'therapist',
        component: <ChangeTherapist />,
    },
]

export function ClientNavbar() {
    const isTherapist = useSelector(state => state.currentUser.profile.isTherapist);
    if (isTherapist) {
        return <Navigate to={"/therapistProfile"} />
    }
    return(
        <Navbar contentProps={contentProps} />
    )
}


