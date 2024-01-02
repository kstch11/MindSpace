import {Button, ButtonProps, Group} from '@mantine/core';
import {GoogleIcon} from './GoogleIcon';

export function GoogleButton(props) {
    return (
        <Button
            leftIcon={<GoogleIcon/>}
            variant="default"
            color="gray"
            onClick={async (e) => await props.onLoginClick()}
            radius="xl"
        />
    );
}
