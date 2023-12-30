import React from 'react';
import {createStyles, Avatar, Paper, Button, Text, Group, Card, Badge, useMantineTheme, rem} from '@mantine/core';
import {ReactComponent as IconVideoCamera} from '../assets/video.svg';

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

    return (
        <div className={classes.container}>
            <Card shadow="xs" padding="lg" className={classes.videoCallContainer}>
                <Group position="center" direction="column" spacing="sm" className={classes.videoCallGroup}>
                    <IconVideoCamera size={128} />
                    <div  className={classes.sessionDate}>
                        <Text className={classes.sessionText}>The nearest session:</Text>
                        <Badge color="blue" className={classes.badge}>
                            Start Date/Time
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
                    src="https://example.com/therapist-photo.jpg"
                    radius="xl"
                    size={120}
                />
                <div>
                    <Text size={"sm"} >Your therapist</Text>

                    <Text weight={500} size="lg" style={{ marginBottom: theme.spacing.md }}>
                        Therapist's Name
                    </Text>
                    <Button fullWidth={70}>Chat</Button>
                </div>
            </div>
        </div>
    );
}

