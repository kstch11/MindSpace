import {Box, Center, Group, Text, createStyles, rem, Tabs} from "@mantine/core";
import {IconListSearch} from "@tabler/icons-react";
import {useEffect, useState} from 'react';

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
        flexDirection: 'row',
        justifyContent: 'space-between',
        [theme.fn.smallerThan('sm')]: {
            width: '90%',
            flexDirection: 'column',
            marginBottom: rem(30),
        }
    },

    navbar: {
        width: '25%',
        marginRight: rem(80),
        top: 0,

        [theme.fn.smallerThan('sm')]: {
            width: '100%',
            marginLeft: rem(20),
            marginRight: rem(20),
            marginBottom: theme.spacing.md,
        },
        [theme.fn.smallerThan('sm')]: {
            display: 'none',
        },
    },

    tabs: {
        top: 0,
        marginBottom: rem(30),
        [theme.fn.largerThan('sm')]: {
            display: 'none',
        },
    },

    profile: {
        [theme.fn.smallerThan('sm')]: {
            display: 'none',
        },
    }
}));

export function Navbar({contentProps}) {
    const { classes, cx } = useStyles();
    const [activeIndex, setActiveIndex] = useState(
        parseInt(localStorage.getItem('activeNavbarIndex'), 10) || 0
    );

    useEffect(() => {
        localStorage.setItem('activeNavbarIndex', activeIndex)
    }, [activeIndex]);

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
        <Center>
            <div className={cx(classes.content)}>
                <div className={cx(classes.navbar)}>
                    <Group mb="md">
                        <IconListSearch size="1.1rem" stroke={1.5} />
                        <Text>Profile</Text>
                    </Group>
                    {renderLabels()}
                </div>

                <div className={classes.tabs}>
                    <Tabs variant="outline" radius="md" defaultValue="personalData">
                        <Tabs.List>
                            {contentProps.map((tab, index) => (
                                <Tabs.Tab
                                    key={tab.value}
                                    value={tab.value}
                                    icon={tab.icon}
                                    onClick={(event) => {
                                        event.preventDefault();
                                        setActiveIndex(index);
                                    }}
                                >
                                    {tab.label}
                                </Tabs.Tab>
                            ))}
                        </Tabs.List>

                        {contentProps.map((tab) => (
                            <Tabs.Panel
                                key={tab.value}
                                value={tab.value}
                                pt="xs"
                            >
                                {renderActiveComponent()}
                            </Tabs.Panel>
                        ))}
                    </Tabs>

                </div>

                <div className={classes.profile}>
                    {renderActiveComponent()}
                </div>
            </div>
        </Center>
    );
}
