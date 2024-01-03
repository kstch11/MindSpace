import {
    Avatar,
    createStyles,
    Textarea,
    TextInput,
    Text,
    Button,
    Spoiler,
    Group,
    rem,
    Center,
    Badge, Loader,
} from '@mantine/core';
import {useToggle} from "@mantine/hooks";
import {useEffect} from "react";
import React from "react";
import {useMutation} from "@tanstack/react-query";
import {postQuestionnaire, putTherapist} from "../../api/client-api";
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";

const useStyles = createStyles((theme) => ({
    inner: {
        maxWidth: rem(780),
        [theme.fn.smallerThan('sm')] : {
            margin: '0 2%',
        }
    },

    group: {
        maxWidth: rem(780),
        [theme.fn.smallerThan('sm')]: {
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
        }
    },

    avatar: {
        marginRight: '3%',

    },

    floatText: {
        width: '82%',
        [theme.fn.smallerThan('sm')]: {
            width: '100%',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            flexDirection: 'column',

        },
    },

    form: {
        marginBottom: theme.spacing.md,
    },

    formField: {
        marginBottom: theme.spacing.sm,
    },
}));

export function TherapistData({toggleValue, therapistData, onClickChooseTherapist}) {
    const accessToken = useSelector(state => state.currentUser.accessToken);
    const {classes} = useStyles();
    const [type, toggle] = useToggle(['all', 'client', 'clientRegistration', 'therapist', 'admin']);

    useEffect(() => {
        if (['all', 'client', 'clientRegistration', 'therapist', 'admin'].includes(toggleValue)) {
            toggle(toggleValue);
        }
    }, [toggleValue, toggle]);

    const formatArrayToString = (array) => {
        if (!Array.isArray(array)) {
            return '';
        }

        const formattedArray = array.map((item) => {
            const lowerCaseItem = item.toLowerCase();
            return lowerCaseItem.charAt(0).toUpperCase() + lowerCaseItem.slice(1);
        });

        return formattedArray.join(', ');
    }

    const formattedSpecs = formatArrayToString(['Depression', 'Anxiety', 'Stress', 'Interpersonal relationships'])

    return(
        <Center>
            <div className={classes.inner}>
                <div>
                    <Group className={classes.group}>
                        <Avatar radius="xl" size="xl" className={classes.avatar} />
                        {type === 'therapist' && (
                            <div className={classes.floatText}>
                            <TextInput
                                label="Your name:"
                                value={"Olivia Anderson"}
                            />
                            <TextInput
                                label="Your age:"
                                value={"31"}
                            />
                        </div>)}
                        {type === 'client' && (
                            <div className={classes.floatText}>
                                <Text fz="lg" weight={700} >Olivia Anderson</Text>
                                <Badge>5 years experience</Badge>
                                <Button>Change therapist</Button>
                            </div>
                        )}
                        {type === 'all' && (
                            <div className={classes.floatText}>
                                <Text fz="lg" mb="xs" weight={700}>Olivia Anderson</Text>
                                <div>
                                    <Badge mr="xs">5 years experience</Badge>
                                    <Badge mr="xs">50€/appointment</Badge>
                                </div>
                            </div>
                        )}
                        {type === 'clientRegistration' && (
                            <div className={classes.floatText}>
                                <Text fz="lg" mb="xs" weight={700}>Olivia Anderson</Text>
                                <div>
                                    <Badge mr="xs">5 years experience</Badge>
                                    <Badge mr="xs">50€/appointment</Badge>
                                </div>
                                <Button onClick={onClickChooseTherapist}>Choose a therapist</Button>
                            </div>
                        )}
                    </Group>
                    {type === 'therapist' && (
                        <div>
                            <TextInput
                                label="Your phone number:"
                                value={"+420777123456"}
                            />
                            <TextInput
                                label="Your email:"
                                value={"oliv_a@gmail.com"}
                            />
                            <TextInput
                                label="Languages:"
                                value={"English, Czech"}
                            />
                            <Textarea
                                label="Self-description"
                                value={"I can provide professional psychotherapeutic help in a wide range of requests: problems at work and in personal life, difficulties in self-determination, general dissatisfaction and apathy. Help in crises that occur because of events (divorce, death of loved ones, adultery), as well as those crises that spontaneously invade life, come as if from nowhere - the search for meaning, dissatisfaction, fears, the desire to change something, get rid of something that causes discomfort or heartache. I am ready to walk this path with you."}
                                minRows={3}
                            />
                            <Button>Save</Button>
                        </div>
                    )}
                </div>
                <div>
                    <Text c="dimmed" fz="lg" my="xs">Specialization</Text>
                    <Text my="xs">{formattedSpecs}</Text>
                    {type !== 'therapist' && (
                        <div>
                            <Text c="dimmed" fz="lg" my="xs">Self-description</Text>
                            <Text my="xs">I can provide professional psychotherapeutic help in a wide range of requests: problems at work and in personal life, difficulties in self-determination, general dissatisfaction and apathy. Help in crises that occur because of events (divorce, death of loved ones, adultery), as well as those crises that spontaneously invade life, come as if from nowhere - the search for meaning, dissatisfaction, fears, the desire to change something, get rid of something that causes discomfort or heartache. I am ready to walk this path with you.</Text>
                        </div>
                    )}
                    <Text c="dimmed" fz="lg" my="xs">Education</Text>
                    <Text my="xs">
                        <p>2017 - Southern Academy of Psychological Studies. Integrative Psychotherapy.</p>
                        <p>2015 - Eastern Institute of Psychoanalysis and Counseling. Child and Adolescent Psychology.</p>
                        <p>2013 - Central Institute for Psychotherapeutic Research. Cognitive Behavioral Therapy.</p>
                        <p>2011 - Global College of Existential Psychology. Existential Psychoanalysis.</p>
                    </Text>
                    <Text c="dimmed" fz="lg"my="xs">Therapy type</Text>
                    <Text>Gestalt therapy, psychoanalysis</Text>
                    <Text c="dimmed" fz="lg" my="xs">Reviews</Text>
                    {type === 'client' && (
                        <form  className={classes.form}>
                            <div className={classes.formField}>
                                <Text>{}</Text>
                            </div>
                            <div className={classes.formField}>
                                <Textarea
                                    required
                                    placeholder="Write your review"
                                    // value={reviewText}
                                    // onChange={(e) => setReviewText(e.target.value)}
                                />
                            </div>
                            <Button type="submit">Submit Review</Button>
                        </form>
                    )}
                    <Spoiler maxHeight={120} showLabel="Show more" hideLabel="Hide">
                        <Group mb="xs">
                            <Text size="md" fw={500}>Jacob Warnhalter</Text>
                            <Text size="sm">
                                This Pokémon likes to lick its palms that are sweetened by being soaked in honey. Teddiursa
                                concocts its own honey by blending fruits and pollen collected by Beedrill. Blastoise has
                                water spouts that protrude from its shell. The water spouts are very accurate.
                            </Text>
                        </Group>
                        <Group mb="xs">
                            <Text size="md"  fw={500}>Jacob Warnhalter</Text>
                            <Text size="sm">
                                This Pokémon likes to lick its palms that are sweetened by being soaked in honey. Teddiursa
                                concocts its own honey by blending fruits and pollen collected by Beedrill. Blastoise has
                                water spouts that protrude from its shell. The water spouts are very accurate.
                            </Text>
                        </Group>
                        <Group mb="xs">
                            <Text size="md"  fw={500}>Jacob Warnhalter</Text>
                            <Text size="sm">
                                This Pokémon likes to lick its palms that are sweetened by being soaked in honey. Teddiursa
                                concocts its own honey by blending fruits and pollen collected by Beedrill. Blastoise has
                                water spouts that protrude from its shell. The water spouts are very accurate.
                            </Text>
                        </Group>
                    </Spoiler>
                </div>
            </div>
        </Center>
    )
}
