import React, { useState } from 'react';
import {
    createStyles,
    Avatar,
    Container,
    Paper,
    Title,
    Text,
    Button,
    TextInput,
    Textarea,
    useMantineTheme, Divider,
} from '@mantine/core';

const useStyles = createStyles((theme) => ({
    container: {
        width: 720,
        marginRight: '15%',
        [theme.fn.smallerThan('md')]: {
            width: 600,
        },
        [theme.fn.smallerThan('sm')]: {
            width: '100%',
            marginBottom: 0,
        },
    },
    profileContainer: {
        display: 'flex',
        alignItems: 'center',
        marginBottom: theme.spacing.md,
    },
    avatar: {
        marginRight: theme.spacing.md,
    },
    infoContainer: {
        display: 'flex',
        flexDirection: 'column',
        marginBottom: theme.spacing.md,
    },
    infoText: {
        marginBottom: theme.spacing.sm,
    },

    button: {
      alignContent: 'right',
        marginBottom: theme.spacing.md,
    },

    sectionTitle: {
        marginBottom: theme.spacing.md,
    },
    reviewContainer: {
        marginBottom: theme.spacing.md,
    },
    reviewAuthor: {
        fontWeight: 600,
        marginBottom: theme.spacing.xs,
    },
    reviewText: {
        marginBottom: theme.spacing.sm,
    },
    form: {
        marginBottom: theme.spacing.md,
    },
    formTitle: {
        marginBottom: theme.spacing.sm,
    },
    formField: {
        marginBottom: theme.spacing.sm,
    },
    divider: {
        marginBottom: theme.spacing.sm,
    }
}));

const therapistData = {
    name: 'John Doe',
    photo: 'https://example.com/therapist-photo.jpg',
    experience: '6 years of experience',
    themes: ['Anxiety', 'Depression', 'Relationships'],
    education: 'Masters in Psychology, University XYZ',
    info: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consequat ex vel dolor sagittis, id tincidunt tortor laoreet.',
    reviews: [
        {
            author: 'Client A',
            text: 'Great therapist! Highly recommended.',
        },
        {
            author: 'Client B',
            text: 'Very knowledgeable and helpful.',
        },
    ],
};

export function ChangeTherapist() {
    const theme = useMantineTheme();
    const { classes } = useStyles();
    const [reviewAuthor, setReviewAuthor] = useState('');
    const [reviewText, setReviewText] = useState('');
    const [reviews, setReviews] = useState(therapistData.reviews);

    const handleSubmitReview = (e) => {
        e.preventDefault();
        const newReview = {
            author: reviewAuthor,
            text: reviewText,
        };
        setReviews([...reviews, newReview]);
        setReviewAuthor('');
        setReviewText('');
    };

    return (
        <Container className={classes.container}>
            <Paper padding="md">
                <div className={classes.profileContainer}>
                    <Avatar className={classes.avatar} src={therapistData.photo} radius="xl" size={120} />
                    <div className={classes.infoContainer}>
                        <Title order={2}>{therapistData.name}</Title>
                        <Text className={classes.infoText}>{therapistData.experience}</Text>
                    </div>
                </div>

                <div>
                    <Title order={2}>About therapist</Title>
                    <Text className={classes.infoText}>
                        {therapistData.info}
                    </Text>
                    <Text className={classes.infoText}>
                        <strong>Themes:</strong> {therapistData.themes.join(', ')}
                    </Text>
                    <Text className={classes.infoText}>
                        <strong>Education:</strong> {therapistData.education}
                    </Text>
                </div>
                <div>
                    <Button className={classes.button}>Change therapist</Button>
                </div>
                <Divider className={classes.divider} />

                <div>
                    <Title className={classes.sectionTitle} order={3}>
                        Write a Review
                    </Title>
                    <form onSubmit={handleSubmitReview} className={classes.form}>
                        <div className={classes.formField}>
                            <TextInput
                                required
                                placeholder="Your name"
                                value={reviewAuthor}
                                onChange={(e) => setReviewAuthor(e.target.value)}
                            />
                        </div>
                        <div className={classes.formField}>
                            <Textarea
                                required
                                placeholder="Write your review"
                                value={reviewText}
                                onChange={(e) => setReviewText(e.target.value)}
                            />
                        </div>
                        <Button type="submit">Submit Review</Button>
                    </form>
                </div>

                <div>
                    <Title className={classes.sectionTitle} order={3}>
                        Reviews
                    </Title>
                    {reviews.map((review, index) => (
                        <div key={index} className={classes.reviewContainer}>
                            <Text className={classes.reviewAuthor}>{review.author}</Text>
                            <Text className={classes.reviewText}>{review.text}</Text>
                        </div>
                    ))}
                </div>
            </Paper>
        </Container>
    );
}