import {
    THERAPIST_DEFAULT_URL,
    LOCAL_BACKEND_PATH,
    makeGetRequest,
    makePostRequest,
    USERS_DEFAULT_URL,
    SCHEDULE_DEFAULT_URL
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