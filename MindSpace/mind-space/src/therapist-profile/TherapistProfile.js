import {Schedule} from "./Schedule";
import {Navbar} from "../parts/Navbar";
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";
import TherapistInfo from "./TherapistInfo";

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
        component: <Schedule />,
    },
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
