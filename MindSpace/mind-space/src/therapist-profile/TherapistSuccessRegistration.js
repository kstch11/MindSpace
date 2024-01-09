import {useSelector} from "react-redux";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchCurrentUser, setRegistrationComplete} from "../api/client-api";
import {useEffect} from "react";
import {Navigate} from "react-router-dom";
import {Center, Loader} from "@mantine/core";
import {setTherapistApplicationComplete} from "../api/therapist-api";


export default function TherapistSuccessRegistration() {
    const accessToken = useSelector(state => state.currentUser.accessToken);

    const {
        isPending,
        isError,
        data,
        isSuccess,
        error,
    } = useQuery({
            queryKey: ['therapistProfile'], queryFn: () => fetchCurrentUser(accessToken)
        }
    )

    const {
        isPending: regDoneIsPending,
        isSuccess: regDoneIsSuccess,
        isError: regDoneIsError,
        mutate: regDoneMutate,
    } = useMutation({
        mutationFn: () => {
            return setTherapistApplicationComplete(accessToken)
        },
    })

    useEffect(() => {
        if (isSuccess) {
            regDoneMutate();
        }
    }, [isSuccess])

    if (isSuccess) {
        return <Navigate to={"/logout"} />
    }

    if (isPending || regDoneIsPending) {
        return (
            <Center>
                <Loader/>
            </Center>
        )
    }

    if (isError || regDoneIsError) {
        return <div> Error</div>
    }


    return (
        <Center>
            <Loader/>
        </Center>
    )
}