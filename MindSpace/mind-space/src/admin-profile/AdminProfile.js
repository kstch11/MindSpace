import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";
import {Navbar} from "../parts/Navbar";
import ListOfTherapists from "./ListOfTherapists";

const contentProps = [
    {
        label: "List of therapists",
        order: 1,
        value: 'listOfTherapists',
        component: <ListOfTherapists />,
    },
]

export default function AdminProfile() {
    const isAdmin = useSelector(state => state.currentUser.profile.isAdmin);
    if (!isAdmin) {
        return <Navigate to={"/clientProfile"} />
    }

    return(
        <Navbar contentProps={contentProps} />
    )
}
