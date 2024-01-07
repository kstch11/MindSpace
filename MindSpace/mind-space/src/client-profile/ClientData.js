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
import {useSelector} from "react-redux";
import {useQuery} from "@tanstack/react-query";
import {fetchCurrentUser} from "../api/client-api";
import {fetchTherapistProfile} from "../api/therapist-api";

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

export function ClientData() {
    const {classes} = useStyles();
    const [formData, setFormData] = useState({name: '', surname: '', email: '', phoneNumber: '', therapistId: null});
    const [therapistData, setTherapistData] = useState({name: '', surname: ''});
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
            console.log(data.therapistId)
            setFormData({
                name: data.name,
                surname: data.surname,
                phoneNumber: data.phone,
                email: data.email,
                therapistId: data.therapistId
            })

        }
    }, [data, isFetched]);

    const {
        isPending: therapistPending,
        data: fetchedTherapist,
        isFetched: therapistFetched
    } = useQuery({
        queryKey: ['clientTherapist'],
        queryFn: () => data && data.therapistId ? fetchTherapistProfile(data.therapistId, accessToken) : null,
        enabled: !!data && !!data.therapistId,
    })

    useEffect(() => {
        if (therapistFetched) {
            console.log(fetchedTherapist)
            setTherapistData({
                name: fetchedTherapist.name,
                surname: fetchedTherapist.surname,
            })
        }
    },  [fetchedTherapist, therapistFetched])

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

    const fullName = `${therapistData.name} ${therapistData.surname}`;

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
                        <Text className={classes.text}>{fullName}</Text>
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
