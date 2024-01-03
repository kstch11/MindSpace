import {useRef, useState} from 'react';
import {Stepper, Button, Group, TextInput, Code, createStyles,  Center} from '@mantine/core';
import {useForm} from '@mantine/form';
import Questionnaire from "./Questionnaire";
import FirstStep from "./FirstStep";
import ChooseTherapistStep from "./ChooseTherapistStep";
import {TherapistsList} from "../therapists-list/TherapistsList";

const useStyles = createStyles((theme) => ({
    questionnaire: {
        width: 900,
    }
}));

export function StepQuestionnaire() {
    const [active, setActive] = useState(0);
    const [temporaryAnswers, setTemporaryAnswers] = useState([]);
    const {classes} = useStyles();

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
                    surname: values.surname.trim().length < 2 ? 'Surname must include at least 2 characters' : null,
                    phoneNumber: ((val) => (/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/.test(val) ? null : 'Invalid phone number'))(values.phoneNumber),
                }
            }

            if (active === 1) {
                return {};
            }

            return {};
        },
    });

    const stepQuestionnaireStyle = {
        marginBottom: active === 0 || active === 2 ? '210px' : '0px',
    };

    const questionnaireRef = useRef();

    const handleDataFromChild = (data) => {
        setTemporaryAnswers(data);
    };

    const nextStep = async () => {
        const validation = await form.validate();

        if (!validation.hasErrors) {
            if (active === 1) {
                form.setFieldValue('answers', questionnaireRef.current.getAnswers());
            }

            setActive((current) => (current < 3 ? current + 1 : current));
        }
    };


    const prevStep = () => setActive((current) => (current > 0 ? current - 1 : current));

    return (
        <Center>
            <div className={classes.questionnaire}>
                <Stepper active={active} breakpoint="sm" style={stepQuestionnaireStyle}>

                    <Stepper.Step label="First step" description="Personal information">
                        <FirstStep form={form}/>
                    </Stepper.Step>

                    <Stepper.Step label="Second step" description="How do you feel?">
                        <Questionnaire ref={questionnaireRef} onDataCollected={handleDataFromChild} />
                    </Stepper.Step>
                    <Stepper.Completed>
                        <TherapistsList toggleValue='client' questionnaire={form.values} />
                    </Stepper.Completed>
                </Stepper>

                <Group position="right" mt="xl">
                    {active !== 0 && (
                        <Button variant="default" onClick={prevStep}>
                            Back
                        </Button>
                    )}
                    {active !== 3 && <Button onClick={nextStep}>Next step</Button>}
                    {active === 3 && <Button>Finish</Button>}
                </Group>
            </div>
        </Center>
    );
}
