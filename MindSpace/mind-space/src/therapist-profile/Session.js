import React from 'react';
import { createStyles, Avatar, Paper, Button, Text, Group, Card, Badge, useMantineTheme } from '@mantine/core';
import {ReactComponent as IconVideoCamera} from '../assets/video.svg';

const useStyles = createStyles((theme) => ({
    container: {
        display: 'flex',
        flexDirection: 'row',
        width: 1000,
        top: 0,
    },
    therapistContainer: {
        display: 'flex',
        flexDirection: 'row',
        width: '30%',
        marginRight: '20%',
        marginBottom: theme.spacing.md,
    },
    therapistAvatar: {
        marginBottom: theme.spacing.md,
        marginRight: theme.spacing.lg,
    },
    videoCallContainer: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        width: 400,
        height: 300,
        backgroundColor: theme.colors.gray[2],
        borderRadius: theme.radius.md,
        padding: theme.spacing.md,
        marginBottom: theme.spacing.md,
    },
    startButton: {
        marginTop: theme.spacing.sm,
    },
}));

export function Session() {
    const theme = useMantineTheme();
    const { classes } = useStyles();

    return (
        <div className={classes.container}>
            <div className={classes.therapistContainer}>
                <Avatar
                    className={classes.therapistAvatar}
                    src="https://example.com/therapist-photo.jpg"
                    radius="xl"
                    size={120}
                />
                <div>
                    <Text size={"sm"} >Your Client</Text>

                    <Text weight={500} size="xl" style={{ marginBottom: theme.spacing.md }}>
                        Client's Name
                    </Text>
                    <Button fullWidth={70}>Chat</Button>
                </div>
            </div>


            <Card shadow="xs" padding="lg" className={classes.videoCallContainer}>
                <Group position="center" direction="column" spacing="sm">
                    <IconVideoCamera size={64} />
                    <Text>The nearest session:</Text>
                    <Badge color="blue" style={{ marginTop: theme.spacing.xs }}>
                        Start Date/Time
                    </Badge>
                    <Button
                        className={classes.startButton}
                        size="lg"
                    >
                        Start
                    </Button>
                </Group>
            </Card>
        </div>
    );
}