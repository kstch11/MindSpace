import {TherapistData} from "./TherapistData";
import {ClientsTable} from "./ListOfClients";
import {ReservationTable} from "./ListOfReservations";
import {Schedule} from "./Schedule";
import {Navbar} from "../parts/Navbar";

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
        component: <ReservationTable />,
    },
]

export function TherapistNavbar() {
    return(
        <Navbar contentProps={contentProps} />
    )
}