import { LOCAL_BACKEND_PATH, makePutRequest} from "./defaults";

export async function approveTherapist(accessToken, id) {
    const url = LOCAL_BACKEND_PATH + `/admin/approve/${id}`

    return makePutRequest(url, accessToken)
}
