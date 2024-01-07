import {Button, Loader, Center} from "@mantine/core";
import {useSelector} from "react-redux";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchCurrentUser, putTherapist, setRegistrationComplete} from "../api/client-api";
import {useEffect} from "react";
import {Navigate} from "react-router-dom";

export default function ClientSuccessRegistration() {
    const accessToken = useSelector(state => state.currentUser.accessToken);

    const {
        isPending,
        isError,
        data,
        isSuccess,
        error,
    } = useQuery({
            queryKey: ['clientProfile'], queryFn: () => fetchCurrentUser(accessToken)
        }
    )

    const {
        isPending: regDoneIsPending,
        isSuccess: regDoneIsSuccess,
        isError: regDoneIsError,
        mutate: regDoneMutate,
    } = useMutation({
        mutationFn: () => {
            return setRegistrationComplete(accessToken)
        },
    })

    useEffect(() => {
        if (isSuccess) {
            regDoneMutate();
        }
    }, [isSuccess])

    if (isSuccess) {
        return <Navigate to={"/clientProfile"} />
    }

    // if (isPending || regDoneIsPending) {
    //     return <Loader/>
    // }
    //
    // if (isError || regDoneIsError) {
    //     return <div> Error</div>
    // }


    return (
        <Center>
            <Loader/>
        </Center>
    )
}
