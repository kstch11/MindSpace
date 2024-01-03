import {useEffect, useState} from 'react';
import {
    createStyles,
    Container,
    Text,
    useMantineTheme,
    Title,
    Divider,
    rem,
    Button,
    TextInput,
    Loader
} from '@mantine/core';
import axios from 'axios';
import {useSelector} from "react-redux";
import {useQuery} from "@tanstack/react-query";
import {fetchClientProfile, fetchCurrentUser} from "../api/client-api";

const useStyles = createStyles((theme) => ({
    paper: {
        width: 720,
        marginBottom: '15%',
        [theme.fn.smallerThan('md')]: {
            width: 600,
        },
        [theme.fn.smallerThan('sm')]: {
            width: '100%',
            marginBottom: 0,
        },
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

export function ClientData({id}: { id : number }) {
    const {classes} = useStyles();
    const [formData, setFormData] = useState({name: '', surname: '', email: '', phoneNumber: ''});
    const [therapistData, setTherapistData] = useState({});
    const accessToken = useSelector(state => state.currentUser.accessToken);

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
            setFormData({
                name: data.name,
                surname: data.surname,
                phoneNumber: data.phone,
                email: data.email
            })
        }
    }, [data, isFetched]);

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
        setFormData({...formData, [e.target.name]: e.target.value});
    };

    if (isPending) {
        return (
            <Container className={classes.paper}>
                <Loader/>
            </Container>
        )
    }

    if (isError) {
        return <div>Error!</div>
    }

    return (
        <Container className={classes.paper}>
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
        </Container>
    );
}
