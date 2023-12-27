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

// async function fetchUserData(userId) {
//     try {
//         const response = await fetch(`https://your-backend-api.com/users/${userId}`);
//         if (!response.ok) {
//             throw new Error('Network response was not ok');
//         }
//         return await response.json();
//     } catch (error) {
//         console.error('There was a problem fetching user data:', error);
//         return null;
//     }
// }

export function TherapistCard({userData}) {
    const {classes} = useStyles()
    // const [userData, setUserData] = useState(null);
    //
    // useEffect(() => {
    //     fetchUserData(userId)
    //         .then(data => setUserData(data))
    //         .catch(error => console.error(error));
    // }, [userId]);
    //
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
        if (text.length > maxLength) {
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

                <Button fullWidth mt="md">
                    More about therapist
                </Button>
            </Paper>
        </div>
    );
}