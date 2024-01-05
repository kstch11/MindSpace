import {ClientData} from "./ClientData";
import {ClientSession} from "./ClientSession";
import {ChangeTherapist} from "./ChangeTherapist";
import {Navbar} from "../parts/Navbar";
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";
import {TherapistData} from "../common-pages/therapists-list/TherapistData";

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
        component: <TherapistData toggleValue={'client'} />,
    },
]

export function ClientNavbar() {
    const isTherapist = useSelector(state => state.currentUser.profile.isTherapist);
    const isAdmin = useSelector(state => state.currentUser.profile.isAdmin);
    if (isTherapist) {
        return <Navigate to={"/therapistProfile"} />
    }
    if (isAdmin) {
        return <Navigate to={"/adminProfile"} />
    }
    return(
        <Navbar contentProps={contentProps} />
    )
}


