import {
  createStyles,
  Image,
  Container,
  Title,
  Button,
  Group,
  Text,
  rem, MantineProvider, Divider,
} from '@mantine/core';
import image from '../../assets/main.jpg';
import {FeaturesCards} from "./SecondSection"
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";
import {useState} from "react";

const useStyles = createStyles((theme) => ({
  inner: {
    display: 'flex',
    justifyContent: 'space-between',
    paddingTop: `calc(${theme.spacing.xl} * 2)`,
    paddingBottom: `calc(${theme.spacing.xl} * 5)`,
  },

  content: {
    maxWidth: rem(400),
    marginRight: `calc(${theme.spacing.xl} * 4)`,

    [theme.fn.smallerThan('sm')]: {
      maxWidth: '100%',
      marginRight: 0,
    },
  },

  title: {
    color: theme.colorScheme === 'dark' ? theme.white : theme.black,
    fontFamily: `Greycliff CF, ${theme.fontFamily}`,
    fontSize: rem(44),
    lineHeight: 1.2,
    fontWeight: 900,

    [theme.fn.smallerThan('xs')]: {
      fontSize: rem(28),
    },
  },

  control: {
    [theme.fn.smallerThan('xs')]: {
      flex: 1,
    },
  },

  image: {
    flex: 1,
    [theme.fn.smallerThan('sm')]: {
      display: 'none',
    },
  },

  highlight: {
    position: 'relative',
    backgroundColor: theme.fn.variant({ variant: 'light', color: theme.primaryColor }).background,
    borderRadius: theme.radius.sm,
    padding: `${rem(4)} ${rem(12)}`,
  },
}));

export function MainPage() {
  const { classes } = useStyles();
  const isLoggedIn = useSelector(state => state.currentUser.accessToken != null)
  const [redirect, setRedirect] = useState(false);

  const handleLogin = () => {
      setRedirect(true);
  }
  if (redirect) {
    return <Navigate to="login"/>
  }

  return (
      <MantineProvider>
        <Container>
          <div className={classes.inner}>
            <div className={classes.content}>
              <Title className={classes.title}>
                Consultation with psychotherapists <span className={classes.highlight}>online</span>
              </Title>
              <Text color="dimmed" mt="md">
                Soothing Minds, Guiding Spaces: Welcome to MindSpace, Your Online Haven for Psychotherapy
              </Text>

              <Group mt={30}>
                <Button radius="md" size="md" className={classes.control} onClick={handleLogin}>
                  Find a therapist
                </Button>
              </Group>
            </div>
            <Image src={image} className={classes.image} />
          </div>
        </Container>
        <Divider my="sm" className={classes.divider}></Divider>
        <FeaturesCards />
      </MantineProvider>
  );
}
