import Calendar from "@fullcalendar/react";
import timeGridPlugin from '@fullcalendar/timegrid'
import {useSelector} from "react-redux";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchAllReservations, fetchSchedule, fetchTherapistProfile} from "../api/therapist-api";
import {useEffect, useState} from "react";
import {postReservation} from "../api/reservation-api";
import {fetchClientProfile, fetchCurrentUser} from "../api/client-api";


export function TherapistSchedule() {
    const [scheduleData, setScheduleData] = useState([])
    const accessToken = useSelector(state => state.currentUser.accessToken);

    const {
        data: userData,
        isFetched: userFetch
    } = useQuery({
        queryKey: ['user'],
        queryFn: () => fetchCurrentUser(accessToken)
    })

    const {
        data: userDetails,
        isFetched: userDetailsFetched
    } = useQuery({
        queryKey: ['userDetails'], queryFn: () => fetchTherapistProfile(accessToken, userData.id)
    })

    const {
        isPending,
        isError,
        data,
        isFetched,
        error
    } = useQuery({
        queryKey: ['schedule'],
        queryFn: () => userData && userData.id ? fetchAllReservations(userData.id , accessToken) : null,
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


    useEffect(() => {
        if (isFetched) {
            console.log(data[0])
            const timeCellsArray = data[0].timeCells.map((item, index) => ({
                id: index,
                title: `event ${index + 1}`,
                start: item.startTime,
                end: item.endTime,
                editable: item.isReserved,
            })).filter(item => item.editable)
            console.log(timeCellsArray)
            setScheduleData(timeCellsArray)
            console.log(scheduleData)
        }
    }, [data, isFetched])

    useEffect(() => {
        console.log(scheduleData);

    }, [scheduleData]);

    // const handleEventClick = (clickInfo) => {
    //     clickInfo.jsEvent.preventDefault();
    //
    //     const eventId = parseInt(clickInfo.event.id, 10)
    //
    //     mutate({therapistId: userData.therapistId, clientId: userData.id, timeCellId: eventId + 1})
    // }
    //
    // if (isSuccess) {
    //     setTimeout(function () {window.location.reload()}, 5000)
    // }
    // eventClick={handleEventClick}

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
                nowIndicator={true}
                weekends={false}
                slotMinTime={'09:00:00'}
                slotMaxTime={'18:30:00'}
                height={600}
                events={scheduleData}
            />
        </div>
    )
}