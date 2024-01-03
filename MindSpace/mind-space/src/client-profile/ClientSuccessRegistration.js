import {Button, Loader} from "@mantine/core";
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
            regDoneMutate()
        }
    }, [isSuccess])

    if (isPending || regDoneIsPending) {
        return <Loader/>
    }

    if (isError || regDoneIsError) {
        return <div> Error</div>
    }


    return (
        <div>
            <div>Registration was complete!</div>
            <Button><a href={"/"}>Home</a></Button>
        </div>
    )
}
