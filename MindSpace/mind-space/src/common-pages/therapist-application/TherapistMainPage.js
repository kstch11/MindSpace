import {Button, Container, createStyles, Divider, Group, Image, MantineProvider, rem, Text, Title, List} from "@mantine/core";
import image from "../../assets/therapist_p.jpeg";
import img1 from "../../assets/ther1.jpeg"
import img2 from "../../assets/ther2.jpeg"
import img3 from "../../assets/ther3.jpeg"

const useStyles = createStyles((theme) => ({
    inner: {
        display: 'flex',
        justifyContent: 'space-between',
        paddingTop: `calc(${theme.spacing.xl})`,
        paddingBottom: `calc(${theme.spacing.xl} * 5)`,
    },

    content: {
        maxWidth: rem(400),
        marginRight: `calc(${theme.spacing.xl} * 4)`,
        paddingTop: '10%',

        [theme.fn.smallerThan('sm')]: {
            maxWidth: '100%',
            marginRight: 0,
        },
    },

    title: {
        color: theme.colorScheme === 'dark' ? theme.white : theme.black,
        fontFamily: `Greycliff CF, ${theme.fontFamily}`,
        fontSize: rem(44),
        lineHeight: 1.2,
        fontWeight: 900,

        [theme.fn.smallerThan('xs')]: {
            fontSize: rem(28),
        },
    },

    control: {
        [theme.fn.smallerThan('xs')]: {
            flex: 1,
        },
    },

    image: {
        flex: 1,
        [theme.fn.smallerThan('sm')]: {
            display: 'none',
        },
    },

    item: {
        display: 'flex',
        flexDirection: 'row-reverse',
        alignItems: 'center',
        justifyContent: 'space-between',
        [theme.fn.smallerThan('sm')]:{
            maxWidth: '100%',
            flexDirection: 'column-reverse',
            marginBottom: rem(30),
        },
    },

    itemTwo: {
        display: 'flex',
        flexDirection: 'row-reverse',
        alignItems: 'center',
        justifyContent: 'space-between',
        [theme.fn.smallerThan('sm')]:{
            maxWidth: '100%',
            flexDirection: 'column',
            marginBottom: rem(30),
        },
    },

    text: {
        width: '130%',
        paddingRight: '20px',
        [theme.fn.smallerThan('sm')]:{
            maxWidth: '100%',
        },
    },

    imagesContainer: {
        flex: '0 0 auto',
    },

    images: {
        maxWidth: '100%',
        [theme.fn.smallerThan('sm')]:{
            maxWidth: '65%',
        },
    },

    textList: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
    },

    reqs: {
        maxWidth: '80%',
        margin: '2% 0 5% 0',
    },
}));

export function TherapistMainPage() {
    const { classes } = useStyles();
    return (
        <MantineProvider
            theme={{
                headings:{fontFamily:'BlinkMacSystemFont',
                },

            }}>
            <Container>
                <div className={classes.inner}>
                    <div className={classes.content}>
                        <Title className={classes.title}>
                            Empower Your Practice with MindSpace
                        </Title>
                        <Text color="dimmed" mt="md">
                            Join a Community of Dedicated Therapists
                        </Text>

                        <Group mt={30}>
                            <Button radius="md" size="md" className={classes.control}>
                                Start application
                            </Button>
                        </Group>
                    </div>
                    <Image src={image} className={classes.image} />
                </div>
            </Container>
            <Divider my="sm" className={classes.divider}></Divider>
            <Container>
                <div className={classes.list}>
                    <div className={classes.item}>
                        <div className={classes.text}>
                            <Text size="xl" fw={700}>Expanded client base</Text>
                            <Text mt="md">Access to a larger and more diverse client pool, often from different geographical areas.</Text>
                        </div>
                        <Image src={img3} className={classes.images} />
                    </div>
                    <div className={classes.itemTwo}>
                        <Image src={img2} className={classes.images} />
                        <div className={classes.text}>
                            <Text size="xl" fw={700}>Professional development</Text>
                            <Text mt="md">Opportunities for professional growth, learning, and networking with other therapists on the platform.</Text>
                        </div>
                    </div>
                    <div className={classes.item}>
                        <div className={classes.text}>
                            <Text size="xl" fw={700}>Flexible work schedule</Text>
                            <Text mt="md">The ability to set their own hours and provide therapy at times that suit their availability and preferences.</Text>
                        </div>
                        <Image src={img1} className={classes.images} />
                    </div>
                </div>
            </Container>
            <Divider my="sm" className={classes.divider}></Divider>
            <Container>
                <div className={classes.textList}>
                    <Title>Our requirements</Title>
                    <List size="lg" className={classes.reqs}>
                        <List.Item>Valid license to provide clinical therapy issued by Czech Association for Psychotherapy or European Certificate of Psychotherapy</List.Item>
                        <List.Item>3+ years of experience in therapy for adults, couples, and/or teens</List.Item>
                        <List.Item>Desktop or laptop computer with a reliable internet connection and a webcam</List.Item>
                        <List.Item>Currently residing in E.U.</List.Item>
                    </List>
                    <Button>Start application</Button>
                </div>
            </Container>
        </MantineProvider>
    )
}