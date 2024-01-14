import Calendar from "@fullcalendar/react";
import timeGridPlugin from '@fullcalendar/timegrid'
import {useSelector} from "react-redux";
import {useQuery} from "@tanstack/react-query";
import {fetchAllReservations} from "../api/therapist-api";
import {useEffect, useState} from "react";
import { fetchCurrentUser} from "../api/client-api";
import {createStyles} from "@mantine/core";

const useStyles = createStyles((theme) => ({
    container: {
        width: 700,
        [theme.fn.smallerThan("sm")]: {
            width: '100%'
        }
    }
}))

export function TherapistSchedule() {
    const {classes} = useStyles();
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
        if (isFetched) {
            console.log(data)
            const reservationsArray = data.map((item) => ({
                title: `${item.clientResponse.name} ${item.clientResponse.surname}`,
                start: item.start,
                end: item.end
            }))
            setScheduleData(reservationsArray)
            console.log(reservationsArray)
        }
    }, [data, isFetched])

    useEffect(() => {
        console.log(scheduleData);

    }, [scheduleData]);

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
            />
        </div>
    )
}