export const DEFAULT_BACKEND_PATH = "http://localhost:8090"

export const USERS_DEFAULT_URL = "/users"

export const CLIENT_DEFAULT_URL = "/clients"


export async function makeGetRequest(url, accessToken) {
    const response = await fetch(url, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${accessToken}`
        }
    })

    if (!response.ok) {
        throw new Error(`Error while making GET request to ${url}`)
    }

    return response.json();
}

export async function makePostRequest(url, accessToken, body) {
    const response = await fetch(url, {
        method: "POST",
        body: JSON.parse(body),
        headers: {
            "Authorization": `Bearer ${accessToken}`
        }
    })

    if (!response.ok) {
        throw new Error(`Error while making POST request to ${url}. Body: ${body}`)
    }

    return response.json();
}
