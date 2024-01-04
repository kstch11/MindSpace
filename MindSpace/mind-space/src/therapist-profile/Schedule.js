import Calendar from "@fullcalendar/react";
import timeGridPlugin from '@fullcalendar/timegrid'
import {useSelector} from "react-redux";
import {useQuery} from "@tanstack/react-query";
import {fetchSchedule} from "../api/therapist-api";
import {useEffect, useState} from "react";


export function Schedule() {
    // const [scheduleData, setScheduleData] = useState({timeCells: []})
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

    useEffect(() => {
        if (isFetched) {
            console.log(data[0].timeCells)
            timeCellsArray = data[0].timeCells.map((item, index) => ({
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
            />
        </div>
    )
}