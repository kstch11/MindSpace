import {
    CLIENT_DEFAULT_URL,
    LOCAL_BACKEND_PATH,
    makeGetRequest,
    makePostRequest,
    makePutRequest,
    USERS_DEFAULT_URL
} from "./defaults";

export async function fetchClientProfile(clientId, accessToken) {
    const url = LOCAL_BACKEND_PATH + CLIENT_DEFAULT_URL + `/${clientId}`;

    return makeGetRequest(url, accessToken);
}

export async function fetchCurrentUser(accessToken) {
    const url = LOCAL_BACKEND_PATH + USERS_DEFAULT_URL + "/profile";

    return makeGetRequest(url, accessToken);
}

export async function fetchClientReservations(clientId, accessToken) {
    const url = LOCAL_BACKEND_PATH + CLIENT_DEFAULT_URL + `/${clientId}/reservations`
    console.log(url)

    return makeGetRequest(url, accessToken);
}

export async function setRegistrationComplete(accessToken) {
    const url = LOCAL_BACKEND_PATH + CLIENT_DEFAULT_URL + "/profile/regDone"

    return makePutRequest(url, accessToken)
}

export async function postQuestionnaire(accessToken, questionnaireBody) {
    const url = LOCAL_BACKEND_PATH + CLIENT_DEFAULT_URL + "/questionnaire"
    console.log(url)

    return makePostRequest(url, accessToken, questionnaireBody);
}

export async function putTherapist(accessToken, putTherapistBody) {
    const url = LOCAL_BACKEND_PATH + CLIENT_DEFAULT_URL + "/therapist"
    console.log(url)

    return makePutRequest(url, accessToken, putTherapistBody)
}

export async function updateClient(accessToken, putNewDetails, clientId) {
    const url = LOCAL_BACKEND_PATH + CLIENT_DEFAULT_URL + `/${clientId}`

    return makePutRequest(url, accessToken, putNewDetails)
}

