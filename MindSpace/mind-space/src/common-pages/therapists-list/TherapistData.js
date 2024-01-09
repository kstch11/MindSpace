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
import {useEffect, useState} from "react";
import React from "react";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchCurrentUser, postQuestionnaire, putTherapist} from "../../api/client-api";
import {useSelector} from "react-redux";
import {fetchTherapistProfile, getReviews, postReview} from "../../api/therapist-api";

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

    column: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'flex-start',
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
    const [therapistInfo, setTherapistInfo] = useState({
        name: '', surname: '', education: '', languages: [], photo: '', topics: [], description: ''
    });
    const [reviewText, setReviewText] = useState({author: null, text: ''});
    const [reviews, setReviews] = useState([])

    useEffect(() => {
        if (['all', 'client', 'clientRegistration', 'therapist', 'admin'].includes(toggleValue)) {
            toggle(toggleValue);
        }
    }, [toggleValue, toggle]);

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
            console.log(data)
        }
    }, [data, isFetched])

    const {
        data: therapist,
        isFetched: fetched,
        isPending: pending,
    } = useQuery({
        queryKey: ['therapistFetching'],
        queryFn: () => data && data.therapistId ? fetchTherapistProfile(data.therapistId, accessToken) : null,
        enabled: !!data && !!data.therapistId,
    })

    const {
        data: reviewss,
        isFetched: fetchedData
    } = useQuery({
        queryFn: () => data && data.therapistId ? getReviews( accessToken, data.therapistId) : null,
        enabled: !!data && !!data.therapistId,
    })


    useEffect(() => {
        if (fetched) {
            console.log(therapist)
            const therapistLanguages = therapist.languages.map(l => l.name)
            const therapistTopics = therapist.topics.map(t => t.name);
            setTherapistInfo({
                name: therapist.name,
                surname: therapist.surname,
                education: therapist.education,
                languages: therapistLanguages,
                photo: therapist.photo,
                topics: therapistTopics,
                description: therapist.description
            })
        }
    }, [therapist, fetched])

    useEffect(() => {
        if (fetchedData) {
            console.log(reviewss)
            const mappedReview = reviewss.map(r => ({
                reviewAuthor: data.name,
                text: r.text,
            }))
            setReviews(mappedReview)
        }
    }, [fetchedData])



    const {
        data: review,
        isPending: reviewPending,
        isSuccess,
        mutate,
    } = useMutation({
        mutationFn: (review) => {
            return postReview(accessToken, data.therapistId, review)
        },
    })

    const handleCreateReview = () => {
        setReviewText({
            author: data.id,
        })
        console.log(reviewText.text)
        console.log(data.id)
        console.log(reviewText)
        mutate({author: data.id, text: reviewText.text})
    }

    if (isSuccess) {
        console.log("review was created")
    }

    const handleSubmitReview = (e: React.ChangeEvent<HTMLInputElement>) => {
        e.preventDefault();
        setReviewText({...reviewText, [e.target.name]: e.target.value})
    }

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

    const formattedTopics = formatArrayToString(therapistInfo.topics);
    const formattedLanguages = formatArrayToString(therapistInfo.languages)


    return(
        <Center>
            <div className={classes.inner}>
                <div>
                    <Group className={classes.group}>
                        <Avatar radius="xl" size="xl" className={classes.avatar} src={therapistInfo.photo}/>
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
                                <Text fz="lg" weight={700} >{`${therapistInfo.name} ${therapistInfo.surname}`}</Text>
                                <div className={classes.column}>
                                    <Badge mb={7}>5 years experience</Badge>
                                    <Button>Change therapist</Button>
                                </div>
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
                                <Text fz="lg" mb="xs" weight={700}>{(therapistData !== undefined) ? `${therapistData.name} ${therapistData.surname}` : ''}</Text>
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
                    <Text my="xs">{formattedTopics}</Text>
                    {type !== 'therapist' && (
                        <div>
                            <Text c="dimmed" fz="lg" my="xs">Self-description</Text>
                            <Text my="xs">{therapistInfo.description}</Text>
                        </div>
                    )}
                    <Text c="dimmed" fz="lg" my="xs">Education</Text>
                    <Text my="xs">{therapistInfo.education}</Text>
                    <Text c="dimmed" fz="lg"my="xs">Spoken Languages</Text>
                    <Text>{formattedLanguages}</Text>
                    <Text c="dimmed" fz="lg" my="xs">Reviews</Text>
                    {type === 'client' && (
                        <form className={classes.form}>
                            <div className={classes.formField}>
                                <Text>{}</Text>
                            </div>
                            <div className={classes.formField}>
                                <Textarea
                                    required
                                    placeholder="Write your review"
                                    name="text"
                                    value={reviewText.text}
                                    onChange={handleSubmitReview}
                                />
                            </div>
                            <Button onClick={handleCreateReview} type="button">Submit Review</Button>
                        </form>
                    )}
                    {reviews === 0 && (
                        <Text>Therapist still does not have any reviews</Text>
                    )}
                    {reviews !== 0 && (
                        <Spoiler maxHeight={120} showLabel="Show more" hideLabel="Hide">
                            {reviews.map(( index) => (
                                <Group mb="xs">
                                    <Text size="md" fw={500}>{index.name}</Text>
                                    <Text size="sm">
                                        {index.text}
                                    </Text>
                                </Group>
                            ))}

                        </Spoiler>
                    )}
                </div>
            </div>
        </Center>
    )
}
