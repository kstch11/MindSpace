import { useEffect, useState } from 'react';
import {createStyles, Container, Text, useMantineTheme, Title, Divider, rem, Button, TextInput} from '@mantine/core';
import axios from 'axios';

const useStyles = createStyles((theme) => ({
    paper: {
        maxWidth: rem(900),
        // padding: theme.spacing.md,
        // width: 1300,
        // margin: '0 auto',
        // [theme.fn.smallerThan('lg')]: {
        //     width: 800,
        // },
        // [theme.fn.smallerThan('md')]: {
        //     width: 600,
        // },
        // [theme.fn.smallerThan('sm')]: {
        //     width: 400,
        // },
        // [theme.fn.smallerThan('xs')]: {
        //     width: 300,
        // },
    },

    title: {
        marginBottom: rem(30),
    },

    form: {
        width: '100%',
        display: 'flex',
        marginBottom: theme.spacing.lg,
        fontSize: rem(17),
        '& label': {
            width: '40%',
            marginRight: theme.spacing.xs,
            fontWeight: 600,
        },
        '& > div': {
            width: '60%',
        },
    },

    textInput: {
      marginRight: '5%',
    },

    divider: {
        marginTop: theme.spacing.xl,
        marginBottom: theme.spacing.xl,
    },

    button: {
        align: 'right',
        width: 100,
    },
}));

export function ClientData({id} : { id : number }) {
    const theme = useMantineTheme();
    const { classes } = useStyles();
    const [formData, setFormData] = useState({});
    const [therapistData, setTherapistData] = useState({});


    const getClientData = async () => {
        try {
            const response = await axios.get(`https://localhost:8090/clients/${id}`);
            return response.data;
        } catch (err) {
            console.error(err);
            return null;
        }
    };

    useEffect(() => {
        const fetchClientData = async () => {
            const fetchedClient = await getClientData();

            if (fetchedClient) {
                setFormData(fetchedClient);
            }
        }
        fetchClientData();
    }, [id]);

    const getTherapist = async () => {
        try {
            const response = await axios.get(`https://localhost:8090/clients/${id}/therapist`);
            return response.data;
        } catch (err) {
            console.error(err);
            return null;
        }
    };

    useEffect(() => {
        const fetchTherapistData = async () => {
            const fetchedTherapist = await getTherapist();
            if (fetchedTherapist) {
                setTherapistData(fetchedTherapist)
            }
        }
        fetchTherapistData();
    }, [])



    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    return (
        <div className={classes.paper}>
            <div>
                <Title order={2} weight={700} className={classes.title}>
                    Personal Information
                </Title>
                <form>
                    <div className={classes.form}>
                        <label htmlFor="name">Name:</label>
                        <TextInput
                            className={classes.textInput}
                            id="name"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                        />
                    </div>
                    <div className={classes.form}>
                        <label htmlFor="name">Surname:</label>
                        <TextInput
                            className={classes.textInput}
                            id="surname"
                            name="surname"
                            value={formData.surname}
                            onChange={handleChange}
                        />
                    </div>
                    <div className={classes.form}>
                        <label htmlFor="email">Email:</label>
                        <TextInput
                            className={classes.textInput}
                            id="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                        />
                    </div>
                    <div className={classes.form}>
                        <label htmlFor="phoneNumber">Phone number:</label>
                        <TextInput
                            className={classes.textInput}
                            id="phoneNumber"
                            name="phoneNumber"
                            value={formData.phoneNumber}
                            onChange={handleChange}
                        />
                    </div>
                </form>
            </div>
            <Divider className={classes.divider} />
            <div>
                <Title order={2} weight={700} className={classes.title}>
                    Therapy information
                </Title>
                <form>
                    <div className={classes.form}>
                        <label>Therapist:</label>
                        <Text className={classes.text}>{therapistData.name} {therapistData.surname}</Text>
                    </div>
                    <div className={classes.form}>
                        <label>Reservation:</label>
                        <Text className={classes.text}>Monday, 29th of May, 9:00</Text>
                    </div>
                    <div className={classes.form}>
                        <label>Kind of therapy:</label>
                        <Text className={classes.text}>Individual</Text>
                    </div>
                </form>
            </div>
            <Button className={classes.button}>Save</Button>
        </div>
    );
}
