import {
    RESERVATION_DEFAULT_URL,
    LOCAL_BACKEND_PATH,
    makeGetRequest,
    makePostRequest,
    makePutRequest,
    USERS_DEFAULT_URL, makeDeleteRequest
} from "./defaults";

export async function postReservation(accessToken, reservationBody) {
    const url = LOCAL_BACKEND_PATH + RESERVATION_DEFAULT_URL;

    return makePostRequest(url, accessToken, reservationBody)
}

export async function deleteReservation(accessToken, reservationId) {
    const url = LOCAL_BACKEND_PATH + RESERVATION_DEFAULT_URL + `/${reservationId}`

    return makeDeleteRequest(url, accessToken)
}

