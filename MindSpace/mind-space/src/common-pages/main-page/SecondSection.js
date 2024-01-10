import {
    createStyles,
    Badge,
    Group,
    Title,
    Text,
    Card,
    SimpleGrid,
    Container,
    rem, Image, Divider,
    Button,
    Center,
} from '@mantine/core';
import img1 from "../../assets/1.jpeg";
import img2 from "../../assets/2.jpg";
import img3 from "../../assets/3.jpg";
import img4 from "../../assets/4.jpg";
import img5 from "../../assets/5.jpg";
import img6 from "../../assets/6.jpeg";
import {useState} from "react";
import {Navigate} from "react-router-dom";

const mockdata = [
    {
        title: 'Emotional Regulation',
        description:
            'Psychotherapy assists individuals in understanding and managing their emotions effectively. It helps develop coping strategies, reduce emotional distress, and improve emotional resilience.',
        image: img1,
    },
    {
        title: 'Self-Exploration and Personal Growth',
        description:
            'Therapy offers a space for self-reflection, self-discovery, and personal growth. It helps individuals gain insight into their thoughts, behaviors, and beliefs, fostering self-awareness and facilitating personal development.',
        image: img2,
    },
    {
        title: 'Relationship Issues',
        description:
            'Therapy can address challenges related to relationships, such as communication problems, conflict resolution, intimacy issues, and building healthier connections with others.',
        image: img5,
    },
    {
        title: 'Coping with Life Transitions',
        description:
            'Life transitions, such as career changes, relocation, loss, or major life events, can be challenging. Psychotherapy can assist in navigating these transitions, providing support, guidance, and coping strategies to adapt and thrive during periods of change.',
        image: img3,
    },
    {
        title: 'Mental Health Conditions',
        description:
            'Psychotherapy is commonly used to treat mental health conditions such as depression, anxiety, post-traumatic stress disorder (PTSD), obsessive-compulsive disorder (OCD), and many others. It provides support, symptom management, and tools for recovery.',
        image: img4,
    },
    {
        title: 'Stress Management',
        description:
            'Therapy equips individuals with techniques and strategies to manage stress effectively. It helps identify stressors, develop healthy coping mechanisms, and promote overall well-being.',
        image: img6,
    },
];

const useStyles = createStyles((theme) => ({
    title: {
        fontSize: rem(34),
        fontWeight: 900,

        [theme.fn.smallerThan('sm')]: {
            fontSize: rem(24),
        },
    },

    description: {
        maxWidth: 600,
        margin: 'auto',

        '&::after': {
            content: '""',
            display: 'block',
            backgroundColor: theme.fn.primaryColor(),
            width: rem(45),
            height: rem(2),
            marginTop: theme.spacing.sm,
            marginLeft: 'auto',
            marginRight: 'auto',
        },
    },

    card: {
        border: `${rem(1)} solid ${
            theme.colorScheme === 'dark' ? theme.colors.dark[5] : theme.colors.gray[1]
        }`,
    },

    cardTitle: {
        '&::after': {
            content: '""',
            display: 'block',
            backgroundColor: theme.fn.primaryColor(),
            width: rem(45),
            height: rem(2),
            marginTop: theme.spacing.sm,
        },
    },

    button: {
        marginTop: rem(25),
        marginBottom: rem(13),
    }
}));



export function FeaturesCards() {
    const { classes, theme } = useStyles();
    const [redirect, setRedirect] = useState(false);

    const handleLogin = () => {
        setRedirect(true);
    }
    if (redirect) {
        return <Navigate to="login"/>
    }

    const features = mockdata.map((feature) => (
        <Card key={feature.title} shadow="md" radius="md" className={classes.card} padding="xl">
            {/*<feature.image size={rem(50)} stroke={2} color={theme.fn.primaryColor()} />*/}
            <Image src={feature.image} size={rem(50)} stroke={2} color={theme.fn.primaryColor()} />
            <Text fz="lg" fw={500} className={classes.cardTitle} mt="md">
                {feature.title}
            </Text>
            <Text fz="sm" c="dimmed" mt="sm">
                {feature.description}
            </Text>
        </Card>
    ));



    return (
        <Container size="lg" py="xl">
            <Group position="center">
                <Badge variant="filled" size="lg">
                    MindSpace
                </Badge>
            </Group>

            <Title order={2} className={classes.title} ta="center" mt="sm">
                Regular sessions with a therapist improve the quality of life
            </Title>

            <SimpleGrid cols={3} spacing="xl" mt={50} breakpoints={[{ maxWidth: 'sm', cols: 1 }]}>
                {features}
            </SimpleGrid>
            <Center>
                <Button
                    component="a"
                    href=""
                    className={classes.button}
                    onClick={handleLogin}
                    variant="gradient"
                    gradient={{ from: 'indigo', to: 'cyan' }}>Want to discuss my situation</Button>
            </Center>
        </Container>
    );
}
