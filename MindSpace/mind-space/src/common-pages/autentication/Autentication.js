import React from "react";
import { useToggle } from '@mantine/hooks';
import {
    Text,
    Paper,
    Group,
    PaperProps,
    Divider,
    Anchor, createStyles, rem, Center
} from '@mantine/core';
import { GoogleButton } from "./GoogleButton";
import {AUTH_LOCAL_LINK} from "../../api/defaults";

const useStyles = createStyles((theme) => ({
    logform: {
        width: rem(420),
        // marginBottom: '22%',
        // [theme.fn.smallerThan('xl')]: {
        //     marginBottom: '10%',
        // },
        [theme.fn.smallerThan('xs')]: {
            width: rem(360),
        },
    }
}))

export function AuthenticationForm(props: PaperProps) {
    const [type, toggle] = useToggle(['client', 'therapist']);
    const {classes} = useStyles();

    const handleGoogleLogin = async (therapistLogin = false) => {
        if (therapistLogin) {
            window.location.href = AUTH_LOCAL_LINK + "&role=THERAPIST";
        } else {
            window.location.href = AUTH_LOCAL_LINK;
        }
    }

    return (
        <Center>
            <Paper className={classes.logform} radius="md" p="xl"  withBorder {...props}>
                <Text size="lg" weight={500}>
                    {type === 'client' ? 'Welcome to MindSpace, log in with' : 'Login for registered therapists'}
                </Text>

                <Group grow mb="md" mt="md">
                    <GoogleButton onLoginClick={async () => await handleGoogleLogin(type === 'therapist')}>Google</GoogleButton>
                </Group>

                <Divider labelPosition="center" my="lg" />

                <Text>Warning! At the moment only authorization via Google is available. The possibility of regular
                    registration and login will be added in the near future. Thank you for your understanding.
                </Text>
                <Group position="apart" mt="xl">
                    <Anchor
                        component="button"
                        type="button"
                        color="dimmed"
                        onClick={() => toggle()}
                        size="xs"
                    >
                        {type === 'client'
                            ? 'Login for therapists'
                            : null}
                    </Anchor>
                </Group>
            </Paper>
        </Center>
    );
}
