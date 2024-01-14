import {Avatar, Badge, Button, Card, createStyles, Group, Text, useMantineTheme, Center} from "@mantine/core";
import {ReactComponent as IconVideoCamera} from "../../assets/video.svg";
import React, {useEffect} from "react";
import {useSelector} from "react-redux";
import {useQuery} from "@tanstack/react-query";
import {fetchCurrentUser} from "../../api/client-api";
import {fetchAllReservations} from "../../api/therapist-api";
import {useState} from "react";

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

    warning: {
        marginBottom: 360,
    }

}));

export function TherapistSession() {
    const theme = useMantineTheme();
    const { classes } = useStyles();
    const accessToken = useSelector(state => state.currentUser.accessToken);
    const [reservationData, setReservationData] = useState({
        name: '', surname: '', start: ''
    })

    const {
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
        data: reservations,
        isFetched: fetchedReserv,
        error: reservError
    } = useQuery({
        queryKey: ['schedule'],
        queryFn: () => data && data.id ? fetchAllReservations(data.id , accessToken) : null,
        enabled: !!data && !!data.id
    })

    useEffect(() => {
        if (fetchedReserv) {
            console.log(reservations)
            if (reservations.length !== 0) {
                setReservationData({
                    name: reservations[0].clientResponse.name,
                    surname: reservations[0].clientResponse.surname,
                    start: reservations[0].start
                })
            }
        }
    })

    if (reservations !== undefined) {
        if (reservations.length !== 0) {
            function formatDateString(dateStr) {
                const date = new Date(dateStr);

                const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false };
                return date.toLocaleString('en-US', options);
            }
            const readableDate = formatDateString(reservationData.start)

            return(
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
                        <div>
                            <Text size={"sm"} >Client</Text>

                            <Text weight={500} size="lg" style={{ marginBottom: theme.spacing.md }}>
                                `${reservationData.name} ${reservationData.surname}`
                            </Text>
                        </div>
                    </div>
                </div>
            )
        } else {
            return (
                <Center className={classes.container}>
                    <Text className={classes.warning}>You still do not have any sessions.</Text>
                </Center>
            )
        }
    } else {
        return (
            <Center className={classes.container}>
                <Text className={classes.warning}>You still do not have any sessions.</Text>
            </Center>
        )
    }

}