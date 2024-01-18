import React from 'react';
import {createStyles, Avatar, Center, Button, Text, Group, Card, Badge, useMantineTheme, Loader} from '@mantine/core';
import {ReactComponent as IconVideoCamera} from '../assets/video.svg';
import {useMutation, useQuery} from "@tanstack/react-query";
import { fetchClientReservations, fetchCurrentUser} from "../api/client-api";
import {useEffect, useState} from "react";
import {fetchTherapistProfile} from "../api/therapist-api";
import {useSelector} from "react-redux";
import {Schedule} from "./Schedule";
import {deleteReservation} from "../api/reservation-api";

const useStyles = createStyles((theme) => ({
    container: {
        display: 'flex',
        flexDirection: 'column',
        width: 720,
        top: 0,
        marginBottom: '15%',
        [theme.fn.smallerThan('md')]: {
            width: 600,
        },
        [theme.fn.smallerThan('sm')]: {
            width: '100%',
            marginBottom: 0,
        },
    },

    therapistContainer: {
        display: 'flex',
        flexDirection: 'row',
        marginBottom: theme.spacing.md,
    },

    therapistAvatar: {
        marginBottom: theme.spacing.md,
        marginRight: theme.spacing.lg,
    },

    videoCallContainer: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        maxWidth: '100%',
        height: 400,
        backgroundColor: theme.colors.gray[2],
        borderRadius: theme.radius.md,
        padding: theme.spacing.md,
        marginBottom: theme.spacing.md,
    },

    videoCallGroup: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
    },

    sessionDate: {
        display: 'flex',
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
    },

    sessionText: {
        marginBottom: 3,
    },

    badge: {
        marginLeft: theme.spacing.xs,
    },

    startButton: {
        marginTop: theme.spacing.sm,
    },

    schedule: {

    }
}));

export function ClientSession() {
    const theme = useMantineTheme();
    const { classes } = useStyles();
    const accessToken = useSelector(state => state.currentUser.accessToken);
    const [therapistInfo, setTherapistInfo] = useState({
        name: '', surname: '', photo: ''
    });
    const [clientReservation, setClientReservation] = useState(null);

    const isPastDateTime = (date) => {
        const now = new Date();
        const dateTime = new Date(date);
        dateTime.setHours(dateTime.getHours() + 1);

        return dateTime < now
    }

    const {
        isPending,
        isError,
        data,
        isFetched,
        error
    } = useQuery({
            queryKey: ['clientProfile'], queryFn: () => fetchCurrentUser(accessToken)
        }
    )

    useEffect(() => {
        if (isFetched) {
            console.log(data)
        }
    }, [data, isFetched])

    const {
        data: therapist,
        isFetched: fetched,
        isPending: pending,
    } = useQuery({
        queryKey: ['therapistFetching'],
        queryFn: () => data && data.therapistId ? fetchTherapistProfile(data.therapistId, accessToken) : null,
        enabled: !!data && !!data.therapistId,
    })

    useEffect(() => {
        if (fetched) {
            console.log(therapist)
            setTherapistInfo({
                name: therapist.name,
                surname: therapist.surname,
                photo: therapist.photo,
            })
        }
    }, [therapist, fetched])

    const {
        data: reservation,
        isFetched: reservationFetched,
        isPending: reservPending,
        isError: reservError,
    } = useQuery({
        queryKey: ['clientReservations'],
        queryFn: () => data && therapist ? fetchClientReservations(data.id, accessToken) : null,
        enabled: !!data && !!therapist,
    })

    const {
        mutate,
        isSuccess
    } = useMutation({
        mutationFn: (reservationId) => {
            return deleteReservation(accessToken, reservationId)
        }
    })

    useEffect(() => {
        if (reservationFetched && reservation) {
            if (reservation.length !== 0) {
                if (!isPastDateTime(reservation[reservation.length - 1].date)){
                    setClientReservation(reservation)
                    console.log(clientReservation)
                }
            }
        }
    }, [reservation, reservationFetched])

    if (reservPending) {
        return (
            <Center>
                <Loader></Loader>
            </Center>
        )
    }

    const onClickCancel = () => {
        mutate(clientReservation[clientReservation.length - 1].id)
    }

    if (isSuccess) {
        window.location.reload()
    }

    if (clientReservation !== undefined || clientReservation !== null) {
        if (clientReservation.length !== 0) {
            function formatDateString(dateStr) {
                const date = new Date(dateStr);

                const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false };
                return date.toLocaleString('en-US', options);
            }

            const readableDate = formatDateString(clientReservation[reservation.length - 1].start);

            return (
                <div className={classes.container}>
                    <Card shadow="xs" padding="lg" className={classes.videoCallContainer}>
                        <Group position="center" direction="column" spacing="sm" className={classes.videoCallGroup}>
                            <IconVideoCamera size={128} />
                            <div  className={classes.sessionDate}>
                                <Text className={classes.sessionText}>The nearest session:</Text>
                                <Badge color="blue" className={classes.badge}>
                                    {readableDate}
                                </Badge>
                            </div>
                            <Button className={classes.startButton} size="lg" component="a" href="https://meet.google.com/mam-kkih-jsq">
                                Start
                            </Button>
                        </Group>
                    </Card>
                    <div className={classes.therapistContainer}>
                        <Avatar
                            className={classes.therapistAvatar}
                            src={therapistInfo.photo}
                            radius={50}
                            size={100}
                        />
                        <div>
                            <Text size={"sm"} >Your therapist</Text>

                            <Text weight={500} size="lg" style={{ marginBottom: theme.spacing.md }}>
                                {`${therapistInfo.name} ${therapistInfo.surname}`}
                            </Text>
                            <Button onClick={onClickCancel} fullWidth={70}>Cancel reservation</Button>
                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <div className={classes.schedule}>
                    <Schedule></Schedule>
                </div>
            );
        }
    } else {
        return (
            <div className={classes.schedule}>
                <Schedule></Schedule>
            </div>
        );
    }


}

