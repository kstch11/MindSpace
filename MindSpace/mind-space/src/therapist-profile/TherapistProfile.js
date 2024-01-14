import {Schedule} from "../client-profile/Schedule";
import {Navbar} from "../parts/Navbar";
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";
import TherapistInfo from "./TherapistInfo";
import {TherapistSchedule} from "./TherapistSchedule";
import {TherapistSession} from "../common-pages/therapists-list/TherapistSession";

const contentProps = [
    {
        label: "Personal data",
        order: 1,
        value: 'personalData',
        component: <TherapistInfo />,
    },
    {
        label: "Schedule",
        order: 1,
        value: 'schedule',
        component: <TherapistSchedule />,
    },
    {
        label: "Session",
        order: 1,
        value: 'session',
        component: <TherapistSession />,
    }
]

export function TherapistNavbar() {
    const isTherapist = useSelector(state => state.currentUser.profile.isTherapist);
    const isAdmin = useSelector(state => state.currentUser.profile.isAdmin);
    if (!isTherapist) {
        return <Navigate to={"/clientProfile"} />
    }
    if (isAdmin) {
        return <Navigate to={"/adminProfile"} />
    }

    return(
        <Navbar contentProps={contentProps} />
    )
}
