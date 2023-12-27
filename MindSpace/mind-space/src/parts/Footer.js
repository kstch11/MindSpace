import {createStyles, Container, Group, Anchor, rem, Title, Image} from '@mantine/core';
import logo from "../assets/mental-health.svg"

const useStyles = createStyles((theme) => ({
    footer: {
        marginTop: rem(20),
        borderTop: `${rem(1)} solid ${
            theme.colorScheme === 'dark' ? theme.colors.dark[5] : theme.colors.gray[2]
        }`,

    },

    inner: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        paddingTop: theme.spacing.xl,
        paddingBottom: theme.spacing.xl,

        [theme.fn.smallerThan('xs')]: {
            flexDirection: 'column',
        },
    },

    links: {
        [theme.fn.smallerThan('xs')]: {
            marginTop: theme.spacing.md,
        },
    },

    img: {
        maxWidth: rem(28),
        maxHeight: rem(28),
        paddingRight: rem(10),
    },

    container: {
        display: 'flex',
        alignItems: 'center',
    }

}));

interface FooterSimpleProps {
    links: { link: string; label: string }[];
}

export function Footer({ links }: FooterSimpleProps) {
    const { classes } = useStyles();
    const items = links.map((link) => (
        <Anchor
    color="dimmed"
    key={link.label}
    href={link.link}
    // onClick={(event) => event.preventDefault()}
    size="sm"
        >
        {link.label}
</Anchor>
));

    return (
        <div className={classes.footer}>
            <Container className={classes.inner}>
                <div className={classes.container}>
                    <Image src={logo} alt={"logo"} className={classes.img} />
                    <Title order={3} className={classes.title}>MindSpace</Title>
                </div>
                <Group className={classes.links}>{items}</Group>
            </Container>
        </div>
    );
}



