import {
    createStyles,
    SimpleGrid,
    Pagination,
    rem,
    Title,
    Center,
    Transition,
    Loader,
    Modal,
    ScrollArea
} from "@mantine/core";
import {TherapistCard} from "./TherapistCard";
import React, {useEffect, useState} from "react";
import { IconSearch, IconVideo, IconUsers } from '@tabler/icons-react';
import {InformationCard} from "./InformationCard";
import {useToggle} from "@mantine/hooks";
import {useMutation} from "@tanstack/react-query";
import {postQuestionnaire, putTherapist} from "../../api/client-api";
import {useSelector} from "react-redux";
import {TherapistData} from "./TherapistData";
import {Navigate} from "react-router-dom";

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

    modal: {
        width: 800,
    },

    pagination: {
        marginTop: 40,
    }
}));

export function TherapistsList({toggleValue, questionnaire}) {
    const accessToken = useSelector(state => state.currentUser.accessToken);

    const {classes} = useStyles();
    const [activePage, setActivePage] = useState(1);
    const [type, toggle] = useToggle(['all', 'client', 'clientRegistration', 'therapist', 'admin'])
    const [therapists, setTherapists] = useState([])
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [openedTherapist, setOpenedTherapist] = useState(undefined);

    const handleTherapistClick = (therapist) => {
        setIsModalOpen(true);
        setOpenedTherapist(therapist)
    };

    const handleCloseTherapistInfo = () => {
        setIsModalOpen(false);
        setOpenedTherapist(undefined)
    }

    const {data, isPending, isSuccess, isError, mutate, error} = useMutation({
        mutationFn: (questionnaire) => {
            return postQuestionnaire(accessToken, questionnaire)
        },
    })

    const {
        isPending: updateTherapistPending,
        isSuccess: updateTherapistSuccess,
        isError: updateTherapistError,
        mutate: setTherapist,
        reset
    } = useMutation({
        mutationFn: (putTherapistBody) => {
            return putTherapist(accessToken, putTherapistBody)
        },
    })

    useEffect(() => {
        if (isSuccess) {
            const mappedData = data.map(fetched => ({
                id: fetched.id,
                avatarUrl: fetched.photo,
                name: fetched.name,
                experience: '5 years',
                description: fetched.description,
                specialization: fetched.topics && fetched.topics.map(topic => topic.name)
            }))
            setTherapists(mappedData)
        }

    }, [isSuccess])

    useEffect(() => {
        mutate()
    }, [])

    useEffect(() => {
        console.log(updateTherapistSuccess)
        console.log("her")
    }, [updateTherapistSuccess])

    useEffect(() => {
        if (['all', 'client', 'clientRegistration', 'therapist', 'admin'].includes(toggleValue)) {
            toggle(toggleValue);
        }
    }, [toggleValue, toggle]);

    const itemsPerPage = 12;
    const paginatedUsers = therapists.slice(
        (activePage - 1) * itemsPerPage,
        activePage * itemsPerPage
    );

    const totalPages = Math.ceil(therapists.length / itemsPerPage);

    if (isPending) {
        return <Loader/>
    }

    const onClickChooseTherapist = () => {
        setTherapist({ therapistId: openedTherapist.id});
        console.log(openedTherapist.id)
    }

    if (updateTherapistPending) {
        return <Loader/>
    }

    if (updateTherapistSuccess) {
        console.log("HERE");
        return <Navigate to={"/clientDoneRegistration"} />
    }

    return(
        <div className={classes.inner}>
            <div className={classes.content}>
                {type === 'all' && (
                    <div>
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
                        </SimpleGrid>
                    </div>
                )}
                <SimpleGrid
                    cols={3}
                    spacing="lg"
                    breakpoints={[
                        {maxWidth: 'sm', cols: '2', spacing: 'sm'},
                        {maxWidth: 'xs', cols: '1', spacing: 'sm'}
                    ]}
                >
                    {paginatedUsers.map((user, index) => (
                        <TherapistCard
                            key={index}
                            userData={user}
                            onMoreInfo={() => handleTherapistClick(user)}
                        />
                    ))}
                </SimpleGrid>


                    <Modal
                        opened={isModalOpen}
                        onClose={handleCloseTherapistInfo}
                        title="Therapist details"
                        size="70%"
                    >
                        <ScrollArea h={600}>
                            <TherapistData toggleValue={toggleValue} therapistData={openedTherapist} onClickChooseTherapist={onClickChooseTherapist}/>
                        </ScrollArea>
                    </Modal>

                <Center>
                    {totalPages > 1 && (
                        <Pagination
                            total={totalPages}
                            page={activePage}
                            onChange={setActivePage}
                            className={classes.pagination}
                            color="blue"
                            size="md"
                            radius="md"
                            withEdges
                        />
                    )}
                </Center>
            </div>
        </div>
    )
}
