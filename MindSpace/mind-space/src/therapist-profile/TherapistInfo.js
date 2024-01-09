import {useSelector} from "react-redux";
import {
    Avatar,
    Badge,
    Button,
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

}));

export default function TherapistInfo() {
    const accessToken = useSelector(state => state.currentUser.accessToken);
    const [therapistData, setTherapistData] = useState({
        name: '', phoneNumber: '', selfDescription: ''
    })
    const [therapistInfo, setTherapistInfo] = useState({ topics: '',
        education: '', languages: ''})
    const [avatar, setAvatar] = useState('')
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

    useEffect(() => {
        if (isFetched) {
            console.log(data)
            const formattedTopics = formatArrayToString(data.topics.map(t => t.name))
            const formattedLanguages = formatArrayToString(data.languages.map(l => l.name))
            console.log(formattedLanguages)
            console.log(formattedTopics)
            setTherapistData({
                name: data.name,
                phoneNumber: data.phone,
                selfDescription: data.description,
            })
            console.log(therapistData)
            setTherapistInfo({
                topics: formattedTopics,
                education: data.education,
                languages: formattedLanguages})
            console.log(therapistInfo)
            setAvatar(data.photo)
        }
    }, [data, isFetched])

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setTherapistData({...therapistData, [e.target.name]: e.target.value});
    };



    return (
        <Center>
            <div className={classes.inner}>
                <div>
                    <Group className={classes.group}>
                        <Avatar radius={50} size={100} className={classes.avatar} src={avatar} />
                        <div className={classes.floatText}>
                            <form>
                                <TextInput
                                    id="name"
                                    label="Your name:"
                                    name="name"
                                    value={therapistData.name}
                                    onChange={handleChange}
                                />
                                <TextInput
                                    label="Your phone number:"
                                    name="phoneNumber"
                                    id="phoneNumber"
                                    value={therapistData.phoneNumber}
                                    onChange={handleChange}
                                />
                                <Textarea
                                    label="Self-description"
                                    name="selfDescription"
                                    id="selfDescription"
                                    value={therapistData.selfDescription}
                                    minRows={6}
                                    onChange={handleChange}
                                />
                                <Button>Save</Button>
                            </form>
                        </div>
                    </Group>

                </div>
                <div>
                    <Text c="dimmed" fz="lg" my="xs">Specialization</Text>
                    <Text my="xs">
                        {therapistInfo.topics}
                    </Text>

                    {/*<div>*/}
                    {/*    <Text c="dimmed" fz="lg" my="xs">Self-description</Text>*/}
                    {/*    <Text my="xs">*/}
                    {/*        {therapistData.description}*/}
                    {/*    </Text>*/}
                    {/*</div>*/}

                    <Text c="dimmed" fz="lg" my="xs">Education</Text>
                    <Text my="xs">
                        {therapistInfo.education}
                    </Text>
                    <Text c="dimmed" fz="lg"my="xs">Spoken Languages</Text>
                    <Text>
                        {therapistInfo.languages}
                    </Text>
                    <Text c="dimmed" fz="lg" my="xs">Reviews</Text>
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