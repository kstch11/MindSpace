import Calendar from "@fullcalendar/react";
import timeGridPlugin from '@fullcalendar/timegrid'
import {useSelector} from "react-redux";
import {useQuery} from "@tanstack/react-query";
import {fetchSchedule} from "../api/therapist-api";
import {useEffect, useState} from "react";


export function Schedule() {
    const [scheduleData, setScheduleData] = useState({timeCells: []})
    const accessToken = useSelector(state => state.currentUser.accessToken);

    const {
        isPending,
        isError,
        data,
        isFetched,
        error
    } = useQuery({
        queryKey: ['schedule'], queryFn: () => fetchSchedule(accessToken)
    })

    useEffect(() => {
        console.log(data)
    })

    return(
        <div>
            <Calendar
                plugins={[ timeGridPlugin ]}
                initialView="timeGridWeek"
                weekends={false}
                headerToolbar={{
                    left: 'prev,next',
                    center: 'title',
                    right: 'timeGridWeek,timeGridDay'
                }}
                events={[
                    { title: 'event 1', start: '2024-01-01T09:00:00Z', end: '2024-01-01T10:00:00Z' },
                    { title: 'event 2', start: '2024-01-02T09:00:00Z', end: '2024-01-02T11:00:00Z' }
                ]}
            />
        </div>
    )
}