import {ClientData} from "./ClientData";
import {ClientSession} from "./ClientSession";
import {ChangeTherapist} from "./ChangeTherapist";
import {Navbar} from "../parts/Navbar";

const contentProps = [
    {
        label: "Personal data",
        order: 1,
        value: 'personalData',
        component: <ClientData id={3} />,
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
    return(
        <Navbar contentProps={contentProps} />
    )
}


