import {Stepper, TextInput} from "@mantine/core";

export default function FirstStep({form}) {

    return (
        <div>
            <TextInput required label="Name" placeholder="Name" {...form.getInputProps('name')} />
            <TextInput required label="Surname" placeholder="Surname" {...form.getInputProps('surname')} />
            <TextInput required label="Phone number"
                       placeholder="Phone number" {...form.getInputProps('phoneNumber')} />
        </div>
    )
}
