import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchAllTherapists} from "../api/therapist-api";
import {
    Button,
    Center,
    createStyles,
    Loader,
    MantineProvider,
    rem,
    ScrollArea,
    Table,
    Text,
    Title
} from "@mantine/core";
import React, {useState} from "react";
import {useSelector} from "react-redux";
import {setRegistrationComplete} from "../api/client-api";
import {approveTherapist} from "../api/admin-api";

const useStyles = createStyles((theme) => ({
    rowSelected: {
        backgroundColor:
            theme.colorScheme === 'dark'
                ? theme.fn.rgba(theme.colors[theme.primaryColor][7], 0.2)
                : theme.colors[theme.primaryColor][0],
    },

    clients: {
        margin: '0 auto',
        maxWidth: rem(1200),
        [theme.fn.smallerThan('md')]: {
            maxWidth: '100%',
        },
    },

    title: {
        marginBottom: rem(15),
    },

    buttonContainer: {
        marginTop: rem(25),
        textAlign: 'right',

    }
}));

export default function ListOfTherapists() {
    const accessToken = useSelector(state => state.currentUser.accessToken);
    const { classes, theme, cx } = useStyles();
    const [selectedId, setSelectedId] = useState(-1);
    const {data, isLoading} = useQuery({
        queryKey: ['allTherapisst'],
        queryFn: () => fetchAllTherapists(accessToken),
    });

    const {
        isPending: approvePending,
        isSuccess: approveSuccess,
        isError: approveError,
        mutate: approveTherapistMutate,
    } = useMutation({
        mutationFn: (id) => {
            return approveTherapist(accessToken, id)
        },
    })

    if (isLoading) {
        return <Loader/>
    }

    if (approveSuccess) {
        window.location.reload()
    }

    const rows = data.map((item, index) => {
        const selected = index === selectedId;
        console.log(item)
        return(
            <tr key={index} className={cx({ [classes.rowSelected]: selected })}>
                <td>{item.id}</td>
                <td>{item.name} {item.surname}</td>
                <td>{item.surname}</td>
                <td>{item.phone}</td>
                <td>{item.experience}</td>
                <td>{item.approved === true
                    ? <Button disabled={true}>Approved</Button>
                    : <Button onClick={() => approveTherapistMutate(item.id)}>Approve</Button>
                }</td>
            </tr>
        )
    })


    return (
        <MantineProvider theme={theme}>
            <ScrollArea className={classes.clients}>
                <Title order={2} className={classes.title}>Therapists</Title>
                {data.length === 0 ? (
                    <Text size="lg">There are no clients at the moment.</Text>
                ) : (
                    <div>
                        <Table miw={800} verticalSpacing="sm">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Phone number</th>
                                <th>Email</th>
                                <th>Experience</th>
                                <th>Approve</th>
                            </tr>
                            </thead>
                            <tbody>{rows}</tbody>
                        </Table>
                    </div>
                )}
            </ScrollArea>
        </MantineProvider>
    )
}
