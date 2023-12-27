import {ClientData} from "./ClientData";
import {ClientSession} from "./ClientSession";
import {ChangeTherapist} from "./ChangeTherapist";
import {Navbar} from "../common-parts/Navbar";

const contentProps = [
    {
        label: "Personal data",
        order: 1,
        component: <ClientData id={3} />,
    },
    {
        label: "Session",
        order: 1,
        component: <ClientSession />,
    },
    {
        label: "Therapist",
        order: 1,
        component: <ChangeTherapist />,
    },
]

export function ClientNavbar() {
    return(
        <Navbar contentProps={contentProps} />
    )
}