import {createStyles, Paper, Avatar, Text, Button, Divider, Badge, Title} from "@mantine/core";

const useStyles = createStyles((theme) =>({
    skillList: {
        display: '-webkit-box',
        WebkitBoxOrient: 'vertical',
        WebkitLineClamp: 2,
        overflow: 'hidden',
    },

    badge: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexDirection: 'column',
        margin:'0 15%'
    },

}));

export function TherapistCard({userData}) {
    const {classes} = useStyles()
    if (!userData) {
        return <div>Loading...</div>;
    }


    const renderSkills = (skills) => {
        if (!skills || skills.length === 0) return 'No skills listed';

        const visibleSkills = skills.slice(0, 5);
        const hiddenSkillCount = skills.length - visibleSkills.length;

        return (
            <>
                {visibleSkills.join(', ')}
                {hiddenSkillCount > 0 && ` and ${hiddenSkillCount} others`}
            </>
        );
    };

    const truncateText =(text, maxLength) => {
        if (text && text.length > maxLength) {
            return text.substring(0, maxLength) + '...';
        }
        return text;
    }


    return(
        <div>
            <Paper radius="md" shadow="sm" p="lg" bg="var(--mantine-color-body)">
                <Avatar
                    src={userData.avatarUrl}
                    size={120}
                    radius={120}
                    mx="auto"
                />
                <Text ta="center" fz="lg" fw={500} mt="md">
                    {userData.name}
                </Text>
                <Badge color="blue" radius="sm" className={classes.badge}>{userData.experience} experience</Badge>
                <Divider my="sm" />
                <Text fz="xs" c="dimmed">Specialization</Text>
                <Text  fz="sm" className={classes.skillList}>
                    {renderSkills(userData.specialization)}
                </Text>
                <Text c="dimmed" fz="xs">Description</Text>
                <Text  fz="sm">
                    {truncateText(userData.description, 297)}
                </Text>
                <Divider my="sm" />
                <Text  fz="lg" fw={700}>
                    50€/appointment
                </Text>

                <Button fullWidth mt="md">
                    More about therapist
                </Button>
            </Paper>
        </div>
    );
}
