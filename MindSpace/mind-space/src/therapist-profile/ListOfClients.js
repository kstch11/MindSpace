import { useState } from 'react';
import React from "react";
import {
    createStyles,
    Table,
    Radio,
    ScrollArea,
    Group,
    Text,
    rem,
    MantineProvider,
    Title,
    Button
} from '@mantine/core';


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

interface TableSelectionProps {
    data: { name: string; surname: string; phoneNumber: string; email: string; id: string }[];
}

const data = [
    {name: "qwerty", surname: "qwerty", phoneNumber: "123456789", email: "fffff@fff.ff", id: 1},
    {name: "qwerty", surname: "qwerty", phoneNumber: "123456789", email: "fffff@fff.ff", id: 2},
    {name: "qwerty", surname: "qwerty", phoneNumber: "123456789", email: "fffff@fff.ff", id: 3},
    {name: "qwerty", surname: "qwerty", phoneNumber: "123456789", email: "fffff@fff.ff", id: 4},
    {name: "qwerty", surname: "qwerty", phoneNumber: "123456789", email: "fffff@fff.ff", id: 5},
    {name: "qwerty", surname: "qwerty", phoneNumber: "123456789", email: "fffff@fff.ff", id: 6},
    {name: "qwerty", surname: "qwerty", phoneNumber: "123456789", email: "fffff@fff.ff", id: 7},
    {name: "qwerty", surname: "qwerty", phoneNumber: "123456789", email: "fffff@fff.ff", id: 8},
]

export function ClientsTable() {
    const { classes, theme, cx } = useStyles();
    const [selectedId, setSelectedId] = useState('');

    const handleRowSelection = (id: string) => {
        setSelectedId(id);
    };

    const rows = data.map((item) => {
        const selected = item.id === selectedId;
        return (
            <tr key={item.id} className={cx({ [classes.rowSelected]: selected })}>
                <td>
                    <Radio
                        checked={selected}
                        onChange={() => handleRowSelection(item.id)}
                        transitionDuration={0}
                    />
                </td>
                <td>
                    <Group spacing="sm">
                        <Text size="sm" weight={500}>
                            {item.name} {item.surname}
                        </Text>
                    </Group>
                </td>
                <td>{item.phoneNumber}</td>
                <td>{item.email}</td>
            </tr>
        );
    });

    return (
        <MantineProvider theme={theme}>
            <ScrollArea className={classes.clients}>
                <Title order={2} className={classes.title}>Clients</Title>
                {data.length === 0 ? (
                    <Text size="lg">There are no clients at the moment.</Text>
                ) : (
                    <div>
                        <Table miw={800} verticalSpacing="sm">
                            <thead>
                            <tr>
                                <th style={{ width: rem(40) }}></th>
                                <th>Client</th>
                                <th>Phone number</th>
                                <th>Email</th>
                            </tr>
                            </thead>
                            <tbody>{rows}</tbody>
                        </Table>
                        <div className={classes.buttonContainer}>
                            <Button>Show reservations</Button>
                        </div>
                    </div>
                )}
            </ScrollArea>
        </MantineProvider>
    );
}
