import { useState } from 'react';
import React from "react";
import {
    createStyles,
    Table,
    Button,
    ScrollArea,
    Group,
    Text,
    rem,
    Collapse,
    MantineProvider,
    Title
} from '@mantine/core';

const useStyles = createStyles((theme) => ({
    table: {
        margin: '0 auto',
        maxWidth: rem(1200),
        [theme.fn.smallerThan('md')]: {
            maxWidth: '100%',
        },
    },

    start: {
        marginRight: rem(10),
        [theme.fn.smallerThan('md')]: {
            marginRight: rem(5),
        },
    },

    delay: {
        marginRight: rem(10),
        [theme.fn.smallerThan('md')]: {
            marginRight: rem(5),
        },
    },

    title: {
        marginBottom: rem(15),
    }

}));

interface TableSelectionProps {
    data: { dateTime: string; client: string; email: string; reservationId: string }[];
}

export function ReservationTable({ data }: TableSelectionProps) {
    const { classes, theme } = useStyles();

    const [expandedRows, setExpandedRows] = useState([]);

    const rows = data.map((item, index) => {
        const isExpanded = expandedRows.includes(item.reservationId);

        return (
            <React.Fragment key={`${item.reservationId}-${index}`}>
                <tr>
                    <td>{item.dateTime}</td>
                    <td>
                        <Group spacing="sm">
                            <Text size="sm" weight={500}>
                                {item.client}
                            </Text>
                        </Group>
                    </td>
                    <td>{item.email}</td>
                    <td>
                        <Group spacing="sm">
                            <Button className={classes.start}>Start session</Button>
                            <Button className={classes.delay}>Delay session</Button>
                            <Button>Cancel session</Button>
                        </Group>
                    </td>
                </tr>
                <tr>
                    <td colSpan={4}>
                        <Collapse in={isExpanded}>
                            <td>
                                <div>Additional details or actions for the reservation</div>
                            </td>
                        </Collapse>
                    </td>
                </tr>
            </React.Fragment>
        );
    });

    return (
        <MantineProvider theme={theme}>
            <ScrollArea className={classes.table}>
                <Title order={2} className={classes.title}>Reservations</Title>
                {data.length === 0 ? (
                    <Text size="lg">There are no reservations at the moment.</Text>
                ) : (
                    <Table
                        miw={theme.fn.smallerThan('md') ? '100%' : '100'}
                        verticalSpacing="sm"
                    >
                        <colgroup>
                            <col style={{ width: '20%' }} />
                            <col style={{ width: '20%' }} />
                            <col style={{ width: '20%' }} />
                            <col style={{ width: '40%' }} />
                        </colgroup>
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Client</th>
                            <th>Email</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>{rows}</tbody>
                    </Table>
                )}
            </ScrollArea>
        </MantineProvider>
    );
}
