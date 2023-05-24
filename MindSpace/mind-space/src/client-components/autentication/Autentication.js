import React from "react";
import { useToggle, upperFirst } from '@mantine/hooks';
import { useForm } from '@mantine/form';
import {
    TextInput,
    PasswordInput,
    Text,
    Paper,
    Group,
    PaperProps,
    Button,
    Divider,
    Checkbox,
    Anchor,
    Stack, createStyles, rem, Center
} from '@mantine/core';
import { GoogleButton } from "./GoogleButton";

const useStyles = createStyles((theme) => ({
    logform: {
        width: rem(420),
        [theme.fn.smallerThan('xs')]: {
            width: rem(360),
        },
    }
}))

export function AuthenticationForm(props: PaperProps) {
    const [type, toggle] = useToggle(['login', 'register']);
    const {classes} = useStyles();
    const form = useForm({
        initialValues: {
            email: '',
            name: '',
            password: '',
            terms: false,
        },

        validate: {
            email: (val) => (/^\S+@\S+$/.test(val) ? null : 'Invalid email'),
            password: (val) => (val.length < 6 ? 'Password should include at least 6 characters' : null),
            confirmPassword: (val) => {
                if (form.values.type === 'register' && val !== form.values.password) {
                    return 'Passwords do not match';
                }
                return null;
            },
        },
    });

    const authenticationFormStyle = {
        marginBottom: form.values.type === 'register' ? '80px' : '65px',
    };

    return (
        <Center>
            <Paper className={classes.logform} radius="md" p="xl"  withBorder {...props} style={authenticationFormStyle}>
                <Text size="lg" weight={500}>
                    Welcome to MindSpace, {type} with
                </Text>

                <Group grow mb="md" mt="md">
                    <GoogleButton radius="xl">Google</GoogleButton>
                </Group>

                <Divider label="Or continue with email" labelPosition="center" my="lg" />

                <form onSubmit={form.onSubmit(() => (window.location.href = '/questionnaire'))}>
                    <Stack>
                        <TextInput
                            required
                            label="Email"
                            placeholder="hello@mindspace.dev"
                            value={form.values.email}
                            onChange={(event) => form.setFieldValue('email', event.currentTarget.value)}
                            error={form.errors.email && 'Invalid email'}
                            radius="md"
                        />

                        <PasswordInput
                            required
                            label="Password"
                            placeholder="Your password"
                            value={form.values.password}
                            onChange={(event) => form.setFieldValue('password', event.currentTarget.value)}
                            error={form.errors.password && 'Password should include at least 6 characters'}
                            radius="md"
                        />

                        {type === 'register' && ([
                            <PasswordInput
                                required
                                label="Repeat the password"
                                placeholder="Your password"
                                value={form.values.confirmPassword}
                                onChange={(event) => form.setFieldValue('password', event.currentTarget.value)}
                                error={form.errors.confirmPassword && 'Passwords do not match'}
                                radius="md"
                            />,
                        ])}

                        {type === 'register' && (
                            <Checkbox
                                label="I accept terms and conditions"
                                checked={form.values.terms}
                                onChange={(event) => form.setFieldValue('terms', event.currentTarget.checked)}
                            />
                        )}
                    </Stack>

                    <Group position="apart" mt="xl">
                        <Anchor
                            component="button"
                            type="button"
                            color="dimmed"
                            onClick={() => toggle()}
                            size="xs"
                        >
                            {type === 'register'
                                ? 'Already have an account? Login'
                                : "Don't have an account? Register"}
                        </Anchor>
                        <Button type="submit" radius="xl">
                            {upperFirst(type)}
                        </Button>
                    </Group>
                </form>
            </Paper>
        </Center>
    );
}