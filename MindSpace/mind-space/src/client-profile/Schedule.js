import Calendar from "@fullcalendar/react";
import timeGridPlugin from '@fullcalendar/timegrid'
import {useSelector} from "react-redux";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchSchedule} from "../api/therapist-api";
import {useEffect, useState} from "react";
import {postReservation} from "../api/reservation-api";
import {fetchClientProfile, fetchCurrentUser} from "../api/client-api";
import {createStyles} from "@mantine/core";

const useStyles = createStyles((theme) => ({
    container: {
        width: 700,
    }
}))

export function Schedule() {
    const [scheduleData, setScheduleData] = useState([])
    const {classes} = useStyles();
    const accessToken = useSelector(state => state.currentUser.accessToken);

    const {
        data: userData,
        isFetched: userFetch
    } = useQuery({
        queryKey: ['user'],
        queryFn: () => fetchCurrentUser(accessToken)
    })

    const {
        isPending,
        isError,
        data,
        isFetched,
        error
    } = useQuery({
        queryKey: ['schedule'],
        queryFn: () => fetchSchedule(accessToken),
        enabled: !!userData
    })

    const {
        data: userDetails,
        isFetched: userDetailsFetched
    } = useQuery({
        queryKey: ['userDetails'],
        queryFn: () => userData && userData.id ? fetchClientProfile(userData.id, accessToken) : null,
        enabled: !!userData && !!userData.id
    })

    useEffect(() => {
        if (userFetch) {
            console.log(userData)
        }
    })

    useEffect(() => {
        if (userDetailsFetched) {
            console.log(userDetails)
        }
    })


    const {
        isPending: creationReservationPending,
        isSuccess,
        isError: creationReservationError,
        mutate
    } = useMutation({
        mutationFn: (reservation) => {
            return postReservation(accessToken, reservation)
        }
    })

    useEffect(() => {
        if (isFetched) {
            console.log(userData.therapistId)
            const timeCellsArray = data[userData.therapistId - 1].timeCells.map((item) => ({
                id: item.id,
                title: 'Free time slot',
                start: item.startTime,
                end: item.endTime,
                editable: item.isReserved,
            })).filter(item => !item.editable)
            console.log(timeCellsArray)
            setScheduleData(timeCellsArray)
            console.log(scheduleData)
        }
    }, [data, isFetched])

    useEffect(() => {
        console.log(scheduleData);

    }, [scheduleData]);

    useEffect(() => {
        if (isSuccess) {
            console.log("reservation created!")
        }
    }, [isSuccess])

    const handleEventClick = (clickInfo) => {
        clickInfo.jsEvent.preventDefault();

        const eventId = parseInt(clickInfo.event.id, 10)
        console.log(eventId)

        mutate({therapistId: userData.therapistId, clientId: userData.id, timeCellId: eventId})
    }

    if (isSuccess) {
        window.location.reload()
    }

    return(
        <div className={classes.container}>
            <Calendar
                plugins={[ timeGridPlugin ]}
                initialView="timeGridWeek"
                headerToolbar={{
                    left: 'prev,next',
                    center: 'title',
                    right: 'timeGridWeek,timeGridDay'
                }}
                nowIndicator={true}
                weekends={false}
                slotMinTime={'09:00:00'}
                slotMaxTime={'18:30:00'}
                height={600}
                events={scheduleData}
                eventClick={handleEventClick}
            />
        </div>
    )
}
