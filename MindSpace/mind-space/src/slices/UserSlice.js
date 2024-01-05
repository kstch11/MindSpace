import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    accessToken: undefined,
    profile: {
        id: -1,
        name: '',
        surname: '',
        email: '',
        phoneNumber: '',
        registrationFinished: false,
        isTherapist: false,
        isAdmin: false,
    },
}

const currentUserSlice = createSlice({
    name: 'currentUser',
    initialState: initialState,
    reducers: {
        setAccessToken: (state, action) => {
            state.accessToken = action.payload;
        },
        setProfile: (state, action) => {
            state.profile = action.payload
        },
        revokedAccessToken: (state) => {
            state.accessToken = undefined;
            state.profile = undefined;
        }
    }
})

export const {setAccessToken, setProfile, revokedAccessToken} = currentUserSlice.actions
export default currentUserSlice.reducer
