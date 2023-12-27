import {createStyles, Paper, Text} from "@mantine/core";

const useStyles = createStyles((theme) => ({
    paper: {
        padding: theme.spacing.md,
        borderRadius: theme.radius.md,
        maxWidth: 300,
        marginBottom: theme.spacing.lg,
        backgroundColor: theme.colors.gray[0],
        [theme.fn.smallerThan('md')]:{
            maxWidth: '100%',
            margin: '0 3%',
        },
    },

    icon: {
        marginBottom: theme.spacing.md,
    },
}))

export function InformationCard({title, text, Icon}) {
    const {classes} = useStyles();

    return(
        <div>
            <Paper
                shadow="sm"
                className={classes.paper}
            >
                {Icon && <Icon size={50} className={classes.icon} />}
                <Text weight={500} size="lg">{title}</Text>
                <Text size="sm" >{text}</Text>
            </Paper>
        </div>
    )
}
