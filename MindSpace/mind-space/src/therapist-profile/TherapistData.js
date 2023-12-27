import {Avatar, createStyles, Textarea, TextInput, Text, Button, Spoiler, Group, rem} from '@mantine/core';

const useStyles = createStyles((theme) => ({
    inner: {
        maxWidth: rem(780),
    },

    group: {
      maxWidth: rem(780),
    },

    avatar: {
        marginRight: '3%',
    },

    floatText: {
        width: '82%',
        [theme.fn.smallerThan('md')]: {
            width: '100%'
        },
    }
}));

export function TherapistData() {
    const {classes} = useStyles();

    return(
        <div className={classes.inner}>
            <div>
                <div>
                    <Group className={classes.group}>
                        <Avatar radius="xl" size="xl" className={classes.avatar} />
                        <div className={classes.floatText}>
                            <TextInput
                                label="Your name:"
                                value={"Olivia Anderson"}
                            />
                            <TextInput
                                label="Your age:"
                                value={"31"}
                            />
                        </div>
                    </Group>
                    <TextInput
                        label="Your phone number:"
                        value={"+420777123456"}
                    />
                    <TextInput
                        label="Your email:"
                        value={"oliv_a@gmail.com"}
                    />
                    <TextInput
                        label="Languages:"
                        value={"English, Czech"}
                    />
                    <Textarea
                        label="Self-description"
                        value={"I can provide professional psychotherapeutic help in a wide range of requests: problems at work and in personal life, difficulties in self-determination, general dissatisfaction and apathy. Help in crises that occur because of events (divorce, death of loved ones, adultery), as well as those crises that spontaneously invade life, come as if from nowhere - the search for meaning, dissatisfaction, fears, the desire to change something, get rid of something that causes discomfort or heartache. I am ready to walk this path with you."}
                        minRows={3}
                    />
                    <Button>Save</Button>
                </div>
                <div>
                    <Text c="dimmed" fz="lg">Education</Text>
                    <Text>
                        <p>2017 - Southern Academy of Psychological Studies. Integrative Psychotherapy.</p>
                        <p>2015 - Eastern Institute of Psychoanalysis and Counseling. Child and Adolescent Psychology.</p>
                        <p>2013 - Central Institute for Psychotherapeutic Research. Cognitive Behavioral Therapy.</p>
                        <p>2011 - Global College of Existential Psychology. Existential Psychoanalysis.</p>
                    </Text>
                    <Text c="dimmed" fz="lg">Therapy type</Text>
                    <Text>Gestalt therapy, psychoanalysis</Text>
                    <Spoiler maxHeight={120} showLabel="Show more" hideLabel="Hide">
                        <Text c="dimmed" fz="lg" >Reviews</Text>
                        <div>
                            <Group>
                                <div>
                                    <Text size="sm">Jacob Warnhalter</Text>
                                    <Text size="xs" c="dimmed">
                                        10 minutes ago
                                    </Text>
                                </div>
                            </Group>
                            <Text  pt="sm" size="sm">
                                This Pokémon likes to lick its palms that are sweetened by being soaked in honey. Teddiursa
                                concocts its own honey by blending fruits and pollen collected by Beedrill. Blastoise has
                                water spouts that protrude from its shell. The water spouts are very accurate.
                            </Text>
                        </div>
                        <div>
                            <Group>
                                <div>
                                    <Text size="sm">Jacob Warnhalter</Text>
                                    <Text size="xs" c="dimmed">
                                        10 minutes ago
                                    </Text>
                                </div>
                            </Group>
                            <Text  pt="sm" size="sm">
                                This Pokémon likes to lick its palms that are sweetened by being soaked in honey. Teddiursa
                                concocts its own honey by blending fruits and pollen collected by Beedrill. Blastoise has
                                water spouts that protrude from its shell. The water spouts are very accurate.
                            </Text>
                        </div>
                        <div>
                            <Group>
                                <div>
                                    <Text size="sm">Jacob Warnhalter</Text>
                                    <Text size="xs" c="dimmed">
                                        10 minutes ago
                                    </Text>
                                </div>
                            </Group>
                            <Text  pt="sm" size="sm">
                                This Pokémon likes to lick its palms that are sweetened by being soaked in honey. Teddiursa
                                concocts its own honey by blending fruits and pollen collected by Beedrill. Blastoise has
                                water spouts that protrude from its shell. The water spouts are very accurate.
                            </Text>
                        </div>
                    </Spoiler>
                </div>
            </div>
        </div>
    )
}
