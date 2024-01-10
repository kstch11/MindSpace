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
    Badge, Card,
} from '@mantine/core';
import {useToggle} from "@mantine/hooks";
import {useEffect, useState} from "react";
import React from "react";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchCurrentUser} from "../../api/client-api";
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
        name: '', surname: '', education: '', languages: [], photo: '', topics: [], description: '', experience: null
    });
    const [reviewText, setReviewText] = useState({author: null, text: ''});
    const [reviews, setReviews] = useState([])
    let topicsToString = ''
    let languagesToString = ''

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
        queryFn: () => data && data.therapistId ? getReviews(accessToken, data.therapistId) : null,
        enabled: !!data && !!data.therapistId,
    })

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


    useEffect(() => {
        if (fetched) {
            const therapistLanguages = therapist.languages.map(l => l.name)
            const therapistTopics = therapist.topics.map(t => t.name);
            setTherapistInfo({
                name: therapist.name,
                surname: therapist.surname,
                education: therapist.education,
                languages: therapistLanguages,
                photo: therapist.photo,
                topics: therapistTopics,
                description: therapist.description,
                experience: therapist.experience
            })
            console.log(therapistInfo)
        }
    }, [therapist, fetched])



    useEffect(() => {
        if (fetchedData) {
            console.log(reviewss)
            const authorFullname = `${data.name} ${data.surname}`
            const mappedReview = reviewss.map(r => ({
                reviewAuthor: authorFullname,
                text: r.text,
            }))
            console.log(mappedReview)
            setReviews(mappedReview)
        }
    }, [reviewss, fetchedData])


    const handleCreateReview = () => {
        setReviewText({
            author: data.id,
        })
        mutate({author: data.id, text: reviewText.text})
        setReviewText({
            author: null,
            text: ''
        })
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
    const formattedLanguages = formatArrayToString(therapistInfo.languages);

    if (therapistData !== undefined) {
        topicsToString = formatArrayToString(therapistData.topics);
        languagesToString = formatArrayToString(therapistData.languages)
    }


    return(
        <Center>
            <div className={classes.inner}>
                <div>
                    <Group className={classes.group}>
                        <Avatar radius="xl" size="xl" className={classes.avatar} src={therapistInfo.photo}/>
                        {type === 'client' && (
                            <div className={classes.floatText}>
                                <Text fz="lg" weight={700} >{`${therapistInfo.name} ${therapistInfo.surname}`}</Text>
                                <div className={classes.column}>
                                    <Badge mb={7}>{`${therapistInfo.experience}`} years experience</Badge>
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
                                    <Badge mr="xs">{(therapistData !== undefined) ? `${therapistData.experience}` : '5'} years experience</Badge>
                                    <Badge mr="xs">50€/appointment</Badge>
                                </div>
                                <Button onClick={onClickChooseTherapist}>Choose a therapist</Button>
                            </div>
                        )}
                    </Group>
                </div>
                <div>
                    <Text c="dimmed" fz="lg" my="xs">Specialization</Text>
                    <Text my="xs">{(therapistData !== undefined) ? `${topicsToString}` : `${formattedTopics}`}</Text>
                    {type !== 'therapist' && (
                        <div>
                            <Text c="dimmed" fz="lg" my="xs">Self-description</Text>
                            <Text my="xs">{(therapistData !== undefined) ? `${therapistData.description}` : `${therapistInfo.description}`}</Text>
                        </div>
                    )}
                    <Text c="dimmed" fz="lg" my="xs">Education</Text>
                    <Text my="xs">{(therapistData !== undefined) ? `${therapistData.education}` : `${therapistInfo.education}`}</Text>
                    <Text c="dimmed" fz="lg"my="xs">Spoken Languages</Text>
                    <Text>{(therapistData !== undefined) ? `${languagesToString}` : `${formattedLanguages}`}</Text>
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
                            {reviewText.text !== '' ?
                                <Button onClick={handleCreateReview} type="button">Submit Review</Button> :
                                <Button data-disabled
                                        sx={{ '&[data-disabled]': { pointerEvents: 'all' } }}
                                        onClick={(event) => event.preventDefault()}>
                                    Submit Review
                                </Button>}
                        </form>
                    )}
                    {reviews.length === 0 && (
                        <Text>Therapist still does not have any reviews</Text>
                    )}
                    {reviews.length !== 0 && (
                        <Spoiler maxHeight={150} showLabel="Show more" hideLabel="Hide">
                            {reviews.map(( index) => (
                                <Card withBorder radius="md" shadow="sm" mb={10}>
                                    <Text size="md" fw={500}>{index.reviewAuthor}</Text>
                                    <Text size="sm" w="100%" mt={5}>
                                        {index.text}
                                    </Text>
                                </Card>
                            ))}
                        </Spoiler>
                    )}
                </div>
            </div>
        </Center>
    )
}
