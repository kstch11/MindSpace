import {useSelector} from "react-redux";
import {
    Avatar,
    Badge,
    Button, Card,
    Center,
    createStyles,
    Group,
    rem,
    Spoiler,
    Text,
    Textarea,
    TextInput
} from "@mantine/core";
import React, {useEffect, useState} from "react";
import {useQuery} from "@tanstack/react-query";
import {fetchCurrentUser} from "../api/client-api";
import {getReviews} from "../api/therapist-api";

const useStyles = createStyles((theme) => ({
    inner: {
        width: rem(780),
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

}));

export default function TherapistInfo() {
    const accessToken = useSelector(state => state.currentUser.accessToken);
    const [therapistInfo, setTherapistInfo] = useState({ name: '', phoneNumber: '', selfDescription: '',
        topics: '', education: '', languages: '', avatar: '', experience: ''});
    const [reviews, setReviews] = useState([])
    const {classes} = useStyles();

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

    const {
        isPending,
        isError,
        data,
        isFetched,
        error
    } = useQuery({
            queryKey: ['therapistProfile'], queryFn: () => fetchCurrentUser(accessToken)
        }
    )

    const {
        data: reviewss,
        isFetched: fetchedData
    } = useQuery({
        queryFn: () => data && data.id ? getReviews(accessToken, data.id) : null,
        enabled: !!data && !!data.id,
    })

    useEffect(() => {
        if (isFetched) {
            console.log(data)
            const formattedTopics = formatArrayToString(data.topics.map(t => t.name))
            const formattedLanguages = formatArrayToString(data.languages.map(l => l.name))
            setTherapistInfo({
                name: data.name,
                phoneNumber: data.phone,
                selfDescription: data.description,
                topics: formattedTopics,
                education: data.education,
                languages: formattedLanguages,
                avatar: data.photo,
                experience: data.experience
            })
            console.log(therapistInfo)
        }
    }, [data, isFetched])


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

    return (
        <Center>
            <div className={classes.inner}>
                <div>
                    <Group className={classes.group}>
                        <Avatar radius={50} size={100} className={classes.avatar} src={therapistInfo.avatar} />
                        <div className={classes.floatText}>
                            <Text weight={500}>{therapistInfo.name}</Text>
                            <Text weight={500}>{therapistInfo.phoneNumber}</Text>
                            <Badge mr="xs">{therapistInfo.experience} years experience</Badge>
                                {/*<TextInput*/}
                                {/*    id="name"*/}
                                {/*    label="Your name:"*/}
                                {/*    name="name"*/}
                                {/*    value={therapistData.name}*/}
                                {/*    onChange={handleChange}*/}
                                {/*/>*/}
                                {/*<TextInput*/}
                                {/*    label="Your phone number:"*/}
                                {/*    name="phoneNumber"*/}
                                {/*    id="phoneNumber"*/}
                                {/*    value={therapistData.phoneNumber}*/}
                                {/*    onChange={handleChange}*/}
                                {/*/>*/}
                        </div>
                    </Group>

                </div>
                <div>
                    <Text c="dimmed" fz="lg" my="xs">Specialization</Text>
                    <Text my="xs">
                        {therapistInfo.topics}
                    </Text>

                    <div>
                        <Text c="dimmed" fz="lg" my="xs">Self-description</Text>
                        <Text my="xs">
                            {therapistInfo.selfDescription}
                        </Text>
                    </div>

                    <Text c="dimmed" fz="lg" my="xs">Education</Text>
                    <Text my="xs">
                        {therapistInfo.education}
                    </Text>
                    <Text c="dimmed" fz="lg"my="xs">Spoken Languages</Text>
                    <Text>
                        {therapistInfo.languages}
                    </Text>
                    <Text c="dimmed" fz="lg" my="xs">Reviews</Text>
                    {reviews.length === 0 && (
                        <Text>You still do not have any reviews</Text>
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