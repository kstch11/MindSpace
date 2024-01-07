import React from 'react';
import {createStyles, Avatar, Center, Button, Text, Group, Card, Badge, useMantineTheme, Loader} from '@mantine/core';
import {ReactComponent as IconVideoCamera} from '../assets/video.svg';
import {useQuery} from "@tanstack/react-query";
import {fetchClientProfile, fetchClientReservations, fetchCurrentUser} from "../api/client-api";
import {useEffect, useState} from "react";
import {fetchTherapistProfile} from "../api/therapist-api";
import {useSelector} from "react-redux";
import {Schedule} from "../therapist-profile/Schedule";

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
}));

export function ClientSession() {
    const theme = useMantineTheme();
    const { classes } = useStyles();
    const accessToken = useSelector(state => state.currentUser.accessToken);
    const [therapistInfo, setTherapistInfo] = useState({
        name: '', surname: '', photo: ''
    });
    const [clientReservation, setClientReservation] = useState(null);

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
        queryFn: () => data && data.id ? fetchClientReservations(data.id, accessToken) : null,
        enabled: !!data && !!data.id,
    })

    useEffect(() => {
        if (reservationFetched) {
            console.log(reservation);
            setClientReservation(reservation)
            console.log(clientReservation)
        }
    }, [reservation, reservationFetched])

    if (reservPending) {
        return (
            <Center>
                <Loader></Loader>
            </Center>
        )
    }
    console.log(clientReservation)

    if (clientReservation !== null) {
        console.log(clientReservation)
        if (clientReservation.length !== 0) {
            const formattedDate = (dateString) => {
                const [time, date] = dateString.split(' ');
                const [hours, minutes] = time.split(':');
                const [day, month, year] = date.split('/');

                const dateObj = new Date(year, month - 1, day, hours, minutes);

                return `${dateObj.toLocaleDateString()} at ${dateObj.toLocaleTimeString()}`;
            }

            const readableDate = formattedDate(clientReservation[0].date)

            return (
                <div className={classes.container}>
                    <Card shadow="xs" padding="lg" className={classes.videoCallContainer}>
                        <Group position="center" direction="column" spacing="sm" className={classes.videoCallGroup}>
                            <IconVideoCamera size={128} />
                            <div  className={classes.sessionDate}>
                                <Text className={classes.sessionText}>The nearest session:</Text>
                                <Badge color="blue" className={classes.badge}>{readableDate}
                                </Badge>
                            </div>
                            <Button className={classes.startButton} size="lg">
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
                            <Button fullWidth={70}>Chat</Button>
                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <Schedule></Schedule>
            );
        }
    } else {
        return (
            <Schedule></Schedule>
        );
    }


}

