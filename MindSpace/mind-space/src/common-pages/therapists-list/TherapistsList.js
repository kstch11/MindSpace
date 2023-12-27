import {createStyles, SimpleGrid, Pagination, rem, Title, Center, Transition} from "@mantine/core";
import {TherapistCard} from "./TherapistCard";
import {useState} from "react";
import { IconSearch, IconVideo, IconUsers } from '@tabler/icons-react';
import {InformationCard} from "./InformationCard";

const useStyles = createStyles((theme) =>({
    inner: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        paddingTop: theme.spacing.md,
        paddingBottom: `calc(${theme.spacing.xl} * 5)`,
        flexDirection: 'column',
    },

    content: {
        maxWidth: rem(950),
        marginRight: `0 auto`,

        [theme.fn.smallerThan('sm')]: {
            maxWidth: '100%',
            margin: '0 3%',
        },
    },

    title: {
      marginBottom: theme.spacing.sm,
    },

    pagination: {
        marginTop: 40,
    }
}));

export function TherapistsList() {
    const {classes} = useStyles();
    const [activePage, setActivePage] = useState(1);

    const users = [
        {
            avatarUrl:'https://www.perfocal.com/blog/content/images/size/w960/2021/01/Perfocal_17-11-2019_TYWFAQ_100_standard-3.jpg',
            name: 'Olivia Anderson',
            experience: '5 years',
            specialization: [
                'Depression',
                'Burnout',
                'Procrastination',
                'Anxiety',
                'Lack of motivation',
                'Panic attacks',
            ],
            description:'I can provide professional psychotherapeutic help in a wide range of requests: problems at work and in personal life, difficulties in self-determination, general dissatisfaction and apathy. Help in crises that occur because of events (divorce, death of loved ones, adultery), as well as those crises that spontaneously invade life, come as if from nowhere - the search for meaning, dissatisfaction, fears, the desire to change something, get rid of something that causes discomfort or heartache. I am ready to walk this path with you.',
        },
        {
            avatarUrl:'https://www.perfocal.com/blog/content/images/size/w960/2021/01/Perfocal_17-11-2019_TYWFAQ_100_standard-3.jpg',
            name: 'Olivia Anderson',
            experience: '5 years',
            specialization: [
                'Depression',
                'Burnout',
                'Procrastination',
                'Anxiety',
                'Lack of motivation',
                'Panic attacks',
            ],
            description:'I can provide professional psychotherapeutic help in a wide range of requests: problems at work and in personal life, difficulties in self-determination, general dissatisfaction and apathy. Help in crises that occur because of events (divorce, death of loved ones, adultery), as well as those crises that spontaneously invade life, come as if from nowhere - the search for meaning, dissatisfaction, fears, the desire to change something, get rid of something that causes discomfort or heartache. I am ready to walk this path with you.',
        },
        {
            avatarUrl:'https://www.perfocal.com/blog/content/images/size/w960/2021/01/Perfocal_17-11-2019_TYWFAQ_100_standard-3.jpg',
            name: 'Olivia Anderson',
            experience: '5 years',
            specialization: [
                'Depression',
                'Burnout',
                'Procrastination',
                'Anxiety',
                'Lack of motivation',
                'Panic attacks',
            ],
            description:'I can provide professional psychotherapeutic help in a wide range of requests: problems at work and in personal life, difficulties in self-determination, general dissatisfaction and apathy. Help in crises that occur because of events (divorce, death of loved ones, adultery), as well as those crises that spontaneously invade life, come as if from nowhere - the search for meaning, dissatisfaction, fears, the desire to change something, get rid of something that causes discomfort or heartache. I am ready to walk this path with you.',
        },
        {
            avatarUrl:'https://www.perfocal.com/blog/content/images/size/w960/2021/01/Perfocal_17-11-2019_TYWFAQ_100_standard-3.jpg',
            name: 'Olivia Anderson',
            experience: '5 years',
            specialization: [
                'Depression',
                'Burnout',
                'Procrastination',
                'Anxiety',
                'Lack of motivation',
                'Panic attacks',
            ],
            description:'I can provide professional psychotherapeutic help in a wide range of requests: problems at work and in personal life, difficulties in self-determination, general dissatisfaction and apathy. Help in crises that occur because of events (divorce, death of loved ones, adultery), as well as those crises that spontaneously invade life, come as if from nowhere - the search for meaning, dissatisfaction, fears, the desire to change something, get rid of something that causes discomfort or heartache. I am ready to walk this path with you.',
        },
    ]

    const itemsPerPage = 12;
    const paginatedUsers = users.slice(
        (activePage - 1) * itemsPerPage,
        activePage * itemsPerPage
    );


    return(
        <div className={classes.inner}>
            <div className={classes.content}>
                <Title className={classes.title}>Our therapists</Title>
                <SimpleGrid
                    cols={3}
                    spacing="lg"
                    breakpoints={[
                        {maxWidth: 'sm', cols: '2', spacing: 'sm'},
                        {maxWidth: 'xs', cols: '1', spacing: 'sm'}
                    ]}
                >
                    <InformationCard
                        title="There's something for everyone"
                        text="Here you can make an appointment for a consultation with a psychologist who works specifically with your problem: stress, anxiety, difficulties in relationships, low self-esteem, phobias, depression. The topics are listed in the specialist's questionnaire."
                        Icon={IconSearch}
                    />
                    <InformationCard
                        title="The appointment is online"
                        text="Reception is held online - this means that you can get quality help from a psychologist not only in Prague, but also from anywhere in the world. The price of the service is 45 euros for a personal consultation."
                        Icon={IconVideo}
                    />
                    <InformationCard
                        title="A large number of psychologists"
                        text="A large number of psychologists, all with diverse specialties and extensive experience, are here to provide you with the personalized support you need on our online psychotherapy platform. Whether you're seeking help for anxiety, depression, or any other mental health concern, our team is dedicated to helping you thrive."
                        Icon={IconUsers}
                    />
                    {paginatedUsers.map((user, index) => (
                        <TherapistCard key={index} userData={user} />
                    ))}
                </SimpleGrid>

                <Center>
                    <Pagination
                        total={Math.ceil(users.length / itemsPerPage)}
                        page={activePage}
                        onChange={setActivePage}
                        className={classes.pagination}
                        color="blue"
                        size="md"
                        radius="md"
                        withEdges
                    />
                </Center>
            </div>
        </div>
    )
}
