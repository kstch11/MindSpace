import {useMutation} from "@tanstack/react-query";
import {postQuestionnaire} from "../../api/client-api";
import {useSelector} from "react-redux";
import {useEffect} from "react";
import {Loader} from "@mantine/core";

export default function ChooseTherapistStep({form, active}) {
    const accessToken = useSelector(state => state.currentUser.accessToken);

    const {data, isPending, isSuccess, isError, mutate, error} = useMutation({
        mutationFn: (questionnaire) => {
            console.log(accessToken)
            console.log(questionnaire)
            return postQuestionnaire(accessToken, questionnaire)
        },
    })


    useEffect(() => {
        if (active) {
            console.log(form)
            mutate(form.values);
        }
    }, [active])

    if (isPending) {
        return <Loader/>
    }

    if (isError) {
        console.error(error)
        return <div>error</div>
    }

    return (
        <div>
             huj
        </div>
    )
}
