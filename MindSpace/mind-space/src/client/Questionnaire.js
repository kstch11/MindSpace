import React, { useState, useEffect } from 'react';
import {Container, Text, Checkbox, Button, createStyles, rem} from '@mantine/core';

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
        question: 'Question 1',
        options: [
            { value: 'Option 1', text: 'Option 1' },
            { value: 'Option 2', text: 'Option 2' },
            { value: 'Option 3', text: 'Option 3' },
        ],
    },
    {
        question: 'Question 2',
        options: [
            { value: 'Option A', text: 'Option A' },
            { value: 'Option B', text: 'Option B' },
            { value: 'Option C', text: 'Option C' },
        ],
    },
    {
        question: 'Question 3',
        options: [
            { value: 'Option A', text: 'Option A' },
            { value: 'Option B', text: 'Option B' },
            { value: 'Option C', text: 'Option C' },
        ],
    },
    {
        question: 'Question 4',
        options: [
            { value: 'Option A', text: 'Option A' },
            { value: 'Option B', text: 'Option B' },
            { value: 'Option C', text: 'Option C' },
        ],
    },
    {
        question: 'Question 5',
        options: [
            { value: 'Option A', text: 'Option A' },
            { value: 'Option B', text: 'Option B' },
            { value: 'Option C', text: 'Option C' },
        ],
    },
];

function Questionnaire({onDataCollected}) {
    const [answers, setAnswers] = useState([]);
    const { classes } = useStyles();

    const handleSubmit = () => {
        console.log('Submitted answers:', answers);
    };

    useEffect(() => {
        // Pass the collected data to the parent component when answers change
        onDataCollected(answers);
    }, [answers, onDataCollected]);

    return (
        <Container
            size="sm"
            className={classes.questionnaire}
        >
            {questionnaireData.map((question, index) => (
                <div
                    key={index}
                    className={classes.question}
                >
                    <Text
                        weight={500}
                        className={classes.text}
                    >
                        {question.question}
                    </Text>
                    <Checkbox.Group
                        value={answers[index] || []}
                        onChange={(value) => {
                            const updatedAnswers = [...answers];
                            updatedAnswers[index] = value;
                            setAnswers(updatedAnswers);
                        }}
                    >
                        {question.options.map((option, optionIndex) => (
                            <Checkbox
                                key={optionIndex}
                                value={option.value}
                                label={option.text}
                                className={classes.checkbox}
                            />
                        ))}
                    </Checkbox.Group>
                </div>
            ))}
        </Container>
    );
}

export default Questionnaire;
