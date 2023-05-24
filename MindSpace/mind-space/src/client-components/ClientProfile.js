import {createStyles, Box, Text, Group, rem, Container} from '@mantine/core';
import { IconListSearch } from '@tabler/icons-react';
import { useState} from 'react';
import {ClientData} from "./ClientData";
import {ClientSession} from "./ClientSession";
import {ChangeTherapist} from "./ChangeTherapist";

const useStyles = createStyles((theme) => ({
    link: {
        ...theme.fn.focusStyles(),
        display: 'block',
        textDecoration: 'none',
        color: theme.colorScheme === 'dark' ? theme.colors.dark[0] : theme.black,
        lineHeight: 1.2,
        fontSize: theme.fontSizes.sm,
        padding: theme.spacing.xs,
        borderTopRightRadius: theme.radius.sm,
        borderBottomRightRadius: theme.radius.sm,
        borderLeft: `${rem(1)} solid ${
            theme.colorScheme === 'dark' ? theme.colors.dark[4] : theme.colors.gray[3]
        }`,

        '&:hover': {
            backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[6] : theme.colors.gray[0],
        },
    },

    linkActive: {
        fontWeight: 500,
        borderLeftColor: theme.colors[theme.primaryColor][theme.colorScheme === 'dark' ? 6 : 7],
        color: theme.colors[theme.primaryColor][theme.colorScheme === 'dark' ? 2 : 7],

        '&, &:hover': {
            backgroundColor:
                theme.colorScheme === 'dark'
                    ? theme.fn.rgba(theme.colors[theme.primaryColor][9], 0.25)
                    : theme.colors[theme.primaryColor][0],
        },
    },

    content: {
        display: 'flex',
        alignItems: 'flex-start',
        width: '100%',
        position: 'relative',
        [theme.fn.smallerThan('sm')]: {
            flexDirection: 'column',
            alignItems: 'flex-start',
        }
    },

    navbar: {
        flex: '1',
        width: '15%',
        marginLeft: rem(80),
        top: 0,
        [theme.fn.largerThan('xl')]: {
            marginLeft: rem(150),
        },
        [theme.fn.smallerThan('lg')]: {
            width: '20%',
        },
        [theme.fn.smallerThan('sm')]: {
            width: '100%',
            marginLeft: rem(20),
            marginBottom: theme.spacing.xs,
        },
    },

    component: {
        flex: '1',
        marginLeft: rem(80),
        maxWidth: '100%',
        [theme.fn.largerThan('xl')]: {
            width: '190%',
        },
        [theme.fn.smallerThan('lg')]: {
            marginLeft: rem(20),
            width: '80%'
        }
    }
}));


const contentProps = [
    {
        label: "Personal data",
        order: 1,
        component: <ClientData {...ClientData.id = 3} />,
    },
    {
        label: "Session",
        order: 1,
        component: <ClientSession />,
    },
    {
        label: "Therapist",
        order: 1,
        component: <ChangeTherapist />,
    },
]

export function ClientNavbar() {
    const { classes, cx } = useStyles();
    const [activeIndex, setActiveIndex] = useState(0);

    const renderActiveComponent = () => {
        const activeItem = contentProps[activeIndex];
        return activeItem ? (
            <Box className={classes.component}>
                {activeItem.component}
            </Box>
        ) : null;
    };

    const renderLabels = () => {
        return contentProps.map((item, index) => (
            <Box
                as="a"
                onClick={(event) => {
                    event.preventDefault();
                    setActiveIndex(index);
                }}
                key={item.label}
                className={cx(classes.link, { [classes.linkActive]: activeIndex === index })}
                sx={(theme) => ({ paddingLeft: `calc(${item.order} * ${theme.spacing.md})` })}
            >
                {item.label}
            </Box>
        ));
    };

    return (
        <div className={cx(classes.content)}>
            <div className={cx(classes.navbar)}>
                <Group mb="md">
                    <IconListSearch size="1.1rem" stroke={1.5} />
                    <Text>Profile</Text>
                </Group>
                {renderLabels()}
            </div>
            <div>
                {renderActiveComponent()}
            </div>

        </div>
    );
}