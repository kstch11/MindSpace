import {
    createStyles,
    Stepper,
    Group,
    Button,
    rem,
    TextInput,
    Textarea,
    MultiSelect,
    FileInput,
    Select, PasswordInput
} from "@mantine/core";
import { useState, useEffect } from 'react';
import {useForm} from "@mantine/form";
import {DatePicker, DatePickerInput} from "@mantine/dates";
import {useDisclosure} from "@mantine/hooks";

const useStyles = createStyles((theme) =>({
    inner: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        paddingTop: theme.spacing.md,
        paddingBottom: `calc(${theme.spacing.xl} * 5)`,
        flexDirection: 'column',
    },

    content: {
        maxWidth: rem(1500),
        marginRight: `0 auto`,

        [theme.fn.smallerThan('sm')]: {
            maxWidth: '100%',
            margin: '0 3%',
        },
    },
}))

export function ApplicationStepper() {
    const {classes} = useStyles();
    const [active, setActive] = useState(0);
    const [visible, { toggle }] = useDisclosure(false);
    const nextStep = async () => {
        const validation = await form.validate();

        if (!validation.hasErrors) {
            setActive((current) => (current < 3 ? current + 1 : current));
        }
    };
    const prevStep = () => setActive((current) => (current > 0 ? current - 1 : current));

    const form = useForm({
        initialValues: {
            firstname:'',
            surname:'',
            dateOfBirth: null,
            description: '',
            education: '',
            specialization: [],
            certificates: [],
            therapeuticCommunity: '',
            languages: '',
            personalTherapy: '',
            photo: null,
            phoneNumber: '',
            email: '',
            password: '',
            confirmPassword: '',
        },

        validate: (values) => {
            if (active === 0) {
                return {
                    firstname: values.firstname.trim().length < 2 ? 'First name must include at least 2 characters' : null,
                    surname: values.surname.trim().length < 2 ? 'Second name must include at least 2 characters' : null,
                    description: values.description.trim().length < 2 ? 'Description name must include at least 2 characters' : null,
                    dateOfBirth: ((val) => {
                        if (!val) return 'Date of birth is required';

                        const today = new Date();
                        const birthDate = new Date(val);
                        let age = today.getFullYear() - birthDate.getFullYear();
                        const m = today.getMonth() - birthDate.getMonth();

                        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                            age--;
                        }

                        return age >= 18 ? null : 'You must be at least 18 years old';
                    })(values.dateOfBirth),
                }
            }

            if (active === 1) {
                return {
                    specialization: ((val) => (val.length < 3 ? 'You should choose at least 3 topics' : null))(values.specialization),
                    education: values.education.trim().length < 5 ? 'Education must include at least 5 characters' : null,
                    certificates: ((val) => (val.length === 0 ? 'You must upload your diploma' : null))(values.certificates),
                    therapeuticCommunity: values.therapeuticCommunity.trim().length < 2 ? 'You should answer at least something' : null,
                    languages: values.languages.trim().length < 2 ? 'Languages must include at least 2 characters' : null,
                }
            }

            if (active === 2) {
                return {
                    email: ((val) => (/^\S+@\S+$/.test(val) ? null : 'Invalid email'))(values.email),
                    password: ((val) => (val.length < 6 ? 'Password should include at least 6 characters' : null))(values.password),
                    confirmPassword: ((val) => {
                        if (val !== form.values.password) {
                            return 'Passwords do not match';
                        }
                        return null;
                    })(values.confirmPassword),
                }
            }

            return {}
        }
    })

    return(
        <div className={classes.inner}>
            <div className={classes.content}>
                <Stepper active={active} onStepClick={setActive} breakpoint="sm">
                    <Stepper.Step label="First step" description="Personal information">
                        <TextInput
                            label="First name"
                            placeholder="First name"
                            {...form.getInputProps('firstname')}
                            required
                        />
                        <TextInput
                            label="Last name"
                            placeholder="Last name"
                            {...form.getInputProps('surname')}
                            required
                        />
                        <DatePickerInput
                            label="Date of Birth"
                            placeholder="Date of birth"
                            {...form.getInputProps('dateOfBirth')}
                            required
                        />
                        <Textarea
                            label="Self-description"
                            placeholder="Self-description"
                            {...form.getInputProps('description')}
                            minRows={3}
                            required
                        />
                    </Stepper.Step>
                    <Stepper.Step label="Second step" description="Education">
                        <MultiSelect
                            required
                            data={[
                                'Depression', 'Anxiety', 'Stress', 'Interpersonal relationships', 'Emotion management',
                                'Self-esteem', 'Self-acceptance', 'Trauma and loss', 'Addictions and habits',
                                'Personal development', 'Professional issues', 'Sexual issues', 'Family issues',
                                'Relationship issues'
                            ]}
                            label="Choose the topics you work with:"
                            placeholder="Select specializations"
                            {...form.getInputProps('specialization')}
                        />
                        <TextInput
                            required
                            label="What is your higher education? Write about basic psychological (related) training or retraining: 1. Year of graduation 2. Name of institution of higher education 3. Name of department and specialization 4. Indicate academic degree (bachelor's, master's) or academic degree (if any)."
                            placeholder="Your educational background"
                            {...form.getInputProps('education')}
                        />
                        <FileInput
                            label="Please attach photos of detailed diplomas and certificates confirming the training. Required documents: (1) diploma of basic psychological (related) training / retraining (2) documents of training in the method. If the training is not completed, please attach a certificate from the training institution."
                            placeholder="Upload your diplomas and certificates"
                            {...form.getInputProps('certificates')}
                            multiple
                            required
                        />
                        <Textarea
                            required
                            label="Do you belong to any psychotherapeutic community. If yes, which one?"
                            placeholder="Enter text here"
                            {...form.getInputProps('therapeuticCommunity')}
                        />
                        <TextInput
                            required
                            label="In which languages you can lead sessions?"
                            placeholder="Languages"
                            {...form.getInputProps('languages')}
                        />
                        <Select
                            required
                            label="Are you in personal psychotherapy?"
                            placeholder="Pick one"
                            data={[
                                { label: 'Yes', value: 'yes' },
                                { label: 'No', value: 'no' },
                            ]}
                            {...form.getInputProps('personalTherapy')}
                        />
                    </Stepper.Step>
                    <Stepper.Step label="Final step" description="Contact information">
                        <FileInput
                            label="Please attach photos of detailed diplomas and certificates confirming the training. Required documents: (1) diploma of basic psychological (related) training / retraining (2) documents of training in the method. If the training is not completed, please attach a certificate from the training institution."
                            placeholder="Upload your diplomas and certificates"
                            {...form.getInputProps('photo')}
                            multiple
                            required
                        />
                        <TextInput
                            required
                            label="Phone number address for contacting"
                            placeholder="Phone number"
                            {...form.getInputProps('phoneNumber')}
                        />
                        <TextInput
                            required
                            label="E-mail address for contacting"
                            placeholder="E-mail"
                            {...form.getInputProps('email')}
                        />
                        <PasswordInput
                            required
                            label="Password"
                            placeholder="Enter your password"
                            visible={visible}
                            onVisibilityChange={toggle}
                            {...form.getInputProps('password')}
                        />
                        <PasswordInput
                            required
                            label="Confirm Password"
                            placeholder="Confirm your password"
                            error={form.errors.confirmPassword}
                            visible={visible}
                            onVisibilityChange={toggle}
                            {...form.getInputProps('confirmPassword')}
                        />
                    </Stepper.Step>
                    <Stepper.Completed>
                        Completed, click back button to get to previous step
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
        </div>
    );
}
