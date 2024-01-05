import Calendar from "@fullcalendar/react";
import timeGridPlugin from '@fullcalendar/timegrid'
import {useSelector} from "react-redux";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchSchedule} from "../api/therapist-api";
import {useEffect, useState} from "react";
import {postReservation} from "../api/reservation-api";
import {fetchCurrentUser} from "../api/client-api";


export function Schedule() {
    const [scheduleData, setScheduleData] = useState([])
    const accessToken = useSelector(state => state.currentUser.accessToken);
    let timeCellsArray = []

    const {
        isPending,
        isError,
        data,
        isFetched,
        error
    } = useQuery({
        queryKey: ['schedule'], queryFn: () => fetchSchedule(accessToken)
    })

    const {
        data: userData,
        isFetched: userFetch
    } = useQuery({
        queryKey: ['user'], queryFn: () => fetchCurrentUser(accessToken)
    })

    useEffect(() => {
        if (userFetch) {
            console.log(userData)
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
            console.log(data[0])
            timeCellsArray = data[0].timeCells.map((item, index) => ({
                id: index,
                title: `event ${index + 1}`,
                start: item.startTime,
                end: item.endTime,
            }))
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
        mutate({therapistId: 1, clientId: 11, timeCellId: eventId})
    }

    return(
        <div>
            <Calendar
                plugins={[ timeGridPlugin ]}
                initialView="timeGridWeek"
                headerToolbar={{
                    left: 'prev,next',
                    center: 'title',
                    right: 'timeGridWeek,timeGridDay'
                }}
                events={scheduleData}
                eventClick={handleEventClick}
            />
        </div>
    )
}