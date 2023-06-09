import { useState } from 'react';
import {Stepper, Button, Group, TextInput, PasswordInput, Code, createStyles, rem, Center} from '@mantine/core';
import { useForm } from '@mantine/form';
import Questionnaire from "./Questionnaire";

const useStyles = createStyles((theme) => ({
    questionnaire: {
        width: '80%',
    }
}));

export function StepQuestionnaire() {
    const [active, setActive] = useState(0);
    const { classes } = useStyles();

    const form = useForm({
        initialValues: {
            answers: [],
            name: '',
            surname: '',
            phoneNumber: '',
            website: '',
            github: '',
        },

        validate: (values) => {
            if (active === 0) {
                return {
                    name: values.name.trim().length < 2 ? 'Name must include at least 2 characters' : null,
                    phoneNumber: (val) => (/^\d{7,16}$/.test(val) ? null : 'Invalid phone number'),
                }
            }

            if (active === 1) {
                return {

                };
            }

            return {};
        },
    });

    const stepQuestionnaireStyle = {
        marginBottom: active === 0 || active === 2 ? '210px' : '0px',
    };

    const handleDataFromChild = (data) => {
        form.setFieldValue('answers', data);
        console.log('Data received from child component:', data);
    };

    const nextStep = async () => {
        const validation = await form.validate();

        if (!validation.hasErrors) {
            setActive((current) => (current < 3 ? current + 1 : current));
        }
    };


    const prevStep = () => setActive((current) => (current > 0 ? current - 1 : current));

    return (
        <Center>
            <div className={classes.questionnaire}>
                <Stepper active={active} breakpoint="sm" style={stepQuestionnaireStyle}>
                    <Stepper.Step label="First step" description="Personal information">
                        <TextInput required label="Name" placeholder="Name" {...form.getInputProps('name')} />
                        <TextInput required label="Surname" placeholder="Surname" {...form.getInputProps('surname')} />
                        <TextInput required label="Phone number" placeholder="Phone number" {...form.getInputProps('phoneNumber')} />
                    </Stepper.Step>

                    <Stepper.Step label="Second step" description="How do you feel?">
                        <Questionnaire onDataCollected={handleDataFromChild} />
                    </Stepper.Step>

                    <Stepper.Step label="Final step" description="Choose a therapist">
                        <TextInput label="Website" placeholder="Website" {...form.getInputProps('website')} />
                        <TextInput
                            mt="md"
                            label="GitHub"
                            placeholder="GitHub"
                            {...form.getInputProps('github')}
                        />
                    </Stepper.Step>
                    <Stepper.Completed>
                        Completed! Form values:
                        <Code block mt="xl">
                            {JSON.stringify(form.values, null, 2)}
                        </Code>
                    </Stepper.Completed>
                </Stepper>

                <Group position="right" mt="xl">
                    {active !== 0 && (
                        <Button variant="default" onClick={prevStep}>
                            Back
                        </Button>
                    )}
                    {active !== 3 && <Button onClick={nextStep}>Next step</Button>}
                </Group>
            </div>
        </Center>
    );
}