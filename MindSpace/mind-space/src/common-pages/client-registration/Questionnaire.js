import React, {useState, useImperativeHandle, forwardRef} from 'react';
import {Container, Text, Checkbox, createStyles, rem, SimpleGrid} from '@mantine/core';

const useStyles = createStyles((theme) => ({
    questionnaire: {
        border: 'none',
        boxShadow: 'none',
        position: "relative",
    },

    question: {
        marginBottom: rem(20),
    },

    text: {
        marginBottom: rem(8),
    },

    checkbox: {
        marginBottom: rem(6),
    },

    submit: {
        position: "absolute",
        bottom: 0,
        right: 0,
        marginTop: rem(8),
        marginRight: rem(8),
    }
}));

const questionnaireData = [
    {
        question: 'How would you describe your current emotional state?',
        options: [
            { value: 'Depression', text: 'Depressed' },
            { value: 'Anxiety', text: 'Anxious' },
            { value: 'Stress', text: 'Stressed' },
            { value: 'Fair', text: 'Fair' },
            { value: 'Loneliness', text: 'Lonely' },
            { value: 'Emotion management', text: 'Overwhelmed' },
        ],
    },
    {
        question: 'Are you experiencing specific symptoms or issues?',
        options: [
            { value: 'Panic attacks', text: 'Panic attacks' },
            { value: 'Insomnia', text: 'Insomnia' },
            { value: 'Emotion management', text: 'Mood swings' },
            { value: 'Eating disorder', text: 'Eating disorder' },
            { value: 'Emotional dependence', text: 'Emotional dependence' },
            { value: 'Self-esteem', text: 'Unstable self-esteem' },
            { value: 'Concentration issues', text: 'Concentration issues' },
            { value: 'Addictions and habits', text: 'Issues with alcohol/drugs' },
        ],
    },
    {
        question: 'Are you experiencing relationship issues?',
        options: [
            { value: 'Relationship issues', text: 'With partner' },
            { value: 'Family issues1', text: 'With parents' },
            { value: 'Sexual issues', text: 'Sexual' },
            { value: 'Family issues2', text: 'With children' },
            { value: 'Interpersonal relationships', text: 'In general, with people around me' },
        ],
    },
    {
        question: 'Are you experiencing issues with your professional life?',
        options: [
            { value: 'Lack of motivation', text: 'Lack of motivation' },
            { value: 'Burn-out', text: 'Burn-out' },
            { value: 'Procrastination', text: 'Procrastination' },
            { value: 'Work-loss', text: 'Work-loss' },
            { value: 'Lack of purpose', text: 'Lack of purpose' },
            { value: 'Loss of direction', text: 'Loss of direction' },
            { value: 'None of the above', text: 'None of the above' },
        ],
    },
    {
        question: 'Rate your current self-esteem:',
        options: [
            { value: 'Very low', text: 'Very low' },
            { value: 'Low', text: 'Low' },
            { value: 'Moderate', text: 'Moderate' },
            { value: 'High', text: 'High' },
            { value: 'Very high', text: 'Very high' },
            { value: 'I am not sure', text: 'I am not sure' },
        ],
    },
    {
        question: 'Do you have a preference for the therapist\'s gender?',
        options: [
            { value: 'Male', text: 'Male' },
            { value: 'Female', text: 'Female' },
            { value: 'No preferences', text: 'No preferences' },
        ],
    },
    {
        question: 'In which language would you be most comfortable conducting sessions?',
        options: [
            { value: 'English', text: 'English' },
            { value: 'Czech', text: 'Czech' },
            { value: 'Ukrainian', text: 'Ukrainian' },
            { value: 'German', text: 'German' },
            { value: 'Russian', text: 'Russian' },
        ],
    },
];

const Questionnaire = forwardRef(({ onDataCollected }, ref) => {
    const [answers, setAnswers] = useState({});
    const { classes } = useStyles();

    const handleCheckboxChange = (questionIndex, value) => {
        const question = questionnaireData[questionIndex].question;
        setAnswers(prevAnswers => ({
            ...prevAnswers,
            [questionIndex]: { id: questionIndex, question: question, option: value },
        }));
    };

    useImperativeHandle(ref, () => ({
        getAnswers: () => Object.values(answers),
    }));

    // useEffect(() => {
    //     onDataCollected(Object.values(answers));
    // }, [answers, onDataCollected]);

    return (
        <Container
            size="sm"
            className={classes.questionnaire}
        >
            {questionnaireData.map((questionItem, index) => (
                <div
                    key={index}
                    className={classes.question}
                >
                    <Text
                        weight={500}
                        className={classes.text}
                    >
                        {questionItem.question}
                    </Text>
                    <Checkbox.Group
                        value={answers[index]?.option || []}
                        onChange={(value) => handleCheckboxChange(index, value)}
                    >
                        <SimpleGrid
                            cols={2}
                            spacing='xs'
                            breakpoints={[
                                {maxWidth: 'sm', cols: '1', spacing: 'xs'},
                            ]}
                        >
                            {questionItem.options.map((option, optionIndex) => (
                                <Checkbox
                                    key={optionIndex}
                                    value={option.value}
                                    label={option.text}
                                    className={classes.checkbox}
                                />
                            ))}
                        </SimpleGrid>
                    </Checkbox.Group>
                </div>
            ))}
        </Container>
    );
});

export default Questionnaire;