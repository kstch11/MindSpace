import {
    THERAPIST_DEFAULT_URL,
    LOCAL_BACKEND_PATH,
    makeGetRequest,
    makePostRequest,
    USERS_DEFAULT_URL,
    SCHEDULE_DEFAULT_URL, makePutRequest
} from "./defaults";

export async function fetchTherapistProfile(therapistId, accessToken) {
    const url = LOCAL_BACKEND_PATH + THERAPIST_DEFAULT_URL + `/${therapistId}`;

    return makeGetRequest(url, accessToken);
}

export async function fetchAllTherapists (accessToken) {
    const url = LOCAL_BACKEND_PATH + THERAPIST_DEFAULT_URL + "/allTherapists";

    return makeGetRequest(url, accessToken);
}

export async function fetchAllReservations(therapistId, accessToken) {
    const url = LOCAL_BACKEND_PATH + THERAPIST_DEFAULT_URL + `/${therapistId}/reservations`;

    return makeGetRequest(url, accessToken);
}

export async function fetchSchedule(accessToken) {
    const url = LOCAL_BACKEND_PATH + SCHEDULE_DEFAULT_URL + "/schedules";

    return makeGetRequest(url, accessToken);
}

export async function postReview(accessToken, therapistId, reviewBody) {
    const url = LOCAL_BACKEND_PATH + THERAPIST_DEFAULT_URL + `/${therapistId}/reviews`

    return makePostRequest(url, accessToken, reviewBody);
}

export async function setTherapistApplicationComplete(accessToken) {
    const url = LOCAL_BACKEND_PATH + THERAPIST_DEFAULT_URL + "/profile/regDone"

    return makePutRequest(url, accessToken);
}

export async function postTherapistQuestionnaire(accessToken, questionnaireBody) {
    const url = LOCAL_BACKEND_PATH + THERAPIST_DEFAULT_URL + "/therapistQuestionnaire"

    return makePostRequest(url, accessToken, questionnaireBody);
}

export async function getReviews(accessToken, therapistId) {
    const url = LOCAL_BACKEND_PATH + THERAPIST_DEFAULT_URL + `/${therapistId}/reviews`

    return makeGetRequest(url, accessToken);
}

export async function updateTherapist(accessToken, putNewDetails, therapistId) {
    const url = LOCAL_BACKEND_PATH + THERAPIST_DEFAULT_URL + `/${therapistId}`

    return makePutRequest(url, accessToken, putNewDetails);
}