import {TherapistData} from "../common-pages/therapists-list/TherapistData";
import {ClientsTable} from "./ListOfClients";
import {ReservationTable} from "./ListOfReservations";
import {Schedule} from "./Schedule";
import {Navbar} from "../parts/Navbar";
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";

const contentProps = [
    {
        label: "Personal data",
        order: 1,
        component: <TherapistData />,
    },
    {
        label: "Chats",
        order: 1,
        component: <ClientsTable />,
    },
    {
        label: "Schedule",
        order: 1,
        component: <Schedule />,
    },
]

export function TherapistNavbar() {
    const isTherapist = useSelector(state => state.currentUser.profile.isTherapist);
    if (!isTherapist) {
        return <Navigate to={"/clientProfile"} />
    }

    return(
        <Navbar contentProps={contentProps} />
    )
}
