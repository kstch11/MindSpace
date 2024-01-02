import Calendar from "@fullcalendar/react";
import timeGridPlugin from '@fullcalendar/timegrid'


export function Schedule() {
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
                    { title: 'event 1', start: '2024-01-01T09:00:00Z', end: '2024-01-01T10:00:00Z', url: 'https://www.figma.com/file/VbLVj4AqWMIYS6FJ7pGbf9/MindSpace?type=design&node-id=0-1&mode=design&t=ogARHdhOOO2QWYoq-0'},
                    { title: 'event 2', start: '2024-01-02T09:00:00Z', end: '2024-01-02T11:00:00Z' }
                ]}
            />
        </div>
    )
}